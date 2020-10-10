public class MyMemoryAllocation extends MemoryAllocation {
	public MyMemoryAllocation(int mem_size, String algorithm){
		super(mem_size, algorithm);
	}
	MyLinkedList free_list;
	MyLinkedList used_list;
	

	public int alloc(int size, String str) {
	return 0;
	}

	public void free(int address) {
		if()
			free(address);
		else
			System.out.println("Nothing to free");
	}
	
	public int size(){
		return mem_size;
	}

	public int max_size(){
	return 0;
	}
	
	public void print(){
		
	}
}
