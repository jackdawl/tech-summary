package demo;

import javax.swing.text.AbstractDocument;
import java.util.Scanner;

public class SubCover {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s1 = sc.nextLine();
        String s2 = sc.nextLine();

        int k = sc.nextInt();


        System.out.println(findLeftIndex(s1, s2, k));


    }

    public static int findLeftIndex(String s1, String s2, int k){
        int[] s1CharsCnt = new int[26];
        for (int i = 0; i< s1.length();i++) {
            char c = s1.charAt(i);
            s1CharsCnt[c-'a']++;
        }

        int left = 0, right = 0;
        int need2Match = s1.length();
        int[] windowCnt = new int[26];

        while (right < s2.length()) {
            char c2 = s2.charAt(right);
            windowCnt[c2-'a']++;

            if (windowCnt[c2-'a'] <= s1CharsCnt[c2-'a']) {
                need2Match--;
            }

            if (right -left +1 > s1.length() + k) {
                char l = s2.charAt(left);

                if (windowCnt[l - 'a'] <= s1CharsCnt[l - 'a']) {
                    need2Match++;
                }
                windowCnt[l -'a']--;
                left++;


            }
            if (need2Match == 0) {
                return  left;
            }
            right++;

        }




        return -1;


    }


}
