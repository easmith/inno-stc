package ru.easmith.state.docStates;

import ru.easmith.state.Document;

/**
 * Created by eku on 05.05.17.
 */
public class DocState {
    public void sendDocument(Document document) throws Exception {
        throw new Exception("Method send not allowed for state " + this.getClass());
    }
    public void signDocument(Document document) throws Exception {
        throw new Exception("Method sign not allowed for state " + this.getClass());
    }
    public void registerDocument(Document document) throws Exception {
        throw new Exception("Method register not allowed for state " + this.getClass());
    }

    protected void changeState(Document document, DocState docState) {
        document.changeState(docState);
    }
}
