package demo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class StringDecode {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        int diffCount = diffChars(str2);

        String[] arr = str1.split("[0-9a-f]+");

        String[] arr2 = Arrays.stream(arr).filter(val -> !"".equals(val) && diffChars(val) <= diffCount).toArray(String[]::new);

        if (arr2.length == 0) {
            System.out.println("Not Found");
        }

        Arrays.sort(arr2, (a, b) -> {
            int cnt1 = diffChars(a);
            int cnt2 = diffChars(b);

            return cnt1 != cnt2 ? cnt2 - cnt1 : b.compareTo(a);
        });

        System.out.println(arr2[0]);


    }


    public static int diffChars(String str) {
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            set.add(str.charAt(i));
        }

        return set.size();
    }

}
