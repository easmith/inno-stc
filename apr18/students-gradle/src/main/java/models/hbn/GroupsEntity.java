package models.hbn;

import models.POJO.Student;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by eku on 09.05.17.
 */
@Entity
@Table(name = "groups", schema = "students", catalog = "")
public class GroupsEntity {
    private int id;
    private String name;

    private Set<Student> students;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupsEntity that = (GroupsEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
