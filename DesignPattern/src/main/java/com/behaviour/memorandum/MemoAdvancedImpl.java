package com.behaviour.memorandum;

import com.utils.BeanUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * func desc:
 * 备忘录高级实现(具体实用的实现)  多备份 同时保证备份的安全性
 */
public class MemoAdvancedImpl {


    public static void main(String[] args) {

        Originator originator = new Originator("origin_one", "origin_two");
        String key = "20180209";
        originator.createMemo(key);
        System.out.println("origin :" + originator.toString());
        originator.state_one = "update_one";
        originator.state_two = "update_two";
        System.out.println("update :" + originator.toString());
        originator.recoverFromMemo(key);
        System.out.println("cover " + originator.toString());

    }


    static class Originator {
        private String state_one;
        private String state_two;

        public Originator(String state_one, String state_two) {
            this.state_one = state_one;
            this.state_two = state_two;
        }

        public String getState_one() {
            return state_one;
        }

        public void setState_one(String state_one) {
            this.state_one = state_one;
        }

        public String getState_two() {
            return state_two;
        }

        public void setState_two(String state_two) {
            this.state_two = state_two;
        }

        public void createMemo(String key) {
            MemoTo memoTo = new MemoTo(key);
            System.out.println("backup with key:" + key);
            memoTo.setBackUps(BeanUtils.backUpFieldFromBean(this));
            MemoManagers.addBackUp(key, memoTo);
        }

        public void recoverFromMemo(String key) {
            MemoTo memoTo = (MemoTo) MemoManagers.getBackup(key);
            if (memoTo == null) {
                System.out.println("Can Not Found Memo for Key:" + key);
                return;
            }
            System.out.println("recover with key:" + key);
            BeanUtils.coverFiledToBean(this, memoTo.getBackUps());
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Originator{");
            sb.append("state_one='").append(state_one).append('\'');
            sb.append(", state_two='").append(state_two).append('\'');
            sb.append('}');
            return sb.toString();
        }

        // 将具体的备份对象 内置为 需要备份对象的内部类,杜绝外部访问备份对象
        private class MemoTo implements IMemoTo {
            private Map<String, Object> backUps;
            private String key;

            public MemoTo(String key) {
                this.key = key;
            }

            public Map<String, Object> getBackUps() {
                return backUps;
            }

            public void setBackUps(Map<String, Object> backUps) {
                this.backUps = backUps;
            }

            public String getKey() {
                return key;
            }
        }
    }

    //多备份管理器  按照备份快照 键值保存
    static class MemoManagers {
        // value  为 IMemo   为空接口   所以通过 此备份保存接口无法访问任何备份数据  当然你反射除外
        private static final Map<String, IMemoTo> backups = new ConcurrentHashMap<>();

        public static void addBackUp(String key, IMemoTo memoTo) {
            backups.put(key, memoTo);
        }

        public static IMemoTo getBackup(String key) {
            return backups.get(key);
        }

    }

    // 空接口   备份对象被外部对象持有的几率 但是没有操作数据的接口 保证数据安全
    interface IMemoTo {
    }


}
