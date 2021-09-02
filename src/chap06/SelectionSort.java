package chap06;

import java.util.Arrays;

public class SelectionSort {
	public static void main(String[] args) {
		int[] arr = {3,1,5,2,7,8,6};
		SelectionSort s = new SelectionSort();

		System.out.println(Arrays.toString(s.selectSort(arr)));
	}
	public int[] selectSort(int[] arr){
		int[] result = arr;
		for(int i = 0; i<result.length-1; i++){
			int min = i;
			for(int j = min+1; j<result.length; j++){
				if(result[min]>result[j]){
					min = j;
					
				}
			}

				int temp = result[min];
				result[min] = result[i];
				result[i] = temp;
		}


		return result;
	}
}
