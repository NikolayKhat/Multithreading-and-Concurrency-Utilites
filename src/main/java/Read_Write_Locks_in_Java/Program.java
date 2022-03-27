package Read_Write_Locks_in_Java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Program {

    public void run() {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        List<Integer> list = new ArrayList<>();

        int n = 5;  // Количество чисел, вводимых одним пользователем

        int[] mas1 = new int[n];    // массив нечетных чисел, которые вводит первый пользователь
        int[] mas2 = new int[n];    // массив четных чисел, которые вводит второй пользователь

        for (int i = 0; i < n; i++) {
            mas1[i] = 2 * i + 1;
            mas2[i] = 2 * (i + 1);
        }

        Human humans1 = new Human(list, readWriteLock);
        Human humans2 = new Human(list, readWriteLock);

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < (int)(mas1.length / 2); i++) {
                    humans1.write(mas1[i]);
                }
                humans1.read();
                for (int i = (int)(mas1.length / 2); i < mas1.length; i++) {
                    humans1.write(mas1[i]);
                }
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < mas2.length; i++) {
                    humans2.write(mas2[i]);
                }
            }
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        System.out.println("Два пользователя каждую секунду одновременно добавляют в начало списка числа.\n" +
                "Первый добавляет нечетные числа, второй - четные.\n" +
                "Спустя некоторое время первый пользователь выводит список на экран\n" +
                "и еще через секунду выводит его длину, затем продолжает добавлять числа.\n" +
                "В конце выводится получившийся список.\n");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Collections.sort(list);
        System.out.println("Получившийся список: " + list);
    }
}
