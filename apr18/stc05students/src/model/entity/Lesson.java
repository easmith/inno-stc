package model.entity;

import java.sql.Timestamp;

/**
 * Created by Olesya on 18.04.2017.
 */
public class Lesson {
    private long id;
    private Long groupId;
    private Group group;
    private Timestamp lessonDate;
    private int room;
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Timestamp getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(Timestamp lessonDate) {
        this.lessonDate = lessonDate;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", group=" + group +
                ", lessonDate=" + lessonDate +
                ", room=" + room +
                ", description='" + description + '\'' +
                '}';
    }
}
