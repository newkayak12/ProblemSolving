package programmers.lv1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;



public class Printer {
	public static void main(String[] args) {
		int[] ref = {1,1,9,1,1,1};
		System.out.println(sol2(ref, 0));
		System.out.println("test2");
		int[] ref2 = {2,1,3,2};
		System.out.println(sol2(ref2, 2));
		
	}	

	public static int solution(int[] priorities, int location){
		int answer = 0;
		// 우선순위대로 정리하며
		// 원래 내가 하고 싶었던 것이 어디있는지 찾아야한다. 
		int[] ref = Arrays.copyOf(priorities, priorities.length);
		int[] ref2 = Arrays.copyOf(priorities, priorities.length);
		Arrays.sort(ref2);
		int maxValue = ref2[ref.length-1];

		int lValue = ref[location];
		ArrayList<int[]> tmp = new ArrayList();
		for(int i = 0; i<ref.length; i++){
			tmp.add(new int[] {i,ref[i]});
		}
		

		for(int i =0; i< tmp.size(); i++){
			if(tmp.get(i)[1]<maxValue){
				int[] tempor = tmp.get(0);
				tmp.remove(0);
				tmp.add(tempor);
			} else {
				break;
			}
		}

		for(int i=0; i< tmp.size(); i++){
			if(tmp.get(i)[0] == location && tmp.get(i)[1] == lValue){
				answer = i;
			}
		}
		return answer;

	}
	
	public static int  sol2(int[] priorities, int location){
		int answer2 = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

		for(int priority : priorities){
			pq.offer(priority);
		}
		while(!pq.isEmpty()){
			for(int i=0; i<priorities.length; i++){
				System.out.println(pq.peek()+" : "+priorities[i]);
				if(pq.peek()==priorities[i]){
					pq.poll();
					answer2++;
				
					if(location == i){
						pq.clear();
						break;
					}

				}
			}

		}


		return answer2;
	}
	
	
	
}

