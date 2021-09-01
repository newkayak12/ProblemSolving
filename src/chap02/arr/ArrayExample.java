package chap02.arr;

import java.util.Scanner;

public class ArrayExample <T>{
	public static void main(String[] args) {
		ArrayExample<Integer> arr = new ArrayExample<Integer>();
		// arr.basicArray();

		// arr.ArrayReversal();
		// arr.toBinary(999999999);
		// arr.toHexaDecimal(99999999);
		arr.primeNumberVerify(2);
	}
	public void printArr(int[] array){
		for(int piece : array){
			System.out.print(piece +"/ ");
		}
		System.out.println("");
	}

	public void basicArray(){
		Scanner scn = new Scanner(System.in);

		int count = 5;
		int[] arr = new int[count];

		for(int i=0; i<count; i++){
			System.out.println((i+1)+"번쨰");
			arr[i] = scn.nextInt();
		}
		for(int piece : arr){
			System.out.println(piece+"이다.");
		}

		scn.close();
	}

	public void ArrayReversal(){
		int[] arr = {1,2,3,4,5};

		System.out.println("before ==");
		printArr(arr);
		int total = arr.length-1;
		for(int i =0; i<(arr.length/2); i++){
			int temp = arr[i];
			arr[i] = arr[total-i];
			arr[total-i]=temp;
		}

		System.out.println("\n\nafter ==");
		printArr(arr);
	}

	public void toBinary(int number){
		StringBuilder result = new StringBuilder();
		int nextNumber = number;
		do{
			if(nextNumber%2==0){
				result.append("0");
			} else if(nextNumber%2!=0){
				result.append("1");
			}
			nextNumber = nextNumber/2;
		}while(nextNumber!=0);
		
		
		char[] temp = (result.toString()).toCharArray();
		// result.reverse();
		int count = temp .length;
		for(int i=0; i<(temp.length/2); i++){
			char temporary = temp[i];
			temp[i] = temp[count-1-i];
			temp[count-1-i]= temporary;
		}
		String end = String.valueOf(temp);
		end = end+"(2)";
		System.out.println(number+" == "+end);
	}

	public void toHexaDecimal(long number){
		long temp = number;
		long numberCase = 0;
		StringBuilder str = new StringBuilder();
		do{	
			numberCase = temp%16;
			switch (String.valueOf(numberCase)) {
				case "10":
					str.append("A");
					break;
				case "11":
					str.append("B");
					break;
				case "12":
					str.append("C");
					break;
				case "13":
					str.append("D");
					break;
				case "14":
					str.append("E");
					break;
				case "15":
					str.append("F");
					break;
				default:
					str.append(numberCase);
					break;
			}
			temp = temp/16;
		}while(temp!=0);
		System.out.println(number +" = "+str.reverse()+"(16)");
	}

	public void  primeNumberVerify(int number){
		int temp = number;
		int primeCount =0;
		for(int i = 2; i<temp; i++){
			if(i!=temp&&temp%i==0){
			} else {
				primeCount +=1;
			}

		}
		if (primeCount>1){
			System.out.println("소수이다.");
		} else {
			System.out.println("소수가 아니다.");
		}
	}

}
