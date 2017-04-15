
package ru.easmith.jaxbmodels;

import ru.easmith.DatabaseManager;
import ru.easmith.DBInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for category complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="category">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tasks" type="{}task" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "category", propOrder = {
    "id",
    "name",
    "tasks"
})
public class Category implements DBInterface {

    protected int id;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected List<Task> tasks;

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
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the tasks property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tasks property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTasks().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Task }
     * 
     * 
     */
    public List<Task> getTasks() {
        if (tasks == null) {
            tasks = new ArrayList<Task>();
        }
        return this.tasks;
    }

    @Override
    public boolean toDB() {
        DatabaseManager dbm = DatabaseManager.getInstance();
        boolean result = dbm.execute("insert into categories (id, name) value (?, ?)", this.id, this.name);

        for (Task task :
                this.getTasks()) {
            result = result && task.toDB();
        }
        return result;
    }

    @Override
    public boolean fromDB() {
        DatabaseManager dbm = DatabaseManager.getInstance();
        ResultSet resultSet = dbm.executeQuery("select * from tasks where category_id = ?", this.getId());
        try {
            while(resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setCategoryId(resultSet.getInt("category_id"));
                task.setText(resultSet.getString("text"));
                task.fromDB();
                this.getTasks().add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
