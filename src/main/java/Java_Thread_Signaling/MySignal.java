package Java_Thread_Signaling;

public class MySignal {

    private Integer integer = new Integer(0);

    private boolean wasSignaled = false;

    public void doWait() {
        synchronized (integer) {
            while (!wasSignaled) {
                try {
                    integer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            wasSignaled = false;
        }
    }

    public void doNotify() {
        synchronized (integer) {
            wasSignaled = true;
            integer.notify();
        }
    }
}
