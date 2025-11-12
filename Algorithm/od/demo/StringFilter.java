package demo;

import java.util.HashSet;
import java.util.Scanner;

public class StringFilter {

    static String[] map = {"abc", "def", "ghi", "jkl", "mno", "pqr", "st", "uv", "wx", "yz"};
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        char[] nums = sc.next().toCharArray();
        String filter = sc.next();

        String[] letters = new String[nums.length];
        for (int i =0;i < nums.length;i++) {
            letters[i] = map[nums[i] - '0'];
        }

        StringBuilder res = new StringBuilder();

        doFind(letters, 0, new StringBuilder(), res, new HashSet<>(), filter);

        System.out.println(res);


    }

    public static void doFind(String[] letters, int index, StringBuilder item, StringBuilder res, HashSet<Character> used,String filter ){

        if (index == letters.length) {
            if (!item.toString().contains(filter)) {
                res.append(item).append(",");
            }
            return;
        }

        for (int i = 0; i < letters[index].length();i++) {
            char c = letters[index].charAt(i);
            if (!used.contains(c)){
                item.append(c);
                used.add(c);
                doFind(letters, index+1,item,res, used, filter);
                item.deleteCharAt(item.length()-1);
                used.remove(c);
            }
        }

    }


}
