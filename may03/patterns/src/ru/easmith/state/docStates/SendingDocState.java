package ru.easmith.state.docStates;

import ru.easmith.state.Document;

/**
 * Created by eku on 05.05.17.
 */
public class SendingDocState extends DocState {
    public void signDocument(Document doc) {
        boolean sent = Math.random() < 0.7;
        System.out.println("doc sent: " + sent);
        changeState(doc, sent ? new SignedDocState() : new SignErrorDocState());
    }

}
