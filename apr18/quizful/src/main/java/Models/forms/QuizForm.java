package Models.forms;

import java.util.HashMap;

/**
 * Created by eku on 30.04.17.
 */
public class QuizForm {
    private HashMap<Integer, String> answers;

    public HashMap<Integer, String> getAnswers() {
        return answers;
    }

    public void setAnswers(HashMap<Integer, String> answers) {
        this.answers = answers;
    }
}
