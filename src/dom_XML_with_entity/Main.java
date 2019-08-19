package dom_XML_with_entity;

import dom_XML_with_entity.entity.Book;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class Main {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        BookHelper bh = new BookHelper();

        Book book = new Book();
        book.setId("05");
        book.setIsbn(234234);
        book.setAuthor("Pushkin");
        book.setTitle("Lukimorie");

        System.out.println("===============");

        List<Book> books = bh.getAll();
        books.add(book);
        for (Book b: books) {
            System.out.println(b.getId() + " " + b.getIsbn() + " " + b.getTitle() + " " + b.getAuthor());
        }

    }
}
