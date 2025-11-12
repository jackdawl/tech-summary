package demo;

import java.util.Arrays;
import java.util.Scanner;

public class HoseMatch {

    static int[] a;
    static int[] b;
    static int maxBigCnt = 0;
    static int res = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        a = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        b = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        doFind(0, new boolean[a.length], 0);

        System.out.println(res);

    }


    public static void doFind(int len, boolean[] used, int bigCnt) {
        if (len >= a.length) {
            if (bigCnt > maxBigCnt) {
                maxBigCnt = bigCnt;
                res = 1;
            }else if (bigCnt == maxBigCnt){
                res++;
            }

            return;
        }

        for (int i = 0;i< a.length;i++) {
            if (used[i]) continue;

            used[i] = true;
            int big = a[i] > b[len] ? 1 : 0;
            doFind(len + 1, used, bigCnt + big);
            used[i] = false;


        }

    }

}
