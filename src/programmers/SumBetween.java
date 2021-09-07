package programmers;

public class SumBetween {
	public static void main(String[] args) {
		System.out.println(solution(3, 5));
		System.out.println(solution(3, 3));
		System.out.println(solution(5, 3));
		System.out.println(solution(-10000000 , 10000000));
	}
	
	public static long solution(int a, int b) {
		long answer = 0;
		int big = 0;
		int small = 0;
		if(a>b){
			big = a;
			small = b;
		} else if(a<b){
			big = b;
			small = a;
		} else if (a==b){
			return a;
		}

		for(int i = small; i<=big; i++){
			answer +=i;
		}
		return answer;
	    }

}
