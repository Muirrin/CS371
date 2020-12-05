package Utils;
import java.util.*;

public class BoundBuffer <T> {
    //Please note the biggest difference between this BoundBuffer
    //and the one we demoed in class is <T>
   // implement member functions: deposit() and fetch()

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
   }

   public void deposit(T item) throws InterruptedException {
        while(currentSize == size){ //if full
           wait();
        }
         circularQueueItems[rear] = item;
         rear = (rear + 1) % circularQueueItems.length;    
         currentSize++; // increase number of elements in Circular queue
    
        }
   

   public T fetch() throws InterruptedException {
        while(size == 0){ //if full
            wait();
        }
        T item;
        item = circularQueueItems[front];
        circularQueueItems[front] = null;
        front = (front + 1) % circularQueueItems.length;
        currentSize--;
        return item;
   }

   public boolean isFull() {
        return (currentSize == circularQueueItems.length);
   }

    public boolean isEmpty() {
        return (currentSize == 0);
    }

}
