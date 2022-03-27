package Java_Volatile_Keyword;

public class LogInRunnable implements Runnable{

    private Account account = null;
    private int numberOfAttempts;

    public LogInRunnable(Account account, int numberOfAttempts) {
        this.account = account;
        this.numberOfAttempts = numberOfAttempts;
    }

    @Override
    public void run() {
        while (!(account.isAuthorization() || account.isHacking())) {
            account.logIn(numberOfAttempts--);
        }
    }
}
