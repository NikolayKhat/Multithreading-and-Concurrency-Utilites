package Java_ThreadLocal;

public class AdditionalRequestForMoneyTransfer implements Runnable{

    private InheritableThreadLocal<String> accountID;
    private ThreadLocal<Integer> amountOfMoney;

    public AdditionalRequestForMoneyTransfer(InheritableThreadLocal<String> accountID, ThreadLocal<Integer> amountOfMoney) {
        this.accountID = accountID;
        this.amountOfMoney = amountOfMoney;
    }

    @Override
    public void run() {
        System.out.println("\tПользователь " + accountID.get() + " отправил еще один запрос на перевод средств в размере " + amountOfMoney.get() + " рублей");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\tПользователь " + accountID.get() + " получил ответ");
    }
}






















