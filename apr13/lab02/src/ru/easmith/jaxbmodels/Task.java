
package ru.easmith.jaxbmodels;

import ru.easmith.utils.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for task complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="task">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="categoryId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="answers" type="{}answer" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "task", propOrder = {
    "id",
    "text",
    "categoryId",
    "answers"
})
public class Task implements DBInterface {

    protected int id;
    @XmlElement(required = true)
    protected String text;
    protected int categoryId;
    @XmlElement(required = true)
    protected List<Answer> answers;

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
     * Gets the value of the categoryId property.
     * 
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the value of the categoryId property.
     * 
     */
    public void setCategoryId(int value) {
        this.categoryId = value;
    }

    /**
     * Gets the value of the answers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the answers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnswers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Answer }
     * 
     * 
     */
    public List<Answer> getAnswers() {
        if (answers == null) {
            answers = new ArrayList<Answer>();
        }
        return this.answers;
    }

    @Override
    public boolean toDB() {
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.execute("INSERT INTO tasks (id, category_id, text) VALUE (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE text = VALUES(text)",
                this.id, this.categoryId, this.text);
        boolean result = true;
        for (Answer answer :
                this.getAnswers()) {
            result = result & answer.toDB();
        }
        return result;
    }

    @Override
    public boolean fromDB() {
        DatabaseManager dbm = DatabaseManager.getInstance();
        ResultSet resultSet = dbm.executeQuery("select * from answers where task_id = ?", this.getId());
        try {
            while(resultSet.next()) {
                Answer answer = new Answer();
                answer.setId(resultSet.getInt("id"));
                answer.setTaskId(resultSet.getInt("task_id"));
                answer.setText(resultSet.getString("text"));
                answer.setIsCorrect(resultSet.getBoolean("is_correct"));
                this.getAnswers().add(answer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public String[] getDepends() {
        return null;
    }
}
