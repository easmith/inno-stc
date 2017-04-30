package Models.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eku on 13.04.17.
 */

public class Task {

    private int id;
    private String text;
    private int categoryId;
    private List<Answer> answers;

    public Task(int id, String text, int categoryId) {
        this.id = id;
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
}
