
package ru.easmith.jaxbmodels;

import ru.easmith.DatabaseManager;
import ru.easmith.DBInterface;

import javax.xml.bind.annotation.*;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * <p>Java class for result_task complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="result_task">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="result_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="task_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="answers" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "result_task", propOrder = {
    "id",
    "resultId",
    "taskId",
    "answers"
})
public class ResultTask implements DBInterface {

    protected int id;
    @XmlElement(name = "result_id")
    protected int resultId;
    @XmlElement(name = "task_id")
    protected int taskId;
    @XmlElement(required = true)
    protected String answers;

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
     * Gets the value of the resultId property.
     * 
     */
    public int getResultId() {
        return resultId;
    }

    /**
     * Sets the value of the resultId property.
     * 
     */
    public void setResultId(int value) {
        this.resultId = value;
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
     * Gets the value of the answers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnswers() {
        return answers;
    }

    /**
     * Sets the value of the answers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnswers(String value) {
        this.answers = value;
    }

    @Override
    public boolean toDB() {
        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.execute("INSERT INTO result_tasks (id, result_id, task_id, answers) VALUE (?, ?, ?, ?) " +
                        "ON DUPLICATE KEY UPDATE answers = VALUES(answers)",
                this.id, this.resultId, this.taskId, this.answers);
        return true;
    }

    @Override
    public boolean fromDB() {
        return true;
    }
}
