package programmers;

import java.util.Arrays;
import java.util.Comparator;

public class RemoveSmallestNumber {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] {3,6,2,9,1,8})));
		System.out.println(Arrays.toString(solution(new int[] {2})));
		System.out.println(Arrays.toString(solution(new int[] {3,0,100,-1,1,8})));
		System.out.println(Arrays.toString(solution(new int[] {3,-1,-2,-3,-4,-5})));
	}
	
	public static int[] solution(int[] arr){
		int[] answer = {};
		if(arr.length==1){
			answer= new int[] {-1};
		} else {
			int smallest = 0;
			for(int i = 0; i<arr.length; i++){
				smallest = arr[i];
				for(int j= 0; j<arr.length; j++){
					if(i!=j&&smallest>arr[j]){
						smallest = arr[j];
					}
				}
			}

			answer = new int[arr.length-1];
			int i = 0;
			for(int a : arr){
				if(a!=smallest){
					answer[i++]=a;
				}
			}

		}

		return answer;
	}
}
