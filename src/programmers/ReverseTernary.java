package programmers;

public class ReverseTernary {
	public static void main(String[] args) {
		System.out.println(solution(45));
	}	
	
	public static int solution(int n) {
		int answer = 0;
		int number = n;
		StringBuilder sb = new StringBuilder();
		while(number!=0){
			System.out.println(number);
			if(number>=3){
				sb.append(number%3);
				number/=3;
			} else {
				sb.append(number);
				number=0;
			}
		}
		String[] element = sb.toString().split("");
		for(int i =0; i<element.length; i++){
			answer += (int) Math.pow(3, (double)element.length-1-i)*Integer.parseInt(element[i]);
		}
		return answer;
	}
}
