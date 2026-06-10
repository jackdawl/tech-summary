package com.jackdaw.jvm.classLoador;

import com.sun.crypto.provider.DESKeyFactory;

/**
 * @author jackdaw
 * @description
 */
public class CheckClassLoaders {

    public static void main(String[] args) {


        System.out.println("====================> check ClassLoader");
        System.out.println(String.class.getClassLoader());
        System.out.println(DESKeyFactory.class.getClassLoader());
        System.out.println(CheckClassLoaders.class.getClassLoader());

        System.out.println("====================> check parent ClassLoader");
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader sysParent = systemClassLoader.getParent();
        ClassLoader sysGrandParent = sysParent.getParent();
        System.out.println("systemClassLoader is " + systemClassLoader);
        System.out.println(systemClassLoader + " parent  is " + sysParent);
        System.out.println(sysParent + " parent  is " + sysGrandParent);

        // 启动类加载器 是由C++ 实现的，所以 String 类的类加载器是 null，ExtClassLoader 的父加载器是 null


    }



}
