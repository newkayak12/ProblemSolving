package programmers.example;

import java.util.LinkedList;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

public class BFS {
    Node root;

    public static void main(String[] args) {
        BFS tree = new BFS();
        tree.root = new Node(0);
        tree.root.lt = new Node(1);
        tree.root.rt = new Node(2);
        tree.root.lt.lt = new Node(3);
        tree.root.lt.rt = new Node(4);
        tree.root.rt.lt = new Node(5);
        tree.root.rt.rt = new Node(6);

        tree.scan(tree.root);
    }

    public void scan(Node element) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(element);
        int L = 0;


        while (!queue.isEmpty()) {
            int len = queue.size();
            System.out.print(L + " : ");

            for ( int i = 0; i < len; ++i) {
                Node current = queue.poll();
                System.out.print(current.data + " ");
                if(Objects.nonNull(current.lt)) queue.offer(current.lt);
                if(Objects.nonNull(current.rt)) queue.offer(current.rt);
            }

            L ++;
            System.out.println();
        }
    }
}
