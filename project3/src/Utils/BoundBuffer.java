package Utils;

public class BoundBuffer <T> {
    //Please note the biggest difference between this BoundBuffer
    //and the one we demoed in class is <T>
   // implement member functions: deposit() and fetch()
   int size;
   int current;
   int front;
   int back;
   private int[] buffer;

   public BoundBuffer(int size){
     if(size <= 0) {
        throw new IllegalArgumentException("size out of bounds");
     }
     this.size = size;
     buffer = new int[size];
   }

   public void deposit(int val){
     while(current == size){ //if full
       wait();
     }
     buffer[back] = val;
     back = (back + 1) % size;
     current++;
     notifyAll();
   }

   public int fetch(){ //return the first value and move buffer backwards
     while(size == 0){ //if empty
       wait();
     }
     int x = buffer[front];
     front = (front + 1) % size;
     current--;
     notifyAll();
     return x;
   }
}
