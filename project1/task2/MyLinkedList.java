public class MyLinkedList {

	private MyNode head;
	
	public MyLinkedList(){

	}

	public void add(){
		if(head==null){
			head = new MyNode();
		}
		else{
			MyNode newNode = new MyNode();
			MyNode temp = head;
			
			while(temp!=null){
				temp=temp.next();
			}

			temp.set(newNode);
		}
	}
	
	public void delete(){


	}

	public void set(){

	}

	public Node get(){
		return NULL;
	}

	public String toString(){
		String str = "This is a linked List";
		return str;
	}


}

