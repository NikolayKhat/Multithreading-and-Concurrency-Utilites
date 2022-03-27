package Read_Write_Locks_in_Java;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

public class Human {

    private List<Integer> list;

    private Lock readLock;
    private Lock writeLock;

    public Human(List<Integer> list, ReadWriteLock readWriteLock) {
        this.list = list;
        readLock = readWriteLock.readLock();
        writeLock = readWriteLock.writeLock();
    }

    // Выводит на экран список и его длину
    public void read() {
        readLock.lock();
        System.out.println("Промежуточный список: " + list);
        try {
            Thread.sleep(1L * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Его длина: " + list.size());
        readLock.unlock();
    }

    // Добавляет число в начала списка
    public void write(int val) {
        try {
            Thread.sleep(1L * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        writeLock.lock();
        list.add(0, val);
        writeLock.unlock();
    }
}
