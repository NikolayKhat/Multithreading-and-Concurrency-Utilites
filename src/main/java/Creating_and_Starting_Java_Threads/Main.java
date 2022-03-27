package Creating_and_Starting_Java_Threads;

public class Main {
    public static void main(String[] args) {

        int N = 10 * 1000 * 1000;   // количество проверяемых чисел
        int numThread = 3;  // количество потоков

        PrimeNum primeNum = new PrimeNum(N, numThread);

        double start = System.currentTimeMillis();

        primeNum.run();

        double end = System.currentTimeMillis();

        System.out.println((end - start) / 1000);
    }
}

