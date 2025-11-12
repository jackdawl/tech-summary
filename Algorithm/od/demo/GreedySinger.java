package demo;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class GreedySinger {
//10 2
//1 1 2
//120 20
//90 10

    static int t;
    static int n;

    static int[][] mds;

    static int roadCost;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        t = sc.nextInt();
        n = sc.nextInt();


        for (int i = 0;i<n+1;i++) {
            roadCost += sc.nextInt();;
        }

        mds = new int[n][2];
        for (int i = 0; i < n; i++) {
            mds[i][0] = sc.nextInt();
            mds[i][1] = sc.nextInt();
        }

        System.out.println(getResult());

    }


    public static int getResult() {
        int remainDays = t - roadCost;
        if (remainDays <= 0) return 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n;i++) {
            int m = mds[i][0];

            int d = mds[i][1];

            while (m>0) {
                if (pq.size() >= remainDays) {
                    if (m <= pq.peek()) break;
                    pq.poll();

                }
                pq.add(m);
                m -= d;

            }

        }
        return pq.stream().reduce(Integer::sum).orElse(0);

    }



}
