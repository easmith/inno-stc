package ru.easmith.state;

/**
 * Created by eku on 05.05.17.
 */
public class Main {

    public static void main(String[] args) {

        try {
            Document doc = new Document("content");
            doc.sendDocument();
            doc.signDocument();
            doc.registerDocument();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
