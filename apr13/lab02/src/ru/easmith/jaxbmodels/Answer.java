
package ru.easmith.jaxbmodels;

import ru.easmith.DatabaseManager;
import ru.easmith.DBInterface;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for answer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="answer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="task_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="is_correct" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "answer", propOrder = {
    "id",
    "taskId",
    "text",
    "isCorrect"
})
public class Answer implements DBInterface {

    protected int id;
    @XmlElement(name = "task_id")
    protected int taskId;
    @XmlElement(required = true)
    protected String text;
    @XmlElement(name = "is_correct")
    protected boolean isCorrect;

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the taskId property.
     * 
     */
    public int getTaskId() {
        return taskId;
    }

    /**
     * Sets the value of the taskId property.
     * 
     */
    public void setTaskId(int value) {
        this.taskId = value;
    }

    /**
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText(String value) {
        this.text = value;
    }

    /**
     * Gets the value of the isCorrect property.
     * 
     */
    public boolean isIsCorrect() {
        return isCorrect;
    }

    /**
     * Sets the value of the isCorrect property.
     * 
     */
    public void setIsCorrect(boolean value) {
        this.isCorrect = value;
    }

    @Override
    public boolean toDB() {
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.execute("INSERT INTO answers (id, task_id, text, is_correct) VALUE (?, ?, ?, ?) " +
                        "ON DUPLICATE KEY UPDATE text = VALUES(text)",
                this.id, this.taskId, this.text, this.isCorrect);
        return true;
    }

    @Override
    public boolean fromDB() {
        return true;
    }
}
