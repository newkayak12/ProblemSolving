package chap01;

import java.util.Scanner;

public class Demorgan {
	public static void main(String[] args) {
		Demorgan d = new Demorgan();
		d.NumVerify();
	}

	public void NumVerify(){
		Scanner scn = new Scanner(System.in);
		System.out.println("su");
		int num = scn.nextInt();
			if(!(num<10 || num>99)){
				System.out.println(num+"은 두 자리 양수 입니다.");
			}

		scn.close();
	}
}
