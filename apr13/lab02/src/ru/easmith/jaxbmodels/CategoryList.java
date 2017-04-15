
package ru.easmith.jaxbmodels;

import ru.easmith.DBInterface;
import ru.easmith.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;


/**
 * <p>Java class for categoryList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="categoryList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="category" type="{}category" maxOccurs="unbounded"/>
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
@XmlType(name = "categoryList", propOrder = {
    "category"
})
public class CategoryList implements DBInterface {

    @XmlElement(required = true)
    protected List<Category> category;

    /**
     * Gets the value of the category property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the category property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Category }
     * 
     * 
     */
    public List<Category> getCategory() {
        if (category == null) {
            category = new ArrayList<Category>();
        }
        return this.category;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Category category :
                this.getCategory()) {
            stringBuilder.append(category.getName()).append("\n");
            for (Task task :
                    category.getTasks()) {
                stringBuilder.append("\t").append(task.getText()).append("\n");
                for (Answer answer :
                        task.getAnswers()) {
                    stringBuilder.append("\t\t").append(answer.getId()).append(answer.getText()).append(" [" + answer.isIsCorrect() + "]").append("\n");
                }
            }
        }

        return stringBuilder.toString();
    }

    public boolean toDB() {
        boolean result = true;
        for (Category category :
                this.getCategory()) {
            result = result & category.toDB();
        }
        return result;
    }

    @Override
    public boolean fromDB() {
        DatabaseManager dbm = DatabaseManager.getInstance();
        ResultSet resultSet = dbm.executeQuery("select * from categories");
        try {
            while(resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                category.fromDB();
                this.getCategory().add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}
