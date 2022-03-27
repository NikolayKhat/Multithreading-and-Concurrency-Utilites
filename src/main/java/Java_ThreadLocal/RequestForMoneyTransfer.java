package Java_ThreadLocal;

class RequestForMoneyTransfer implements Runnable {
    private InheritableThreadLocal<String> accountID = new InheritableThreadLocal<>() {
        @Override
        protected String initialValue() {
            return ("(ID: " + (int) (Math.random() * 10000) + ")");
        }
    };

    private ThreadLocal<Integer> amountOfMoney = new ThreadLocal<>() {
        @Override
        protected Integer initialValue() {
            return (int) (Math.random() * 100000);
        }

    };

    @Override
    public void run() {
        System.out.println("Пользователь " + accountID.get() + " отправил запрос на перевод средств в размере " + amountOfMoney.get() + " рублей");
        int temp = (int) (Math.random() * 2);
        if (temp == 1) {
            Thread thread = new Thread(new AdditionalRequestForMoneyTransfer(accountID, amountOfMoney));
            thread.start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Пользователь " + accountID.get() + " получил ответ");
    }
}
