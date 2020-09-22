package bova.coursera.concurrent.wait2;

public class BadThreads1 {

    static String message;

    private static class CorrectorThread
            extends Thread {

        public void run() {
            try {
                sleep(1000);
            } catch (InterruptedException e) {}
            // Key statement 1:
            message = "Mares do eat oats.";
        }
    }

    public static void main(String args[])
            throws InterruptedException {

        Thread t = new CorrectorThread();
        t.start();
        message = "Mares do not eat oats.";
        Thread.sleep(2000);
        // Key statement 2:

        t.join();
        System.out.println(message);
    }
}
