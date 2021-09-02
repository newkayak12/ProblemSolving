package chap06;

import java.util.Arrays;

public class BubbleSort {
	public static void main(String[] args) {
		int[] arr = {3,1,5,2,7,8,6};

		BubbleSort b = new BubbleSort();

		System.out.println(Arrays.toString(b.bubbleSort(arr)));
	}

	public int[] bubbleSort(int[] arr){
		int[] result = arr;
		int count = 0;
		for(int j = 0; j<result.length; j++){
			
			for(int i=count; i<result.length; i++){
				if((i!=result.length-1) && (result[i]>result[i+1])){
					int temp = result[i];
					result[i] = result[i+1];
					result[i+1] = temp;
				}
			}
			count +=1;
		}
		return result;
	}
}
