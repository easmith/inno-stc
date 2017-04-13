package com.company.library.models;

import org.mockito.internal.util.reflection.Fields;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by eku on 05.04.17.
 */
public abstract class Model implements Cloneable, Externalizable, ModelInterface {
    @Override
    public Model clone() throws CloneNotSupportedException {
        return (Model) super.clone();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF("Evgeny Kuznetsov");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        in.readUTF();
    }

    public Element getFields(Document doc) {

        Element fieldsElem = null;

        try {
            fieldsElem = doc.createElement("Fields");
            for (Field field :
                    getClass().getDeclaredFields()) {
                Element fieldElem = doc.createElement(field.getName());

                field.setAccessible(true);
                if (field.getType().getName().equals("com.company.library.models.Book")) {
                    Method methodFields = field.get(this).getClass().getMethod("getFields", Document.class);
                    fieldElem.appendChild((Element)methodFields.invoke(field.get(this), doc));
                } if (field.getType().getName().equals("com.company.library.models.BookInstance")) {
                    Method methodFields = field.get(this).getClass().getMethod("getFields", Document.class);
                    fieldElem.appendChild((Element)methodFields.invoke(field.get(this), doc));
                } if (field.getType().getName().equals("com.company.library.models.Booking")) {
                    Method methodFields = field.get(this).getClass().getMethod("getFields", Document.class);
                    fieldElem.appendChild((Element) methodFields.invoke(field.get(this), doc));
                } else {
                    if (field.get(this) != null) {
                        fieldElem.appendChild(doc.createTextNode(field.get(this).toString()));
                    } else {
                        fieldElem.appendChild(doc.createTextNode("null"));
                    }
                }

                fieldsElem.appendChild(fieldElem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fieldsElem;
    }

    public Element getMethods(Document doc) {
        Element methodsElem = null;
        try {
            methodsElem = doc.createElement("Methods");

            for (Method method :
                    getClass().getMethods()) {
                Element methodElem = doc.createElement("Method");

                Attr attrName = doc.createAttribute("name");
                attrName.setValue(method.getName());
                methodElem.setAttributeNode(attrName);

                Attr attrAccess = doc.createAttribute("modifiers");
                attrAccess.setValue(Modifier.toString(method.getModifiers()));
                methodElem.setAttributeNode(attrAccess);
                methodsElem.appendChild(methodElem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return methodsElem;
    }
}
