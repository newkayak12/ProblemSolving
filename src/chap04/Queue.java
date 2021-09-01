package chap04;

public class Queue {
	int max = 10;
	int top = -1;

	int arr[] = new int[max];

	public void enque(int number){
		if(top<max){
			if(top>=0){
				arr[top] = number;
				top++;
			} else if(top<0){
				top=0;
				arr[top] = number;
				top++;
			}
		} else {
			System.out.println("out of range");
		}
	}
	public void reLocation(){
		for(int i = 1; i<arr.length; i++){
			arr[i-1] = arr[i];
		}
	}
	public void deque (){
		if(top>0){
			System.out.println(arr[0]);
			reLocation();
			top--;	
		// } else if(top ==0){
		// 	System.out.println(arr[0]);
		// 	reLocation();
		// 	top--;
		} else if(top<=0){
			System.out.println("no data");
		}
	}
	public void clear(){
		arr = new int[max];
	}
	public void capacity(){
		System.out.println(max);
	}
	public void size(){
		System.out.println(top);
	}
	public void isEmpty(){
		if(top<0){
			System.out.println(true);
		} else {
			System.out.println(false);
		}
	}
	public void isFull(){
		if(top==max){
			System.out.println(true);
		} else {
			System.out.println(false);
		}
	}
	public void print(){
		if(top>0){
			for(int i=0; i<=top; i++){
				System.out.println(arr[i]);
			}
			
		} else {
			System.out.println("should init");
		}
	}	
	public void init(){
		arr = new int[max];
		top =0;
	}

	public static void main(String[] args) {
		Queue queue = new Queue();
		
		queue.init();
		queue.enque(1);
		queue.enque(2);
		queue.enque(3);
		queue.enque(4);
		queue.deque();
		queue.deque();
		queue.deque();
		queue.deque();
		queue.deque();
	}
}
