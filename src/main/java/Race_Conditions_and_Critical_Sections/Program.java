package Race_Conditions_and_Critical_Sections;

public class Program {

    private int dimension = 2;

    private int[][] arr1 = new int[dimension][dimension];
    private int[][] arr2 = new int[dimension][dimension];
    private int[][] arr3 = new int[dimension][dimension];
    private int[][] arr4 = new int[dimension][dimension];

    public Program() {}

    public Program(int dimension) {
        this.dimension = dimension;

        arr1 = new int[dimension][dimension];
        arr2 = new int[dimension][dimension];
        arr3 = new int[dimension][dimension];
        arr4 = new int[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                arr1[i][j] = (int) (Math.random() * 1000);
                arr2[i][j] = (int) (Math.random() * 1000);
                arr3[i][j] = (int) (Math.random() * 1000);
                arr4[i][j] = (int) (Math.random() * 1000);
            }
        }
    }

    public void run() {
        System.out.println("Даны четыре матрицы: A, B, C, D.\n" +
                "Нужно найти результирующую матрицу M = A + B + B + C + D + D");

        Runnable runnable = new MyRunnable(arr1, arr2, arr3, arr4);

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1.length; j++) {
                arr1[i][j] += arr3[i][j];
            }
        }
    }

    public int[][] getArr() {
        return arr1;
    }
}
