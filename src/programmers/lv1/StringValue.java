package programmers.lv1;

public class StringValue {
	public static void main(String[] args) {
		System.out.println(solution("a34"));
		System.out.println(solution("123a2"));
	}
	public static boolean solution(String s) {
		boolean answer = true;
		if(s.length()==4 || s.length()==6){
			String[] element = s.split("");
			for(String a : element){
			try{
				Integer.parseInt(a);
			} catch(NumberFormatException e){
				return false;
			}
			}
			return answer;
		} else {
			return false;
		}
	}
}
