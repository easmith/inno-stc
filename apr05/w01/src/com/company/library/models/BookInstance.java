package com.company.library.models;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.lang.reflect.Field;
import java.util.UUID;

/**
 * Created by eku on 05.04.17.
 */
public class BookInstance extends Model {
    private Book book;
    private UUID number;

    public BookInstance(Book book, UUID number) {
        this.book = book;
        this.number = number;

    }

    public BookInstance() {
        this.book = null;
        this.number = null;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public int hashCode() {
        return number.hashCode() * 32;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof Book))
            return false;

        if (!this.number.equals(((BookInstance) obj).number)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "[" + book + "] #" + number;
    }

    public String toXML() {
        String xml = "";
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // root element
            Element rootElement = doc.createElement("BookInstance");
            doc.appendChild(rootElement);

            Element fieldsElem = this.getFields(doc);
            rootElement.appendChild(fieldsElem);

            Element methodsElem = this.getMethods(doc);
            rootElement.appendChild(methodsElem);

            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(domSource, result);
            writer.flush();

            xml = writer.toString();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return xml;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.book);
        out.writeObject(this.number);
        super.writeExternal(out);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.book = (Book) in.readObject();
        this.number = (UUID) in.readObject();
        super.readExternal(in);
    }
}
