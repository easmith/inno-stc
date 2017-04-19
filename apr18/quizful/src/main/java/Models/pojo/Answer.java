package Models.pojo;

/**
 * Created by eku on 13.04.17.
 */

public class Answer {

    private int id;

    private String text;

    private Boolean isCorrect;

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
}
