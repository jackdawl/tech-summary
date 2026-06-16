package com.jackdaw.jvm.memory;

/**
 * @author jackdaw
 * @description 栈内存溢出示例， 设置 -Xss128k，默认1M
 */
public class StackOverflowDemo {

    //记录递归次数
    static int count = 0;
    static void recur() {
        count++;
        recur();
    }

    public static void main(String[] args) {
        try {
            recur();

        } catch (Throwable t){
            t.printStackTrace();
            System.out.println(count);
        }
    }


}
