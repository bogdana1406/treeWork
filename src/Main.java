import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Node<String> root = createTree();
        List<List<String>> lists = root.levelOrder(root);
        for (List<String> s: lists) {
            System.out.println(s);
        }
//        printTree(root, " ");

        String searchQuery = "node 111";
        Optional<Node<String>> findDataInTree = root.findDataInTree(root, searchQuery);
        System.out.println("Node with data \""+ searchQuery +"\" found :" +findDataInTree.isPresent());
    }

    public static Node<String> createTree() {
        Node<String> root = new Node<>("root");

        Node<String> node1 = root.addChild(new Node<String>("node 1"));

        Node<String> node11 = node1.addChild(new Node<String>("node 11"));
        Node<String> node111 = node11.addChild(new Node<String>("node 111"));
        Node<String> node112 = node11.addChild(new Node<String>("node 112"));

        Node<String> node12 = node1.addChild(new Node<String>("node 12"));

        Node<String> node2 = root.addChild(new Node<String>("node 2"));

        Node<String> node21 = node2.addChild(new Node<String>("node 21"));
        Node<String> node211 = node2.addChild(new Node<String>("node 22"));
        return root;
    }
    public static <T> void printTree(Node<T> node, String appender) {
        System.out.println(appender + node.getData());
        node.getChildren().forEach(each ->  printTree(each, appender + appender));
    }
}
