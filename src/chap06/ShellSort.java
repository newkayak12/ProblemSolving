package chap06;

import java.util.Arrays;

public class ShellSort {
	public static void main(String[] args) {
		int[] arr = {8,1,4,2,7,6,3,5};
		ShellSort s = new ShellSort();
		System.out.println(Arrays.toString(s.shellSort(arr)));


	}

	public int[] shellSort(int[] arr){
		int[] result = arr;
		int n = result.length;
		

		for(int h=n/2; h>0; h/=2){
			for(int i =h; i<result.length; i++){
				int j = i-1;
				int target = result[i];
				while(j>=0&&target<result[j]){
					result[j+1]= result[j];
					j--;
				}

				result[j+1] = target;
			}

			
		}
		

		return result;
	}


}
