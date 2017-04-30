package Models.pojo;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by eku on 13.04.17.
 */
public class Result {

    private int id;
    private int categoryId;
    private int userId;
    private Timestamp startAt;
    private Timestamp stopAt;

    public Result(int id, int categoryId, int userId, Timestamp startAt, Timestamp stopAt) {
        this.id = id;
        this.categoryId = categoryId;
        this.userId = userId;
        this.startAt = startAt;
        this.stopAt = stopAt;
    }

    public Timestamp getStartAt() {
        return startAt;
    }

    public void setStartAt(Timestamp startAt) {
        this.startAt = startAt;
    }

    public Timestamp getStopAt() {
        return stopAt;
    }

    public void setStopAt(Timestamp stopAt) {
        this.stopAt = stopAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
