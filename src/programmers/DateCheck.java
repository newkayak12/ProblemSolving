package programmers;

import java.time.LocalDate;

public class DateCheck {
	public static void main(String[] args) {
		System.out.println(solution(5, 24));
	}
	public static String solution(int a , int b){
		LocalDate date = LocalDate.of(2016, a, b);
		int dayOfYear = date.getDayOfYear();
		String answer = "";
		System.out.println(dayOfYear);
		System.out.println(dayOfYear%7);

		switch (String.valueOf(dayOfYear%7)) {
			case "1":
				answer = "FRI";
				break;
			case "2":
				answer ="SAT";
				break;
			case "3":
				answer ="SUN";
				break;
			case "4":
				answer = "MON";
				break;
			case "5":
				answer = "TUE";
				break;
			case "6":
				answer = "WED";
				break;
			case "0":
				answer = "THU";
				break;
		}
		return answer;
	}
	
}
