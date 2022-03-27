package Semaphores;

import java.util.concurrent.Semaphore;

public class Shop {
    private Semaphore semaphore = null;
    private int numClients = 10;    // количество клиентов
    private int[] numBoxOffice = {3, 4, 8};     // количество работаюших касс

    public void run() {
        System.out.println("В магазине имеется 8 касс. Магазин открывается в 6:00 и работает до 24:00.\n" +
                "С 6:00 до 12:00 поток клиентов маленький, поэтому работают только 3 кассы.\n" +
                "С 12:00 до 18:00 поток клиентов увеличивается - работают 4 кассы.\n" +
                "С 18:00 до 24:00 поток клиентов самый большой - работают все кассы.\n");

        for (int i = 0; i < numBoxOffice.length; i++) {
            if (i == 0) System.out.println("Магазин открылся!\n");
            if (i == 1) System.out.println("\nНаступило 12:00!\n");
            if (i == 2) System.out.println("\nНаступило 18:00!\n");

            shopping(numBoxOffice[i]);

            if (i == 2) System.out.println("\nМагазин закрылся!");
        }
    }

    private void shopping(int permits) {
        semaphore = new Semaphore(permits);
        
        Runnable runnable = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " ждет обслуживания.");
                semaphore.acquire();
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("\t" + Thread.currentThread().getName() + " обслуживается.");
                try {
                    Thread.sleep(1L * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("\t\t" + Thread.currentThread().getName() + " закончил покупку.");
                semaphore.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread[] threads = new Thread[numClients];

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