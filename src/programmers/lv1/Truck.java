package programmers.lv1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Truck {
	public static void main(String[] args) {
		System.out.println("\nt1");
		System.out.println(solution(2, 10, new int[]{7,4,5,6}));
		System.out.println("\nt2");
		System.out.println(solution(100, 100, new int[]{10}));
		System.out.println("\nt3");
		System.out.println(solution(100, 100, new int[]{10,10,10,10,10,10,10,10,10,10}));
	}
	
	public static int solution(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;
		// bridge_length 만큼 차가 지나가는 시간이 걸린다.
		// weight을 초과해서는 안된다.
		// que를 사용하는 것으로 추정되며, cpu FIFO 스케쥴링과 유사하다. 
		Queue queue = new LinkedList();
		
		

		int[][] arr1 = {{1,2},{1,2}};
		// System.out.println(queue);
		// List test = new ArrayList();
		// while(!queue.isEmpty()){
		// 	int onload = 0;
		// 	for(int i=0; i<bridge_length; i++){
		// 		if((!queue.isEmpty())&&onload<weight && (weight-onload)>(int)queue.peek()){
		// 			onload =(int) queue.poll();
		// 			answer+=bridge_length;
		// 		}
		// 	}

		// }

		
		
		
		int totalWeight = 0;
		for(int a : truck_weights){
			while(true){
				if(queue.isEmpty()){
					queue.add(a);
					totalWeight += a;
					answer++;
					break;
				} else if(queue.size()==bridge_length){
					totalWeight-= (int) queue.poll();
				} else{
					if(totalWeight+a<=weight){
						queue.add(a);
						totalWeight+=a;
						answer++;
						break;
					} else{
						queue.add(0);
						answer++;
					}
				}

			}
		}

		return answer+bridge_length;
	    }

	// public static int solution2(int bridge_length, int weight, int[] truck_weights) {
	// 	Queue queue = new LinkedList<>();
	// 	for(int a : truck_weights){
	// 		queue.add(a);
	// 	}
	// 	int total = 0;
	// 	while(!queue.isEmpty()){
	// 		if(total+(int)queue.peek()<=weight){

	// 		}
	// 	}
	
	// }
}
