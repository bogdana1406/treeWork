package dom_XML;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse("D:\\testWork\\src\\dom_XML\\books.xml");
        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());
        System.out.println(root.getNodeType());

        NodeList list = root.getElementsByTagName("book");

        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            System.out.println("Book name: " + root.getElementsByTagName("title").item(i).getFirstChild().getTextContent());
            System.out.println("Author name: " + root.getElementsByTagName("author").item(i).getFirstChild().getTextContent());
            System.out.println("ID: " + node.getAttributes().item(0).getNodeValue());
            System.out.println("isbn: " + node.getAttributes().item(1).getNodeValue());
        }
    }
}
