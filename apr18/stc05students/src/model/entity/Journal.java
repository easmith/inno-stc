package model.entity;

/**
 * Created by Olesya on 18.04.2017.
 */
public class Journal {
    private long id;
    private Long lessonId;
    private Lesson lesson;
    private Long studentId;
    private Student student;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "id=" + id +
                ", lessonId=" + lessonId +
                ", lesson=" + lesson +
                ", studentId=" + studentId +
                ", student=" + student +
                '}';
    }
}
