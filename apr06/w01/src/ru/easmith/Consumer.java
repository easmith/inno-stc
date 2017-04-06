package ru.easmith;

class Consumer implements Runnable {
    private MyDate date;
    private int step;

    public Consumer(MyDate date, int step) {
        this.date = date;
        this.step = step;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (date) {
                try {
                    date.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (date.isMultipleOf(5)) {
                    System.out.println("Step " + step);
                }
            }

        }
    }
}