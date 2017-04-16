
package ru.easmith.jaxbmodels;

import ru.easmith.DBInterface;
import ru.easmith.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;


/**
 * <p>Java class for resultList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="resultList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{}result" maxOccurs="unbounded"/>
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
@XmlType(name = "resultList", propOrder = {
    "result"
})
public class ResultList implements DBInterface {

    @XmlElement(required = true)
    protected List<Result> result;

    /**
     * Gets the value of the result property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the result property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Result }
     * 
     * 
     */
    public List<Result> getResult() {
        if (result == null) {
            result = new ArrayList<Result>();
        }
        return this.result;
    }

    @Override
    public boolean toDB() {
        boolean res = true;
        for (Result result :
                this.getResult()) {
            res = res & result.toDB();
        }
        return res;
    }

    @Override
    public boolean fromDB() {
        DatabaseManager dbm = DatabaseManager.getInstance();
        ResultSet resultSet = dbm.executeQuery("select * from results");
        try {
            while(resultSet.next()) {
                Result result = new Result();
                result.setId(resultSet.getInt("id"));
                result.setUserId(resultSet.getInt("user_id"));
                result.setCategoryId(resultSet.getInt("category_id"));
                result.fromDB();
                this.getResult().add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public String[] getDepends() {
        return new String[]{UserList.class.getName(), CategoryList.class.getName()};
    }
}
