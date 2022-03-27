package Java_Thread_Signaling;

public class Program {

    private MySignal signal1 = new MySignal();
    private MySignal signal2 = new MySignal();

    private int sum = 0;

    public void run() {
        int[] arr1 = new int[(int) (Math.random() * 100000)];
        int[] arr2 = new int[(int) (Math.random() * 100000)];
        int[] arr3 = new int[(int) (Math.random() * 100000)];

        fillingTheArray(arr1);
        fillingTheArray(arr2);
        fillingTheArray(arr3);


        Runnable runnableMain = () -> taskMain(arr1);
        Runnable runnable1 = () -> task(signal1, arr2);
        Runnable runnable2 = () -> task(signal2, arr3);

        Thread threadMain = new Thread(runnableMain);
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        System.out.println("Даны три матрцы. Нужно найти отношение суммы элементов первой матрицы к сумме элементов трех матриц.\n");

        threadMain.start();
        thread1.start();
        thread2.start();
    }

    private void taskMain(int[] arr) {
        System.out.println(Thread.currentThread().getName() + " выполняет свою часть задачи");

        int sumArr = sumOfElements(arr);
        addSum(sumArr);
        sleep(5000);

        System.out.println("\t" + Thread.currentThread().getName() + " ждет получения результата от первого и второго потоков");

        signal1.doWait();
        signal2.doWait();

        System.out.println("\t\t" + Thread.currentThread().getName() + " продолжил выполнение задачи");

        sleep(5000);

        System.out.println(Thread.currentThread().getName() + " завершил работу");

        System.out.println("\nОтношение равно " + (double) sumArr / sum);
    }

    private void task(MySignal signal, int[] arr) {
        System.out.println(Thread.currentThread().getName() + " выполняет свою часть задачи");

        int sumArr = sumOfElements(arr);
        addSum(sumArr);
        sleep(5000);

        System.out.println("\t\t\t" + Thread.currentThread().getName() + " закончил");
        signal.doNotify();
    }

    private void fillingTheArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }
    }

    private int sumOfElements(int[] arr) {
        int sumArr = 0;
        for (int i = 0; i < arr.length; i++) {
            sumArr += arr[i];
        }
        return sumArr;
    }

    private synchronized void addSum(int val) {
        sum += val;
    }

    private void sleep(int val) {
        try {
            Thread.sleep((long) (Math.random() * val));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}