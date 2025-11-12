package demo;

import java.util.Arrays;
import java.util.Scanner;

public class NewArray {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] n = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int m = sc.nextInt();

        int[] n2 = Arrays.stream(n).filter(value -> value < m).toArray();
        Arrays.sort(n2);

        System.out.println(doCount(n2, 0, 0, n2[0], m, 0));


    }

    public static int doCount(int[] arr, int index, int sum, int min, int m, int count){
        if (sum > m) return count;

        if (sum == m || (sum < m && m - sum < min && m - sum > 0)) {
            return count+1;
        }

        for (int i = index; i < arr.length;i++) {
            count = doCount(arr, i, sum+arr[i], min, m, count);
        }

        return count;

    }



}
