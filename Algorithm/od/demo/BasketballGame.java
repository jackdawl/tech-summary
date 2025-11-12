package demo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class BasketballGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] inputs = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        int[] outputs = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        LinkedList<Integer> queue = new LinkedList<>();

        StringBuilder res = new StringBuilder();

        int index = 0;

        for (int i : inputs) {
            queue.addLast(i);

            while (queue.size() >0) {
                int first = queue.getFirst();
                int last = queue.getLast();

                if (outputs[index] == first) {
                    queue.removeFirst();
                    res.append("L");
                    index++;
                } else if (last == outputs[index]) {
                    queue.removeLast();
                    res.append("R");
                    index++;

                }else {
                    break;
                }


            }

        }

        if (queue.size() != 0) {
            System.out.println("NO");
        }else {
            System.out.println(res);
        }




    }


}
