import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
public class MyMapReduce extends MapReduce {
	////TODO: your code here.
	//	What is in a running instance of MapReduce?
	static PartitionTable table;
	static int numPartitions;

	public void MREmit(Object key, Object value)
	{
		//TODO: your code here.
		//call partitionTable
		throw new UnsupportedOperationException();
	}

	public Object MRGetNext(Object key, int partition_number) {
		//TODO: your code here.
		throw new UnsupportedOperationException();
	}
	@Override
	protected void MRRunHelper(String inputFileName,
		    		  MapperReducerClientAPI mapperReducerObj,
		    		  int num_mappers,
		    		  int num_reducers)
	{
		//TODO: your code here.
		Reducer(mapperReducerObj, num_partitions);
		throw new UnsupportedOperationException();
	}

	//This is the producer
	public void Mapper(Object inputSource) {
	//public class ConcurrentHashMap<K,​V> extends AbstractMap<K,​V> implements ConcurrentMap<K,​V>, Serializable
	//ConcurrentHashMap<K, V> chm = new ConcurrentHashMap<>();
	//int counter = num of mapper threads; decrement every time thread completes
		Map(inputSource);
	}

	//This is the consumer
	public void Reducer(Object key, int partition_number) {
			reduce(key, partition_number);

	}

}
