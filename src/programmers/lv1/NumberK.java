package programmers.lv1;

import java.util.ArrayList;
import java.util.Arrays;

public class NumberK{

	public static void main(String[] args) {
		int[] progresses ={93,30,55};
		int[] speeds={1,30,5};

		System.out.println(Arrays.toString(solution(progresses, speeds)));
	}




	
	public static int[] solution(int[] progresses, int[] speeds) {
		int[] answer = {};
	       
		int count = 0;
		int runningTime = progresses.length;
		int top = 0;
		ArrayList temp = new ArrayList();
		while(runningTime > 0){
			for(int i = 0; i<progresses.length; i++){
				progresses[i] = progresses[i]+speeds[i];
			}
		    
		    	for(int i =top; i<progresses.length; i++){
				if(progresses[i]>=100){
					count++;
					runningTime --;
					top++;
				} else {
					break;
				}
			}
			if(count>0){
				temp.add(count);
			}
			
			answer = new int[temp.size()];
			for(int i =0; i<temp.size(); i++){
			    answer[i] = (int) temp.get(i);
			}
			count =0;
		}
			return answer;
		}
}