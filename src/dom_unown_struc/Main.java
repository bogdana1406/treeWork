package dom_unown_struc;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document document = db.parse("D:\\testWork\\src\\dom_unown_struc\\books.xml");
        document.getDocumentElement().normalize();

        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());

        //получили список элементов с тегом "book".
        NodeList book = document.getElementsByTagName("book");
        System.out.println("==============");

        // список "book" передали в функцию visitChildNodes
        visitChildNodes(book);
    }

    // принимает список узлов "book"
    private static void visitChildNodes(NodeList book) {
        // перебираем все узлы "book"
        for (int i = 0; i < book.getLength(); i++) {
            // получаем переменую node типа Node
            Node node = book.item(i);
            // если тип переменной node Node.ELEMENT_NODE
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                // печатаем название узла
                System.out.println("Node name " + node.getNodeName() + "; Value = " + node.getTextContent());
                if (node.hasAttributes()) {
                    NamedNodeMap attributes = node.getAttributes();

                    for (int temp = 0; temp < attributes.getLength(); temp++) {
                        Node tempNode = attributes.item(temp);
                        System.out.println("Attr name: " + tempNode.getNodeName() + "; Value = " + tempNode.getNodeValue());
                    }
                    if (node.hasChildNodes()) {
                        visitChildNodes(node.getChildNodes());
                    }
                }
            }
        }
    }

}
