package ru.easmith.state.docStates;

import ru.easmith.state.Document;

/**
 * Created by eku on 05.05.17.
 */
public class NewDocState extends DocState {
    @Override
    public void sendDocument(Document document) {
        System.out.println("send new doc");
        changeState(document, new SendingDocState());
    }
}
