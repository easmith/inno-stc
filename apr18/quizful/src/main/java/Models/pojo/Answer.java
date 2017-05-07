package Models.pojo;

/**
 * Created by eku on 13.04.17.
 */

public class Answer {
    private int id;
    private int question_id;
    private String text;
    private Boolean isCorrect;

    public Answer(int id, int question_id, String text, Boolean isCorrect) {
        this.id = id;
        this.question_id = question_id;
        this.text = text;
        this.isCorrect = isCorrect;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }
}
