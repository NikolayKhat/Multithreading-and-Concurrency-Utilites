package Thread_Safety_and_Shared_Resources;

public class Program {

    public void run() {
        System.out.println("\nПример: дан отрывок стихотворения, в котором присутствуют ошибки\n" +
                "(символы, которых не должно быть, пусть это будут '1').\n" +
                "Два потока будут одновременно удалять лишние символы.\n");

        StringBuilder str = new StringBuilder("У1 лукоморь1я дуб зел1ёный;\n" +
                "Зла1тая цепь на1 дубе том:\n" +
                "И днё1м и но1чью кот учён1ый\n" +
                "Всё1 ходит по цепи круго1м.\n");

        String strBug = "1";

        System.out.println(str);

        fixBug(str, strBug);

        System.out.println("\nУдалены лишние символы.\n");

        System.out.println(str + "\n" +
                "При одновременной работе потоков возникает состояние гонки.");
    }

    private void fixBug(StringBuilder str, String strBug) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int numBugs = 0;
                while (str.indexOf(strBug) != -1) {
                    int temp = str.indexOf(strBug);
                    if (temp != -1) {
                        str.delete(temp, temp + strBug.length());
                        numBugs++;
                    }
                }
                System.out.println(Thread.currentThread().getName() + " нашел " + numBugs + " ошибок");
            }
        };

        Thread[] threads = new Thread[2];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(runnable);
            threads[i].start();
        }

        try {
            for (int i = 0; i < threads.length; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}