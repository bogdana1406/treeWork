import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, TransformerException, ParserConfigurationException {
        Node<String> root = createTree();

        createXMLfromTree(root);
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
    public static void printTree(Node<String> node, String appender) {
        System.out.println(appender + node.getData());
        node.getChildren().forEach(each ->  printTree(each, appender + appender));
    }

    public static void createXMLfromTree(Node<String> node) throws ParserConfigurationException, FileNotFoundException, TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();
        Element root = document.createElement(node.getData());

        document.appendChild(root);


        DOMSource source = new DOMSource(document);
        String outputURL = "node_edit.xml";
        StreamResult result = new StreamResult(new FileOutputStream(outputURL));
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(source, result);
    }
}
