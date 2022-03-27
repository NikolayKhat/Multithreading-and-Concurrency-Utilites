package Creating_and_Starting_Java_Threads;

public class PrimeNum {
    private int N = 20;         // количество проверяемых чисел
    private int numThread = 1;  // количество потоков

    public PrimeNum() {}

    public PrimeNum(int N, int numThread) {

        this.N = N;
        this.numThread = numThread;
    }

    void run() {
        System.out.println("Найти все простые числа до " + N + ", разбив задачу на потоки.\n" +
                "Результат выполнения каждого потока сохранить в соответствующий файл.");
        Thread[] threads = new Thread[numThread];

        for (int i = 0; i < numThread; i++) {
            int start = (int)(N / numThread * i) + 1;
            int end;

            if (i != numThread - 1)
                end = (int)(N / numThread * (i + 1));
            else
                end = (int)(N / numThread * (i + 1)) + 1;

            threads[i] = new Thread(new MyRunnable(start, end));
            threads[i].start();
        }

        try {
            for (int i = 0; i < numThread; i++) threads[i].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
