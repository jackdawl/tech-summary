package demo;

import java.util.Scanner;

public class MaxProfit {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();

        int days = sc.nextInt();


        int[] items = new int[number];
        for (int i = 0 ; i < number;i++) {

            items[i] = sc.nextInt();

        }

        int[][] prices  = new int[number][days];
        int maxProfit = 0;

        for (int i=0;i<number;i++) {
            for (int j=0;j<days;j++){
                prices[i][j] = sc.nextInt();
                if (j > 0) {
                    int profit = Math.max(0, prices[i][j] - prices[i][j-1]);
                    maxProfit += profit * items[i];
                }
            }
        }

//        int maxProfit = 0;
//        for (int i = 0;i < number; i++) {
//
//            for (int j = 1;j < days;j++) {
//                int profit = Math.min(0, prices[i][j] - prices[i][j-1]);
//                maxProfit += profit * items[i];
//            }
//
//        }


        System.out.println(maxProfit);
    }


}
