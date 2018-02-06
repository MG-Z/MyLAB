package main.com.proxy;

import net.sf.cglib.proxy.*;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * CGLIB : 动态代理
 * 回调对象：CallBack   1. MethodInterceptor 2. CallbackFilter 3.NoOp  4.FixedValue 5. <1>.LazyLoader <2>.Dispatcher   6.InterfaceMaker
 * 1. 动态代理实现的方法拦截器
 * 2. 过滤器 指定方法使用指定拦截器
 * 3. 不执行拦截操作
 * 4. 固定返回值<不会调用实际对象方法体>
 * 5. 延迟加载
 * 6. 接口生成器
 * 添加过滤器 实现 CallbackFilter 可以指定调用方法 选择 callbacks[] 数组中回调代理对象
 * func desc:
 */
public class DynamicProxyCGLib {

    public static void main(String[] args) {
        TargetCGLib target;
        CGLibProxy proxy = new CGLibProxy();
        TargetMethodCallBackFilter filter = new TargetMethodCallBackFilter();

//        target = DynamicProxy.createProxyInstance(proxy, TargetCGLib.class);

        target = DynamicProxy.createProxyInstance(TargetCGLib.class, new Callback[]{proxy, NoOp.INSTANCE, new TargetResultFixed()}, filter);

        System.out.println(" outer get :" + target.operate());
        System.out.println(" outer get :" + target.operate2());
//        analyze();
    }


    public static void analyze() {
        System.out.println("----------------analyze------------------------");

        try {
            Class<?> c = Class.forName("DynamicProxyCGLib$TargetCGLib$$EnhancerByCGLIB$$f9c5d708");
            Class<?> beanc = Class.forName("DynamicProxyCGLib$TargetCGLib");

            Method[] beanc_method = beanc.getMethods();
            int i = 1;
            System.out.println("原始的bean的方法总共" + beanc_method.length + "个");
            for (Method method : beanc_method) {

                System.out.println("原始的bean方法" + i++ + method.getName());

            }
            i = 1;
            Method[] methods = c.getMethods();
            System.out.println("我们得到的bean的方法总共" + methods.length + "个");
            for (Method method : methods) {
                System.out.println("我们得到的bean的方法" + i++ + method.getName());
            }
            System.out.println("原始的bean的父类：" + beanc.getSuperclass());
            System.out.println("我们得到的bean的父类：" + c.getSuperclass());

            Field[] bean_fields = beanc.getDeclaredFields();
            i = 1;
            for (Field field : bean_fields) {
                System.out.println("原始bean的属性 " + i++ + field);
            }

            Field[] fields = c.getDeclaredFields();
            i = 1;
            for (Field field : fields) {
                System.out.println("我们得到的bean的属性 " + i++ + field);
            }
            Class proxyGenerator = Class.forName("sun.misc.ProxyGenerator");
            Method[] methods2 = proxyGenerator.getMethods();
            for (Method method : methods2) {
                System.out.println(method);
                byte[] TempProxySuper = (byte[]) method.invoke(proxyGenerator, "TempProxySuper", new Class[]{c.getSuperclass()});
                byte[] TempProxy = (byte[]) method.invoke(proxyGenerator, "TempProxy", new Class[]{c});
                byte[] TempBean = (byte[]) method.invoke(proxyGenerator, "TempBean", new Class[]{beanc});
                createClassFile("TempProxy", TempProxy);
                createClassFile("TempProxySuper", TempProxySuper);
                createClassFile("TempBean", TempBean);
                break;
            }

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 生成class文件
     * @param fileName
     * @param classFile
     */
    public static void createClassFile(String fileName, byte[] classFile) {
        try {
            File file;
            FileOutputStream fos = new FileOutputStream(file = new File(fileName + ".class"));
            fos.write(classFile);
            fos.flush();
            fos.close();
            System.out.println(file.getAbsolutePath());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static class TargetCGLib {
        public String operate() {
            System.out.println("invoked target method 1");
            return "operate 1.";
        }

        public String operate2() {
            System.out.println("invoked target method 2");
            return "operate 2.";
        }
    }

    public static class CGLibProxy implements MethodInterceptor {

        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            Object ret = methodProxy.invokeSuper(o, objects);
            System.out.println("CGLib InspectJ " + ret.toString());
            return ret;
        }
    }


    //方法过滤器
    public static class TargetMethodCallBackFilter implements CallbackFilter {
        /**
         * @param method 被调用的方法
         * @return 值为被代理类的各个方法在回调数组Callback[]中的位置索引 new Callback[]{proxy, NoOp.INSTANCE,new TargetResultFixed()}
         * 0 为实现的动态代理拦截器  2 为不实现拦截，要执行被代理对象方法  3.固定返回  不执行拦截,也不执行方法
         */
        public int accept(Method method) {
            int index = 0;
            String name = method.getName();
            if (name.equalsIgnoreCase("operate")) {
                index = 0;
            }
            if (name.equalsIgnoreCase("operate2")) {
                index = 2;
            }
            return index;
        }
    }

    public static class TargetResultFixed implements FixedValue {

        public Object loadObject() throws Exception {
            return "fixed value";
        }
    }


    public static class DynamicProxy {
        public static <T, R extends MethodInterceptor> T createProxyInstance(R r, Class<?> superClass) {
            return createProxyInstance(superClass, new Callback[]{r}, null);
        }

        public static <T> T createProxyInstance(Class<?> superClass, Callback[] callbacks, CallbackFilter filter) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(superClass);
            enhancer.setCallbacks(callbacks);
            if (filter != null) {
                enhancer.setCallbackFilter(filter);
            }
            return (T) enhancer.create();
        }
    }

}
