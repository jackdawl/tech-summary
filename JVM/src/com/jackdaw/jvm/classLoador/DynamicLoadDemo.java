package com.jackdaw.jvm.classLoador;

/**
 * @author jackdaw
 * @description
 */
public class DynamicLoadDemo {

    static {
        System.out.println("====================> load DynamicLoadDemo");
    }


    public static void main(String[] args) {

        Cat cat = new Cat();
        System.out.println("====================> load test");
        Dog dog = null; // 没有调用 new  Dog(), 不会加载Dog 类

    }

}

class Cat {
    static {
        System.out.println("====================> load Cat");
    }

    public Cat() {
        System.out.println("====================> new Cat");
    }
}

class Dog {
    static {
        System.out.println("====================> load Dog");
    }

    public Dog() {
        System.out.println("====================> new Dog");
    }
}