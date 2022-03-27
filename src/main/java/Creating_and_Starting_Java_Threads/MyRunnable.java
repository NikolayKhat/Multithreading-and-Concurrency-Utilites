package Creating_and_Starting_Java_Threads;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class MyRunnable implements Runnable {

    private int start = 1;
    private int end = 20;

    public MyRunnable() {}

    public MyRunnable(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {

        try {
            File file = new File("Простые числа " + start + "-" + end + ".txt");
            file.createNewFile();

            PrintWriter pw = new PrintWriter(file);

            for (int i = start; i < end + 1; i++) {
                if (prime(i) == true) pw.println(i);
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // проверяет число на простоту
    private boolean prime(int x) {

        for (int i = 2; i <= Math.sqrt(x); i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
}
