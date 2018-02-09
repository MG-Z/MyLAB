package com.behaviour.memorandum;

/**
 * func desc:
 * 备忘录模式：用于保存某个对象的某个瞬时快照,并且用于恢复对象的状态
 * 此类实现标准的备忘录模式(标准模式往往都是理论性，没有实用性)
 * 组成结构： 需要被备份的对象
 * 实用场景： 需要保存或者恢复数据的场景
 */
public class MemoContext {

    public static void main(String[] args) {
        Person person = new Person("origin_one", "origin_two", "origin_three");
        MemosManager memosManager = new MemosManager();
        memosManager.setMemo(person.createMemo());

        person.display();
        person.state_three = "update_three";
        person.state_two = "update_two";
        person.state_one = "update_one";

        person.display();

        person.restoreMemo(memosManager.getMemo());
        person.display();


        //采用原型模式保存快照
        PersonClone personClone = new PersonClone("","","");
//        memosManager.setMemo(personClone.clone());
    }

    /** 需要被备忘的对象 */
    static class Person {
        private String state_one;
        private String state_two;
        private String state_three;

        public Person(String state_one, String state_two, String state_three) {
            this.state_one = state_one;
            this.state_two = state_two;
            this.state_three = state_three;
        }

        /** 理论做法  另外可以通过原型模式 采用继承Cloneable 实现多状态的保存 */
        public PersonMemo createMemo() {
            return new PersonMemo(state_one, state_two, state_three);
        }

        public void restoreMemo(PersonMemo memo) {
            this.state_one = memo.backup_state_one;
            this.state_two = memo.backup_state_two;
            this.state_three = memo.backup_state_three;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Person{");
            sb.append("state_one='").append(state_one).append('\'');
            sb.append(", state_two='").append(state_two).append('\'');
            sb.append(", state_three='").append(state_three).append('\'');
            sb.append('}');
            return sb.toString();
        }

        public void display() {
            System.out.println(toString());
        }
    }

    static class PersonClone implements Cloneable {
        private String state_one;
        private String state_two;
        private String state_three;

        public PersonClone(String state_one, String state_two, String state_three) {
            this.state_one = state_one;
            this.state_two = state_two;
            this.state_three = state_three;
        }

        public void restoreMemo(PersonMemo memo) {
            this.state_one = memo.backup_state_one;
            this.state_two = memo.backup_state_two;
            this.state_three = memo.backup_state_three;
        }

        @Override
        public Object clone() {
            try {
                // 注意浅拷贝和深拷贝问题
                return super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Person{");
            sb.append("state_one='").append(state_one).append('\'');
            sb.append(", state_two='").append(state_two).append('\'');
            sb.append(", state_three='").append(state_three).append('\'');
            sb.append('}');
            return sb.toString();
        }

        public void display() {
            System.out.println(toString());
        }
    }

    /** 备忘录对象 */
    static class PersonMemo {
        private String backup_state_one;
        private String backup_state_two;
        private String backup_state_three;

        public PersonMemo(String backup_state_one, String backup_state_two, String backup_state_three) {
            this.backup_state_one = backup_state_one;
            this.backup_state_two = backup_state_two;
            this.backup_state_three = backup_state_three;
        }
    }

    /**
     * 备忘录对象管理器
     */
    static class MemosManager {
        private PersonMemo memo;

        public PersonMemo getMemo() {
            return memo;
        }

        public void setMemo(PersonMemo memo) {
            this.memo = memo;
        }
    }


}
