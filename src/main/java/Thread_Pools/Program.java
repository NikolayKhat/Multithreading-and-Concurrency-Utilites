package Thread_Pools;

import java.util.concurrent.*;

public class Program {
    private ExecutorService executorService = null;

    private int numCabinets = 1;    // количество кабинетов
    private Semaphore semaphore = null;

    private int numSessions = 1;    // количество сеансов
    private int numHuman = 1;   // количество людей

    public Program(int numCabinets, int numSessions, int numHuman) {
        this.numCabinets = numCabinets;
        this.numSessions = numSessions;
        this.numHuman = numHuman;
    }

    public void run() {
        System.out.println("В больнице всегда работает " + numCabinets + " врача, у каждого имеется собственный кабинет.\n" +
                "В больницу обратились " + numHuman + " человек, которым назначили по три сеанса в одни и те же дни.\n" +
                "В больнице существует правило, согласно которому одновременно на первом сеансе могут\n" +
                "принимать все врачи, на втором - на одного врача меньше и т.д., но не меньше одного.\n");

        for (int i = 0; i < numSessions; i++) {
            System.out.println("\n" + (i + 1) + " сеанс:");
            queue(i);
            if (i == numSessions - 1) System.out.println("\nПациенты прошли курс!");
        }
    }

    private void queue(int week) {
        System.out.println("Свободно кабинетов: " + numCabinets + "\n");

        executorService = Executors.newFixedThreadPool(3);
        semaphore = new Semaphore(numCabinets);

        for (int i = 0; i < numHuman; i++) {
            int Human = i;
            System.out.println("Пациент " + Human + " встал в очередь.");
            executorService.submit(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("\tПациент " + Human + " пришел на сеанс.");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("\t\tПациент " + Human + " закончил сеанс.");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (numCabinets > 1) numCabinets--;
    }
}
