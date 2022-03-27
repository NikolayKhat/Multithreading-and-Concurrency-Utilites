package Java_Volatile_Keyword;

public class AntiHackingRunnable implements Runnable{

    private Account account = null;
    private int counterBlocks = 6;

    public AntiHackingRunnable(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        while (!(account.isAuthorization() || account.isHacking())) {
            if (account.getCounterBlocks() == counterBlocks) {
                account.setHacking();
                System.out.println("\nПытались взломать аккаунт!");
            }
        }
    }
}
