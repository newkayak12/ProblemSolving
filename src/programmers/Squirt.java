package programmers;

public class Squirt {
	public static void main(String[] args) {
		System.out.println(solution(3));
	}
	
	public static long solution(long n) {
		long answer = 0;
		long num = 0;

			// for(long i=1; i<50000000000000L; i++){
			// 	if(Math.sqrt(n)==i){
			// 		num = i;
			// 		break;
			// 	} 
			// }
		for(long i = 1; i<=Math.sqrt(n); i++){
			if(Math.pow(i, 2) == n){
				num = i;
				break;
			} else {
				num = -1;
			}
		}
		

		if(num == -1 ){
			answer = num;
		} else {
			answer = (long)Math.pow(num+1, 2);
		}

		return answer;
	    }
}
