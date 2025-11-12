package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GuessNum {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        List<String[]> guessInfos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            guessInfos.add(new String[]{sc.next(), sc.next()});

        }

        int validCnt = 0;
        String answer = "";

        for (int i = 0; i <= 9999; i++) {
            String ansNum = String.format("%04d", i);

            boolean valid = true;

            for (String[] guessInfo : guessInfos) {
                String guessNum = guessInfo[0];
                String guessRes = guessInfo[1];

                int countA = 0;
                int countB = 0;

                int[] guessArr = new int[10];
                int[] answerArr = new int[10];

                for (int j = 0; j < guessNum.length(); j++) {

                    int an = ansNum.charAt(j) - '0';
                    int gu = guessNum.charAt(j) - '0';

                    if (an == gu) {
                        countA++;
                    } else {
                        guessArr[gu]++;
                        answerArr[an]++;
                    }

                }

                //数字正确，位置不正确
                for (int k = 0; k < 10; k++) {
                    countB += Math.min(answerArr[k], guessArr[k]);
                }

                String realRes = countA + "A" + countB + "B";

                if (!realRes.equals(guessRes)) {
                    valid = false;
                    break;
                }


            }

            if (valid) {
                validCnt++;
                answer = ansNum;
                if (validCnt > 1) {
                    break;
                }
            }

        }

        if (validCnt != 1) {
            System.out.println("NA");
        } else {
            System.out.println(answer);
        }


    }


}
