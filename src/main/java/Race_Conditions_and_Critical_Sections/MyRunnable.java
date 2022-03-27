package Race_Conditions_and_Critical_Sections;

class MyRunnable implements Runnable{
    private int[][] arr1 = null;
    private int[][] arr2 = null;
    private int[][] arr3 = null;
    private int[][] arr4 = null;

    private Integer lock1 = new Integer(1);
    private Integer lock2 = new Integer(2);


    public MyRunnable(int[][] arr1, int[][] arr2, int[][] arr3, int[][] arr4) {
        this.arr1 = arr1;
        this.arr2 = arr2;
        this.arr3 = arr3;
        this.arr4 = arr4;
    }

    @Override
    public void run() {
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1.length; j++) {
                synchronized (lock1) {
                    arr1[i][j] += arr2[i][j];
                }

                synchronized (lock2) {
                    arr3[i][j] += arr4[i][j];
                }
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
