package models.hbn;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by eku on 09.05.17.
 */
@Entity
@Table(name = "lessons", schema = "students", catalog = "")
public class LessonsEntity {
    private int id;
    private Date lessonDate;
    private int room;
    private String description;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "lesson_date")
    public Date getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(Date lessonDate) {
        this.lessonDate = lessonDate;
    }

    @Basic
    @Column(name = "room")
    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LessonsEntity that = (LessonsEntity) o;

        if (id != that.id) return false;
        if (room != that.room) return false;
        if (lessonDate != null ? !lessonDate.equals(that.lessonDate) : that.lessonDate != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (lessonDate != null ? lessonDate.hashCode() : 0);
        result = 31 * result + room;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
