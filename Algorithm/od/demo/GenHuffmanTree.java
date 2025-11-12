package demo;

import java.util.*;

public class GenHuffmanTree {
    static class Node{
        int value;
        Node left;
        Node right;
        int height;

        public Node(int value){
            this.value = value;
            left = null;
            right = null;
            height = 0;
        }
    }

    static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node n1, Node n2) {
            if (n1.value > n2.value) return 1;
            if (n1.value < n2.value) return -1;
            if (n1.height > n2.height) return 1;
            if (n1.height < n2.height) return -1;
            return 0;
        }


    }


    public static Node buildHuffmanTree(List<Integer> list) {

        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());
        for (int v : list) {
            pq.add(new Node(v));
        }

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();

            Node parent = new Node(left.value + right.value);

            if (left.value > right.value || (left.value == right.value && left.height > right.height)) {
                Node temp = left;
                left = right;
                right = temp;
            }

            parent.left = left;
            parent.right = right;
            parent.height = Math.max(left.height, right.height) + 1;


            pq.add(parent);


        }
        return pq.peek();
    }


    public static void inOrderTree(Node root, StringBuilder res) {

        if (root != null) {
            inOrderTree(root.left, res);
            res.append(root.value).append(" ");
            inOrderTree(root.right, res);
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n;i++) {
            list.add(sc.nextInt());
        }

        Node root = buildHuffmanTree(list);
        StringBuilder res = new StringBuilder();

        inOrderTree(root, res);

        System.out.println(res);


    }









}
