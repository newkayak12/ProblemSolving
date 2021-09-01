package chap01;

import java.util.Scanner;

public class SumValue {
	public static void main(String[] args) {
		SumValue v = new SumValue();
		// v.oneToSeven();
		// v.gausSum();
		v.sumBetween();
	}

	public void oneToSeven(){
		Scanner scn = new Scanner(System.in);
		System.out.println("수를 입력");
		int result = 0;
		StringBuilder str = new StringBuilder();
		int n = scn.nextInt();
		for(int i = 0; i<n; i++){
			result +=(i+1);
			if(i == n-1){
				str.append((i+1+" = "+ result));
			} else {
				
				str.append((i+1)+"+");
			}

			
		}

		System.out.println(str);
		scn.close();
	}

	public void gausSum(){
		Scanner scn = new Scanner(System.in);
		System.out.println("su");
		int num = scn.nextInt();

		System.out.println("result : " +((1+num)*num/2) );
		scn.close();
	}

	public void sumBetween(){
		Scanner scn = new Scanner(System.in);
		System.out.println("first");
		int first = scn.nextInt();
		System.out.println("second");
		int second = scn.nextInt();
		int temp =0 ;
		
		if(first>second){
			temp = first;
			first = second;
			second = temp;

			temp =0;
		}

		for(int i = first; i<=second; i++){
			temp += i;
		}

		System.out.println("result : "+temp);
	}

}
