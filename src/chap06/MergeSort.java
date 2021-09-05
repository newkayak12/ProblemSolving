package chap06;

import java.util.Arrays;


public class MergeSort {
	// public static void main(String[] args)throws Exception {
	// 	MergeSort m = new MergeSort();
	// // 	// int[] arr1  = {2,4,6,8,11,13};
	// // 	// int[] arr2  = {1,2,3,4,9,16,21};
	// // 	// System.out.println(Arrays.toString(m.mergeSort(arr1, arr2)));

	// 	int[] arr = {3,2,54,6,78,8,21,13,4,7,5,9,1};
	// 	System.out.println("before : "+ Arrays.toString(arr));
	// 	System.out.println("after : "+Arrays.toString(m.mergeSort(arr)));

	// // 	// m.mergeSort(arr, 0, arr.length);
	// // 	// System.out.println(Arrays.toString(arr));

	// }
	// public int[] mergeSortTest(int[] arr){
	// 	int[] temp1 =  Arrays.copyOfRange(arr, 0, (0+arr.length-1)/2);
	// 	int[] temp2 = Arrays.copyOfRange(arr, (0+arr.length-1)/2, arr.length);
	// 	int[] result = new int[temp1.length+temp2.length];

	// 	// //temp1 _ insertion
	// 	// 	for(int i =1; i<temp1.length; i++){
	// 	// 		int j = i-1;
	// 	// 		int target = temp1[i];
	// 	// 		while(j>=0&&target<temp1[j]){
	// 	// 			temp1[j+1]=temp1[j];
	// 	// 			j--;
	// 	// 		}
	// 	// 		temp1[j+1] =target;

	// 	// 	}


	// 	// //temp2 _ bubble
	// 	// 	for(int i=0; i<temp2.length; i++){
	// 	// 		for (int j=0; j<temp2.length-1; j++){
	// 	// 			if(temp2[j]>temp2[j+1]){
	// 	// 				int t= temp2[j+1];
	// 	// 				temp2[j+1] = temp2[j];
	// 	// 				temp2[j]=t;
	// 	// 			}
	// 	// 		}
	// 	// 	}
			

	// 	int temp1Point = 0;
	// 	int temp2Point = 0;
	// 	int temp1Total = temp1.length;
	// 	int temp2Total = temp2.length;
	// 	int resultPoint = 0;

	// 		while(temp1Point<temp1Total && temp2Point<temp2Total){
	// 			result[resultPoint++] = (temp1[temp1Point]<=temp2[temp2Point])? temp1[temp1Point++]: temp2[temp2Point++];
	// 		}

	// 		while (temp1Point<temp1Total){
	// 			result[resultPoint++] = temp1[temp1Point++];
	// 		}
			
	// 		while (temp2Point<temp2Total){
	// 			result[resultPoint++] = temp1[temp2Point++];
	// 		}

	// 	return result;
	// 	}




	// public int[] mergeSort(int[] arr, int m, int middle, int n) {
	// 	int[] result = new int[arr.length];
	// 	int i = m;
	// 	int j = middle+1;
	// 	int k = m;

	// 	while(i<=middle && j<=n){
	// 		if(result[i]<=result[j]){

	// 		}
	// 	}
		
	// 		return arr;
		
	// }	
	
	// static int[] sorted = new int[8];


	// public static void merge(int a[], int m, int middle, int n) {
	// 	int i = m; 
	// 	int j = middle+1;  
	// 	int k = m; 	
	// 		while(i<=middle && j<=n) {
	// 			if(a[i]<=a[j]) { 
	// 				sorted[k] = a[i]; i++;
	// 			}else { 
	// 				sorted[k] = a[j]; j++;
	// 			} 
	// 			k++; 
	// 		}
			
	// 		if(i>middle) { 
	// 			for(int t=j; t<=n; t++,k++) {
	// 				sorted[k] = a[t];
	// 			} 
	// 		}else { 
	// 			for(int t=i;t<=middle;t++,k++) { 
	// 				sorted[k] = a[t]; 
	// 			} 
	// 		} 
			
	// 		for(int t=m;t<=n;t++) { 
	// 			a[t] = sorted[t]; 
	// 		}
			
	// 			System.out.println("병합 정렬 후: "+Arrays.toString(a)); 
	// 		} 

	// public static void mergeSort(int a[], int m, int n) { 
	// 	int middle; 
	// 	if(m<n) {
	// 		middle = (m+n)/2; 
	// 		mergeSort(a, m, middle);
	// 		mergeSort(a, middle+1, n); 
			
	// 		merge(a, m, middle, n); 
	// 	} 
	// }
	
	
	// public static void main(String[] args) { 
	// 	int[] list = {58,8,28,3,18,6,33,20};
	// 	int size = list.length; 
	// 	System.out.println("정렬 수행 전: "+Arrays.toString(list)); 
		
	// 	System.out.println("-----------------병합 정렬 수행 시작------------------");
	// 	mergeSort(list, 0, size-1); 
	// }


	
}
