package demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RangeCombine {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] ranges = new int[n][2];

        for (int i = 0; i < n; i++) {
            ranges[i][0] = sc.nextInt();
            ranges[i][1] = sc.nextInt();
        }

        Arrays.sort(ranges, (a, b) -> a[0] - b[0]);
        List<int[]> combines = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int s1 = ranges[i][0];
            int e1 = ranges[i][1];

            for (int j = i + 1; j < n; j++) {

                int s2 = ranges[j][0];
                int e2 = ranges[j][1];

                if (s2 <= e1) {
                    combines.add(new int[]{s2, Math.min(e1, e2)});
                } else {
                    break;
                }

            }

        }

        if (combines.isEmpty()) {
            System.out.println("None");
            return;
        }

        combines.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);

        int[] pre = combines.get(0);
        for (int i = 1; i < combines.size(); i++) {
            int[] arr = combines.get(i);
            if (arr[0] <= pre[1]) {

                pre[1] = Math.max(pre[1], arr[1]);
            } else {
                System.out.println(pre[0] + " " + pre[1]);
                pre = arr;
            }
        }

        System.out.println(pre[0] + " " + pre[1]);

    }


}
