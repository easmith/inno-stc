package Models.pojo;

import javax.persistence.*;
import java.util.List;

/**
 * Created by eku on 13.04.17.
 */

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    private String text;
    @Column(name = "category_id")
    private int categoryId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "questionId", cascade = CascadeType.ALL)
    private List<Answer> answers;

    public Question() {
    }

    public Question(int id, String type, String text, int categoryId) {
        this.id = id;
        this.type = type;
        this.text = text;
        this.categoryId = categoryId;
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

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
