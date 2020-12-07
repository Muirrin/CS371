import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyMapReduce extends MapReduce {
	//// TODO: your code here.
	// What is in a running instance of MapReduce?
	static PartitionTable table;
	static int numPartitions;
	private Lock mutex_lock = new ReentrantLock(true);

	public void MREmit(Object key, Object value) {
		// TODO: your code here.
		// call partitionTable addToPartition(Object key, value, partitionNumber);
		mutex_lock.lock();
		long partitionNumber = Partitioner(key, value, numPartitions); // Get the partition number
		KV item = new KV(key, value);
		table.addToPartition(item, partitionNumber); // Add to partition table which will be added to a buffer
		mutex_lock.unlock();
		throw new UnsupportedOperationException();
	}

	public Object MRGetNext(Object key, int partition_number)  {
		// TODO: your code here.
		mutex_lock.lock();
		KV item;
		item = table.fetchFromPartition(key, partition_number);
		mutex_lock.unlock();
		return item;
		//throw new UnsupportedOperationException(); ???
  }
	@Override
	protected void MRRunHelper(String inputFileName,
		    		  MapperReducerClientAPI mapperReducerObj,
		    		  int num_mappers,
		    		  int num_reducers)
	{
		//TODO: your code here.
    int i = 0;
  
   while(i<num_mappers){
            Thread producer = new Thread(new Mapper()); //mapper threads
            producer.start();
            producer.join();
            i++;
   }
   while(i<num_reducers){
            Thread consumer = new Thread(new Reducer()); //mapper threads
            consumer.start();
            consumer.join();
            i++;
    }
		throw new UnsupportedOperationException();
	}

  	//Classes for Mapper() and Reducer()
    private static class Mapper implements Runnable{
        @Override
        public void run() {
							Map(inputSource);
				}
    }

    private static class Reducer implements Runnable{
        @Override
        public void run() {
					ConcurrentHashMap<Object, Object> hashMap = new ConcurrentHashMap<>();
					KV item;
					while(key!=EOS){ //before end of string
						item = table.fetchFromPartition(key, partition_number);
						hashMap.put(item.key,item.val);
					}
						Reduce(key, partition_number);
						System.out.println("This is KVS:");
        }
    }


}
