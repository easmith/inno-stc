package Models.pojo;

import javax.persistence.*;

/**
 * Created by eku on 13.04.17.
 */
@Entity
@Table(name = "question_results")
public class QuestionResult {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "question_id")
    private int questionId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "category_id")
    private int categoryId;
    private String answers;
    @Column(name = "is_correct")
    private boolean isCorrect;

    public QuestionResult(int id, int questionId, int userId, int categoryId, String answers, boolean isCorrect) {
        this.id = id;
        this.questionId = questionId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.answers = answers;
        this.isCorrect = isCorrect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
