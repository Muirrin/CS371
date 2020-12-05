/**
 * References: https://stackoverflow.com/questions/5291041/is-there-a-mutex-in-java
 */
package Utils;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundBuffer <T> {
     //Please note the biggest difference between this BoundBuffer
     //and the one we demoed in class is <T>
     // implement member functions: deposit() and fetch()
     
     //Synchronization Attributes
     private Lock mutex_lock = new ReentrantLock(true);
     private final Condition isEmpty;
     private final Condition isFull;

     //Circular Queue Attributes
     int currentSize; 
     T[] circularQueueItems;
     int size; 
     private int rear;//rear position of circular queue
     private int front; //front position of circular queue     


     //Constructor for the Bound Buffer
     public BoundBuffer(int size){ 
        this.size = size;
        circularQueueItems = (T[]) new Object[this.size];
        currentSize = 0;
        front = 0;
        rear = 0;
        isEmpty = mutex_lock.newCondition();
        isFull = mutex_lock.newCondition();
     }

     //Deposit method 
     public void deposit(T item) throws InterruptedException {
        mutex_lock.lock();
        while(currentSize == size){ //if full
           isFull.await();
        }
         circularQueueItems[rear] = item;
         rear = (rear + 1) % circularQueueItems.length;    
         currentSize++; // increase number of elements in Circular queue
         isEmpty.signal();
         mutex_lock.unlock();
     }
   

     //Fetch method
     public T fetch() throws InterruptedException {
        mutex_lock.lock();
        while(size == 0){ //if full
            isEmpty.await();
        }
        T item;
        item = circularQueueItems[front];
        circularQueueItems[front] = null;
        front = (front + 1) % circularQueueItems.length;
        currentSize--;
        isFull.signal();
        mutex_lock.unlock();
        return item;
     }

//    public boolean isFull() {
//         return (currentSize == circularQueueItems.length);
//    }

//     public boolean isEmpty() {
//         return (currentSize == 0);
//     }

}
