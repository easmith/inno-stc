package ru.easmith.state;

import ru.easmith.state.docStates.DocState;
import ru.easmith.state.docStates.NewDocState;

/**
 * Created by eku on 05.05.17.
 */
public class Document {

    String content;

    DocState docState;

    public Document(String content) {
        this.content = content;
        this.docState = new NewDocState();
    }

    public void changeState(DocState newState) {
        this.docState = newState;
    }

    public void sendDocument() throws Exception {
        docState.sendDocument(this);
    }

    public void signDocument() throws Exception {
        docState.signDocument(this);
    }

    public void registerDocument() throws Exception {
        docState.registerDocument(this);
    }

}
