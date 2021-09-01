package chap04;


public class Stack {
	int max = 10;
	int top = -1;

	int arr[] = new int[max];

	public void push(int number){
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
	public void pop (){
		if(top>0){
			System.out.println(arr[top-1]);
			top--;	
		} else if(top ==0){
			System.out.println(arr[top-1]);
			top--;
		} else if(top<0){
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
		Stack stack = new Stack();
		
			stack.init();
			stack.push(1);
			stack.push(2);
			stack.push(3);
			stack.pop();
			stack.pop();
			stack.pop();
	}
}
