package com.jackdaw.jvm.classLoador;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * @author jackdaw
 * @description 自定义类加载器 重点是重写 findClass() 方法
 */
public class MyClassLoader extends ClassLoader {
    private String classPath;

    public MyClassLoader(String classPath) {
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

            if (c == null) {
                // 防止一些核心类找不到路径，委托给父类加载器加载
                if (name != null && name.startsWith("java.")) {
                    return getParent().loadClass(name);
                }

                // If still not found, then invoke findClass in order
                // to find the class.
                long t1 = System.nanoTime();
                c = findClass(name);

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

class TestMyClassLoader {


    public static void main(String[] args) throws Exception {
        // 初始化时候会线初始化父类加载器 ClassLoader， parent 属性默认为 AppClassLoader
        // protected ClassLoader() {
        //        this(checkCreateClassLoader(), getSystemClassLoader());
        //    }
        MyClassLoader myClassLoader = new MyClassLoader("E:/summary/code/tech-summary/JVM/custom");
        System.out.println("====================> check parent ClassLoader name");
        System.out.println(myClassLoader.getParent().getClass().getName());

        // 要加载的class 文件不要放在 classpath 下，否则会被AppClassLoader 加载
        Class<?> clazz = myClassLoader.loadClass("com.jackdaw.jvm.classLoador.Product");

        System.out.println("====================> check class field and ClassLoader name");
        System.out.println(clazz.getDeclaredField("name"));
        System.out.println(clazz.getClassLoader().getClass().getName());

    }
}


class checkCustomLoadClass {

    public static void main(String[] args) throws Exception {
//        checkString();
        checkStudent();


    }

    public static void checkString() throws Exception {

        MyClassLoader myClassLoader = new MyClassLoader("E:/summary/code/tech-summary/JVM/custom");
        System.out.println("====================> load  java.lang.String");
        Class<?> clazz2 = myClassLoader.loadClass("java.lang.String");
        System.out.println(clazz2.getDeclaredField("name"));
        System.out.println(clazz2.getClassLoader().getClass().getName());

        // 虽然重写了 loadClass() 方法，但是 defineClass() 方法 会调用 preDefineClass() 方法，触发沙箱保护机制，保护核心类不被篡改
        //Exception in thread "main" java.lang.SecurityException: Prohibited package name: java.lang
        //	at java.lang.ClassLoader.preDefineClass(ClassLoader.java:655)
        //	at java.lang.ClassLoader.defineClass(ClassLoader.java:754)
        //	at java.lang.ClassLoader.defineClass(ClassLoader.java:635)
    }

    /**
     * 自定义类加载器加载 Student 类
     * 因为全盘负责委托机制，此时 loadClass() 方法 要把java 核心类委托给父类加载器加载，否则报错 Object 找不到路径，
     * @throws Exception
     */
    public static void checkStudent() throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader("E:/summary/code/tech-summary/JVM/src");

        System.out.println("====================> load  com.jackdaw.jvm.classLoador.Student");
        Class<?> clazz = myClassLoader.loadClass("com.jackdaw.jvm.classLoador.Student");
        Object obj = clazz.newInstance();
        Method method= clazz.getDeclaredMethod("show", null);
        method.invoke(obj, null);
        System.out.println(clazz.getClassLoader().getClass().getName());
    }
}