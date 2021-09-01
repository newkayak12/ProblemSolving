package chap03;



public class BinarySearch {
	
	
	public static void main(String[] args) {
		BinarySearch b = new BinarySearch();
		int[] arr = {1,2,3,5,6,7,8,9,10};
		b.binarySearch(arr, 6);
	}

	public void binarySearch(int[] arr, int key){
		 int start =0;
		 int end = arr.length-1;
		 int middle = 0;
		 do{
			middle = (start+end)/2;
			
			if(arr[middle]==key){
				System.out.println("found at "+ (middle+1));
				break;
			} else if(arr[middle]<key){
				start = middle+1;
			} else {
				end = middle-1;
			}

			
		 }while(start<end);
		 if(start == end){
			 System.out.println("i can't find");
		 }
	}
	
}
