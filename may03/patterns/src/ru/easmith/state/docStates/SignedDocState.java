package ru.easmith.state.docStates;

import ru.easmith.state.Document;

/**
 * Created by eku on 05.05.17.
 */
public class SignedDocState extends DocState {
    @Override
    public void registerDocument(Document document) {
        boolean registered = Math.random() < 0.9;
        System.out.println("Registered: " + registered);
        changeState(document, registered ? new RegisteredDocState() : new ValidationErrorDocState());
    }
}
