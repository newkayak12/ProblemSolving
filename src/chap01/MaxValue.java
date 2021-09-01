package chap01;

import java.util.Scanner;

public class MaxValue {
	public static void main(String[] args) {
		MaxValue v = new MaxValue();
		// v.getMaxValue();
		System.out.println("\n\n최대값 경우의 수");
		System.out.println("\n\nmax(3,2,1)"+v.getMaxValue2(3,2,1)); // a>b>c
		System.out.println("\n\nmax(3,2,2)"+v.getMaxValue2(3,2,2)); // a>b=c
		System.out.println("\n\nmax(3,1,2)"+v.getMaxValue2(3,1,2)); // a>c>b
		System.out.println("\n\nmax(3,2,3)"+v.getMaxValue2(3,2,3)); // a=c>b
		System.out.println("\n\nmax(2,1,3)"+v.getMaxValue2(2,1,3)); // c>a>b
		System.out.println("\n\nmax(3,3,2)"+v.getMaxValue2(3,3,2)); // a=b>c
		System.out.println("\n\nmax(3,3,3)"+v.getMaxValue2(3,3,3)); // a=b=c
		System.out.println("\n\nmax(2,3,1)"+v.getMaxValue2(2,3,1)); // b>c>a
		System.out.println("\n\nmax(2,3,2)"+v.getMaxValue2(2,3,2)); // a=c>b
		System.out.println("\n\nmax(1,3,2)"+v.getMaxValue2(1,3,2)); // a>c>b
		System.out.println("\n\nmax(2,3,3)"+v.getMaxValue2(2,3,3)); // a>b=c
		System.out.println("\n\nmax(1,2,3)"+v.getMaxValue2(1,2,3)); // c>b>a
// 결정 트리로 내놓으면 총 13가지의 경우의 수가 나온다. 


// 이를 활요해서 중앙값을 잡으면
		int middle = v.getCenterValue(10,40,20);
		System.out.println("중간 값은 "+middle+"\n" );

		//

		v.getMaxAndLowValue();
	}



	public void getMaxValue() {
		Scanner scn = new Scanner(System.in);
		int a, b, c;
		int max;

		System.out.println("세 정수");
		System.out.println("a :");
		a = scn.nextInt();

		System.out.println("b : ");
		b = scn.nextInt();

		System.out.println("c : ");
		c = scn.nextInt();

		max = a;

		if (b > max) {
			max = b;
		}

		if (c > max) {
			max = c;
		}

		System.out.println("최대값은 " + max + "입니다.");

		scn.close();
	}

	public int getMaxValue2(int a, int b, int c) {
		int max = a;

		if (b > max) {
			max = b;
		}
		if (c > max) {
			max = c;
		}
		return max;
	}

	public int getCenterValue(int a, int b, int c){
		if(a>=b){
			if(b>=c){
				return b;
			} else if( a>=c){
				return a;
			} else {
				return c;
			}
		} else if(a>c){
			return a;
		} else if(b>c){
			return c;
		} else {
			return b;
		}

	}

	public void getMaxAndLowValue(){
		int[] arr = {100,20,90,40,30,200,50};
		for(int i=0; i<arr.length; i++){
			for(int j=0; j<arr.length; j++){
				if(arr[i]>arr[j]){
					int temp = arr[i];
					arr[i]=arr[j];
					arr[j]=temp;
				}
			}
		}


		for(int piece : arr){
			System.out.println(piece);
		}

		System.out.println("최대값"+arr[0]);
		System.out.println("최소값"+arr[arr.length-1]);
	}
	
}
