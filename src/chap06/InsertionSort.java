package chap06;

import java.util.Arrays;

public class InsertionSort {
	public static void main(String[] args) {
		int[] arr = {3,1,5,2,7,8,6};
		InsertionSort i = new InsertionSort();
		System.out.println(Arrays.toString(i.insertionSort(arr)));
	}

	public int[] insertionSort(int[] arr){
		int[] result = arr;
		for(int i = 1; i<arr.length; i++){
			int j= i-1;
			int target = result[i];
			while(j>=0&&target<arr[j]){
				result[j+1] = result[j];
				j--;
			}
			result[j+1]=target;

		
		}
		return result;
	}
}
