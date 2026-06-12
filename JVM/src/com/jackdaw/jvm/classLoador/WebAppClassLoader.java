package com.jackdaw.jvm.classLoador;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * @author jackdaw
 * @description  Imitate  WebAppClassLoader of Tomcat
 */
public class WebAppClassLoader extends ClassLoader {
    private String classPath;

    public WebAppClassLoader(String classPath) {
        this.classPath = classPath;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = null;
        try {
            bytes = loadClassData(name);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
        // 将字节数组转换为一个class 对象
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] loadClassData(String name) throws Exception {
        name = name.replace(".", "/");
        FileInputStream fis = new FileInputStream(classPath + "/" + name + ".class");
        int len = fis.available();
        byte[] bytes = new byte[len];
        fis.read(bytes);
        fis.close();
        return bytes;

    }


    /**
     * 重写 loadClass() 方法，打破双亲委派机制
     * @param name
     * @param resolve
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            // 此处删除父类方法中委托给父类加载器加载的逻辑

            long t1 = System.nanoTime();
            if (c == null) {
                // 非自定义的类，委托给父类加载器加载
                if (!name.startsWith("com.jackdaw")) {
                    c = getParent().loadClass(name);
                } else {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    c = findClass(name);
                }

                // this is the defining class loader; record the stats
                sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                sun.misc.PerfCounter.getFindClasses().increment();
            }

            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }


}


class TestWebAppClassLoader {

    public static void main(String[] args) throws Exception {
        // 测试同一个web容器，隔离不同应用程序依赖的相同类的不同版本

        WebAppClassLoader wcl1 = new WebAppClassLoader("E:/summary/code/tech-summary/JVM/war1");
        Class<?> clazz = wcl1.loadClass("com.jackdaw.Student");
        System.out.println("====================> check class method and ClassLoader name");
        Object obj = clazz.newInstance();
        Method method= clazz.getDeclaredMethod("show", null);
        method.invoke(obj, null);
        System.out.println(clazz.getClassLoader());

        WebAppClassLoader wcl2 = new WebAppClassLoader("E:/summary/code/tech-summary/JVM/war2");
        Class<?> clazz2 = wcl2.loadClass("com.jackdaw.Student");
        System.out.println("====================> check class method and ClassLoader name");
        Object obj2 = clazz2.newInstance();
        Method method2= clazz2.getDeclaredMethod("show", null);
        method2.invoke(obj2, null);
        System.out.println(clazz2.getClassLoader());



    }
}