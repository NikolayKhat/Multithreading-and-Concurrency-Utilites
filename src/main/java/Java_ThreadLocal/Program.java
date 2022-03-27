package Java_ThreadLocal;

public class Program {

    public void run() {

        Runnable runnable = new RequestForMoneyTransfer();

        for (int i = 0; i < 5; i++) {
            new Thread(runnable).start();
        }
    }
}