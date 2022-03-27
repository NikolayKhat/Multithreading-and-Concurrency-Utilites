package Java_Volatile_Keyword;

public class Account {

    private int password = 1;
    private int block = 3;  // количество попыток до блокировки
    private int counterBlocks = 0;  // количество неудачных попыток
    private boolean authorization = false;
    private volatile boolean hacking = false;

    public void logIn(int password) {
        if (this.password == password) {
            System.out.println("Вы вошли в аккаунт.");
            this.authorization = true;
            this.counterBlocks = 0;
        } else {
            if (--block > 0) System.out.println("Повторите попытку! (до блокировки оталось попыток: " + block + ")");
            this.counterBlocks++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!hacking) toWait();
    }

    public int getCounterBlocks() {
        return counterBlocks;
    }

    public void setHacking() {
        hacking = true;
    }

    public boolean isAuthorization() {
        return this.authorization;
    }

    public boolean isHacking() {
        return this.hacking;
    }

    private void toWait() {
        if (this.block == 0) {
            System.out.println("Доступ запрещен, подождите некоторое время.\n\n" +
                    "До следующей попытки осталось:");
            int sec = 5;
            while (!(sec == 0)) {
                System.out.println(sec--);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
            this.block = 3;
        }
    }
}
