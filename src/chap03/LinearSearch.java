package chap03;

public class LinearSearch {
	public static void main(String[] args) {
		int[] arr = {5,2,1,3,6,4,7,0,9,8};
		search(arr, 200);

	}

	static void search(int[] arr, int keyNumber){
		boolean flag = true;
		for(int i = 0; i< arr.length; i++){
			if(arr[i]==keyNumber){
				flag=false;
				break;
			}
		}

		if(flag){
			System.out.println("찾지 못했음");
		} else {
			System.out.println("찾음");
		}


	}
}
