package demo;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class AppendStr {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String str = scanner.next();

        int n = scanner.nextInt();


        System.out.println(getResult(str, n));

    }

    static  int count = 0;

    public static int getResult(String str, int n) {
        if (str.length() < n) return 0;

        char[] arr = str.toCharArray();

        for (char c : arr) {
            if (c < 'a' || c > 'z') {
                return 0;
            }
        }

        Arrays.sort(arr);
        doFind(arr, 0, -1, new boolean[str.length()], n);

        return count;

    }

    public static void doFind(char[] cArr, int len, int pre, boolean[] used, int n) {

        if (len == n) {
            count = count+1;
            return;
        }

        for (int i = 0; i < cArr.length; i++) {

            if (used[i]) continue;

            if (pre >= 0 && cArr[i]==cArr[pre] ) continue;

            if (i >0 && cArr[i] == cArr[i-1] && !used[i-1]) continue;

            used[i] = true;
            doFind(cArr, len+1, i, used, n);
            used[i] = false;


        }


    }


}
