package ru.easmith.chain;

/**
 * Created by eku on 05.05.17.
 */
public class NewspaperRumor extends Rumor {

    private long balance = 0;

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Override
    public void obs() {
        if (balance > 10000) {
            System.out.println("Слухи не подтвердились");
            return;
        }
        super.obs();
    }
}
