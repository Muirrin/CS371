import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import Utils.*;

public class PartitionTable {
	// TODO: your code here
	// Notes:
	// (1) each partition works like an bounded buffer between mappers and a
	// reducer. (you can assume size = 10 or 50)
	// (2) if reducer_i wants to fetch a KV pair it can only fetches from
	// partition_i
	// (3) reducer_i also has to maintain a kv store which maps a key to all the
	// value associated to the key, for instace
	// if reducer_i has fetched {"foo", 1} {"bar",1} {"foo",1} {"foo",1} and
	// {"bar",1}
	// from partition_i, then reducer_i should have maintained {"foo", {1,1,1}} and
	// {"bar", {1,1}}. You can use a
	// hashmap or a tree to implement this KV store, but where
	// should this KV stored be? inside the reducer? inside this partitionTable?

	private Lock mutex_lock = new ReentrantLock(true);
	private static int INITIAL_SIZE = 10;
	BoundBuffer<KV>[] boundBuffer = new BoundBuffer[INITIAL_SIZE];// Declaring an array of Bound Buffers

	public PartitionTable() {
		// public KV(Object key, Object value);
		for (int i = 0; i < INITIAL_SIZE; i++) { // Initializing the array of bound buffers
			boundBuffer[i] = new BoundBuffer<KV>(INITIAL_SIZE);
		}
	}

	public void addToPartition(KV item, long partitionNumber) {
		mutex_lock.lock();
		// long partitionNumber = Partitioner(item.key,INITIAL_SIZE);
		boundBuffer[(int) partitionNumber].deposit(item);
		mutex_lock.unlock();
	}

	// Fetch method
	public KV fetchFromPartition(Object key, int partitionNumber) {
		mutex_lock.lock();
		KV item;
		item = boundBuffer[partitionNumber].fetch();
     	mutex_lock.unlock();
     	return item;
  }
}
