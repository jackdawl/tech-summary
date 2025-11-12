package demo;

import java.util.Scanner;

public class EmployeeDispatch {

    static long x;
    static long y;
    static long cntx;
    static long cnty;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        x = sc.nextLong();
        y = sc.nextLong();
        cntx = sc.nextLong();
        cnty = sc.nextLong();

        long min = cntx + cnty;
        long max = 1000000000L;


        while (min <= max) {

            long mid  = min + (max - min) / 2;

            if (checkNum(mid)) {
                max = mid -1;
            } else {
                min = mid + 1;
            }

        }

        System.out.println(min);


    }


    public static boolean checkNum(long k) {
        long a = k / x;
        long b = k / y;
        long c = k / (x * y);

        return Math.max(0, cntx - (b - c)) + Math.max(0, cnty - (a - c)) <= k - a - b + c;

    }


}
