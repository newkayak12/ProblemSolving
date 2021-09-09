package chap06;

import java.util.Arrays;

public class QuickSort {
	public static void main(String[] args) {
		int[] arr = {5,8,4,2,6,1,3,9,7};
		QuickSort q = new QuickSort();
		System.out.println(Arrays.toString(q.quickSort(arr, 0, arr.length-1)));
	}
	public int[] quickSort(int[] arr, int left, int right){
		int[] result = arr;
		int leftPoint = left;
		int rightPoint = right;
		int pivotPoint = (leftPoint+rightPoint)/2;
		System.out.println("pivot : "+ result[pivotPoint]);

			do{
				while(result[pivotPoint]>result[leftPoint]){
					leftPoint+=1;
				}
				while(result[pivotPoint]<result[rightPoint]){
					rightPoint -=1;
				}

				if(leftPoint<=rightPoint){
					int temp = result[leftPoint];
					result[leftPoint] = result[rightPoint];
					result[rightPoint] = temp;
					leftPoint++;
					rightPoint--;
				}
			}while(leftPoint<=rightPoint);

			if(left<rightPoint){
				quickSort(result, left, rightPoint);
			}
			if(right>leftPoint){
				quickSort(result, leftPoint, right);
			}
		return result;
	}
}
