package programmers;

import java.net.Socket;

public class Money {
	public static void main(String[] args) {
		System.out.println(solution(3, 20, 4));
		System.out.println(solution(3, 200, 4));
	}
	public static long solution(int price, int money, int count) {
		long answer = -1;
		int p = 0;
		for(int i =1; i<=count; i++){
		    p += price*i;
		}
		
		if(p>money){
		    answer = Math.abs(money-p);
		    
		} else {
		    answer = 0;
		}
	
		return answer;
	    }
}
