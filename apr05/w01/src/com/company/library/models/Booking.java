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
import java.util.Date;

/**
 * Created by eku on 05.04.17.
 */
public class Booking extends Model {
    private BookInstance bookInstance;
    private Reader reader;
    private Date startDate;
    private Date returnDate;
    private Date finishDate;

    public Booking(BookInstance bookInstance, Reader reader, Date startDate, Date returnDate) {
        this.bookInstance = bookInstance;
        this.reader = reader;
        this.startDate = startDate;
        this.returnDate = returnDate;
    }

    public Booking() {
        this.bookInstance = null;
        this.reader = null;
        this.startDate = null;
        this.returnDate = null;
    }

    public String toXML() {
        String xml = "";
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // root element
            Element rootElement = doc.createElement("Booking");
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

    public BookInstance getBookInstance() {
        return bookInstance;
    }

    public Reader getReader() {
        return reader;
    }

    @Override
    public int hashCode() {
        return bookInstance.hashCode() + reader.hashCode() + startDate.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof Booking))
            return false;

        if (!(bookInstance.equals(((Booking) obj).bookInstance)
                && reader.equals(((Booking) obj).reader)
                && startDate.equals(((Booking) obj).startDate))) {
            return false;
        }

        return false;
    }

    @Override
    public String toString() {
        return "[" + reader + "] read [" + bookInstance.getBook() + "]";
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.bookInstance);
        out.writeObject(this.reader);
        out.writeObject(this.startDate);
        out.writeObject(this.finishDate);
        out.writeObject(this.returnDate);
        super.writeExternal(out);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.bookInstance = (BookInstance) in.readObject();
        this.reader = (Reader) in.readObject();
        this.startDate = (Date) in.readObject();
        this.finishDate = (Date) in.readObject();
        this.returnDate = (Date) in.readObject();
        super.readExternal(in);
    }
}
