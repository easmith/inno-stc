package Models.pojo;

/**
 * Created by eku on 13.04.17.
 */
public class QuestionResult {

    private int id;
    private int questionId;
    private int userId;
    private int categoryId;
    private String answers;
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
