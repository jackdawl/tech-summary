package demo;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrMatchFunc {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String source = sc.nextLine();
        String target = sc.nextLine();;

        target = target.replaceAll("[(.*?)]", "[$1]");

        Pattern pattern = Pattern.compile(target);

        Matcher matcher = pattern.matcher(source);
        if (matcher.find()) {
            System.out.println(matcher.start());
        }else {
            System.out.println("-1");
        }

    }


}
