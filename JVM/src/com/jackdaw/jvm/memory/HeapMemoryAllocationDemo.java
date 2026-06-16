package com.jackdaw.jvm.memory;

import java.util.ArrayList;

/**
 * @author jackdaw
 * @description 堆内存分配演示,打开 jvisualvm 选择该进程，在 Visual GC 观察内存分配动态变化
 */
public class HeapMemoryAllocationDemo {

    byte[] a = new byte[1024 * 100];

    public static void main(String[] args) throws InterruptedException {
        ArrayList<HeapMemoryAllocationDemo> list = new ArrayList<>();
        while (true) {
            list.add(new HeapMemoryAllocationDemo());
            Thread.sleep(10);
        }

    }

}
