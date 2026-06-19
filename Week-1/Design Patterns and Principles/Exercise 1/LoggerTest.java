public class LoggerTest {

    public static void main(String[] args) throws InterruptedException {

        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("Application started.");
        logger2.log("Fetching configuration.");

        System.out.println("logger1 == logger2 ? " + (logger1 == logger2));
        System.out.println("Total logs so far: " + logger1.getLogCount());

        Thread worker = new Thread(() -> {
            Logger threadLogger = Logger.getInstance();
            threadLogger.log("Message logged from worker thread.");
            System.out.println("Same instance from worker thread? " +
                    (threadLogger == logger1));
        });

        worker.start();
        worker.join();

        logger1.log("Application finished.");
        System.out.println("Final total log count: " + logger1.getLogCount());
    }
}