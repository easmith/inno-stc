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

/**
 * Created by eku on 05.04.17.
 */
public class Reader extends Model {
    private String firstName;
    private String secondName;
    private String lastName;
    private long passportNumber;
    public Reader(String firstName, String secondName, String lastName, long passportNumber) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
    }

    public Reader() {
        this.firstName = null;
        this.secondName = null;
        this.lastName = null;
        this.passportNumber = 0;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(long passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public int hashCode() {
        return (int) (passportNumber * 32);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof Reader))
            return false;

        if (passportNumber != ((Reader) obj).passportNumber)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(this.passportNumber);
        out.writeUTF(this.firstName);
        out.writeUTF(this.secondName);
        out.writeUTF(this.lastName);
        super.writeExternal(out);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.passportNumber = in.readLong();
        this.firstName = in.readUTF();
        this.secondName = in.readUTF();
        this.lastName = in.readUTF();
        super.readExternal(in);
    }

    public String toXML () {
        String xml = "";
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // root element
            Element rootElement = doc.createElement("Reader");
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
}
