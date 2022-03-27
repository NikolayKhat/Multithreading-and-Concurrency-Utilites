package Java_Volatile_Keyword;

public class Program {

    private int numberOfAttempts = 1;   // количетсво попыток входа

    public Program(int numberOfAttempts) {
        this.numberOfAttempts = numberOfAttempts;
    }

    public void run() {
        Account account = new Account();

        Runnable logInRunnable = new LogInRunnable(account, numberOfAttempts);
        Runnable antiHackingRunnable = new AntiHackingRunnable(account);

        Thread logIn = new Thread(logInRunnable);
        Thread antiHacking = new Thread(antiHackingRunnable);


        logIn.start();
        antiHacking.start();

    }
}
