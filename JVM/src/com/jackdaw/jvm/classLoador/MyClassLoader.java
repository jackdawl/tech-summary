package com.jackdaw.jvm.classLoador;

import java.io.FileInputStream;

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

