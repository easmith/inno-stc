
package ru.easmith.jaxbmodels;

import ru.easmith.utils.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;


/**
 * <p>Java class for resultTaskList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="resultTaskList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resultTask" type="{}result_task" maxOccurs="unbounded"/>
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
@XmlType(name = "resultTaskList", propOrder = {
    "resultTask"
})
public class ResultTaskList implements DBInterface {

    @XmlElement(required = true)
    protected List<ResultTask> resultTask;

    /**
     * Gets the value of the resultTask property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resultTask property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResultTask().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResultTask }
     * 
     * 
     */
    public List<ResultTask> getResultTask() {
        if (resultTask == null) {
            resultTask = new ArrayList<ResultTask>();
        }
        return this.resultTask;
    }

    @Override
    public boolean toDB() {
//        boolean result = true;
//        for (ResultTask resultTask :
//                this.getResultTask()) {
//            result = result & resultTask.toDB();
//        }
//        return result;

        Object[][] objects = new Object[this.getResultTask().size()][];
        int i = 0;
        for (ResultTask resultTask :
                this.getResultTask()) {
            objects[i++] = new Object[]{resultTask.getId(), resultTask.getResultId(), resultTask.getTaskId(), resultTask.getAnswers()};
        }

        DatabaseManager.getInstance().insertBatch("INSERT INTO result_tasks (id, result_id, task_id, answers) VALUE (?, ?, ?, ?) " +
        "ON DUPLICATE KEY UPDATE answers = VALUES(answers)", objects);

        return true;
    }

    @Override
    public boolean fromDB() {
        DatabaseManager dbm = DatabaseManager.getInstance();
        ResultSet resultSet = dbm.executeQuery("select * from result_tasks");
        try {
            while(resultSet.next()) {
                ResultTask resultTask = new ResultTask();
                resultTask.setId(resultSet.getInt("id"));
                resultTask.setTaskId(resultSet.getInt("task_id"));
                resultTask.setResultId(resultSet.getInt("result_id"));
                resultTask.setAnswers(resultSet.getString("answers"));
                resultTask.fromDB();
                this.getResultTask().add(resultTask);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public String[] getDepends() {
        return new String[] {CategoryList.class.getName(), ResultList.class.getName()};
    }
}
