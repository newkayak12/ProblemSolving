package programmers.example;

import java.util.Objects;

public class DFS {
    Node root;
    public static void main(String[] args) {
        DFS tree = new DFS();
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
        if(Objects.isNull( element )) return;
        else {
            scan(element.lt);
            scan(element.rt);
            System.out.print(element.data + " → ");
        }
    }
}
