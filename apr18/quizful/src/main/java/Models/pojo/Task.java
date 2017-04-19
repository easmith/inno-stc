package Models.pojo;

import java.util.ArrayList;

/**
 * Created by eku on 13.04.17.
 */

public class Task {

    private int id;

    private String text;

    private int category_id;

    private ArrayList answerList;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
