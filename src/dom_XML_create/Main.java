package dom_XML_create;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    public static void main(String[] args) throws ParserConfigurationException, FileNotFoundException, TransformerException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();
        Element root = document.createElement("books");
        Element book = document.createElement("book");
        Element title = document.createElement("title");
        Element author = document.createElement("author");

        book.setAttribute("id", "05");
        book.setAttribute("isbn", "333555");
        title.setTextContent("Lucomorie");
        author.setTextContent("Pushkin");
        book.appendChild(title);
        root.appendChild(book);
        document.appendChild(root);

        DOMSource source = new DOMSource(document);
        String outputURL = "book_edit.xml";
        StreamResult result = new StreamResult(new FileOutputStream(outputURL));
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(source, result);
    }
}
