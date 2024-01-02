package programmers.lv1;

public class GcdPlus {
	public static void main(String[] args) {
		System.out.println(solution(13, 17));
	}
	public static int solution(int left, int right) {
		int answer = 0;
		for (int i = left; i<=right; i++){
			int count = 0;
			for(int j =1; j<=i; j++){
				if(i%j == 0){
					count++;
				}
			}
			if(count%2==0){
				answer+=i;
			} else if(count%2!=0){
				answer-=i;
			}

		}

		return answer;
	    }
}
