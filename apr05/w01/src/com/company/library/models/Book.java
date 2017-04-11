package com.company.library.models;

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
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Created by eku on 05.04.17.
 */
public class Book extends Model {

    private static long serialVersionUID = 2L;

    public String author;
    private String title;
    private int year;
    private String isbn;

    public Book() {
        this.author = null;
        this.title = null;
        this.year = 0;
        this.isbn = null;
    }

    public Book(String author, String title, int year, String isbn) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Book)) {
            return false;
        }
        if (!this.isbn.equals(((Book) obj).isbn)) {
            return false;
        }

        return true;
    }

    public String toXML () {
        String xml = "";
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // root element
            Element rootElement = doc.createElement("Book");
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

    public void printClassInfo() {
        System.out.println(Book.class.getCanonicalName());

        for (Method met :
                getClass().getMethods()) {
            System.out.println(met.getName());
            System.out.println(met.getReturnType().getCanonicalName());
            for (Parameter parameter:
                 met.getParameters()) {
                System.out.println("\t" + parameter.getName() + " " + parameter.getType().getName());
            }
            System.out.println(met.getModifiers());
        }

        try {
            for (Field field :
                    Class.forName("com.company.library.models.Book").getFields()) {
                System.out.println(field.getName());
                System.out.println(field.getType().getCanonicalName());
                System.out.println(field.isAccessible());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return author + ' ' + title;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(this.author);
        out.writeUTF(this.title);
        out.writeInt(this.year);
        out.writeUTF(this.isbn);
        super.writeExternal(out);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.author = (String) in.readUTF();
        this.title = (String) in.readUTF();
        this.year = in.readInt();
        this.isbn = (String) in.readUTF();
        super.readExternal(in);
    }
}
