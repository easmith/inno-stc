package ru.easmith.chain;

/**
 * Created by eku on 05.05.17.
 */
public abstract class Rumor {
    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
    private Rumor rumor;

    public void setRumor(Rumor rumor) {
        this.rumor = rumor;
    }

    public void obs() {
        if (rumor != null) {
            rumor.message = message;
            System.out.println(message);
            rumor.obs();
        }
    }
}
