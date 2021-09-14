package programmers.kakaoLv1.dart;


public class Dart {
	public static void main(String[] args) {
		System.out.println("1==");
		System.out.println(solution("1S2D*3T"));
		System.out.println("2==");
		System.out.println(solution("1D2S#10S"));
		System.out.println("3==");
		System.out.println(solution("1D2S0T"));
		System.out.println("4==");
		System.out.println(solution("1S*2T*3S"));
		System.out.println("5==");
		System.out.println(solution("1D#2S*3S"));
		System.out.println("6==");
		System.out.println(solution("1T2D3D#"));
		System.out.println("7==");
		System.out.println(solution("1D2S3T*"));
	}
	
	public static int solution(String dartResult) {
		int answer = 0;
		int[] score = new int[3];
		int count = 0;
		int number = 0;
		for(int i = 0; i<dartResult.length(); i++){
			char charValue = dartResult.charAt(i) ;
			if(Character.isDigit(charValue)){
				if(charValue=='1'&&dartResult.charAt(i+1)=='0'){
					number = 10;
					i++;
					
				} else {
					number = Character.getNumericValue(charValue);
				}
			} else {
				switch (charValue) {
					case 'S':
						score[count++]= (int)Math.pow(number, 1);
						break;
					case 'D':
						score[count++]= (int)Math.pow(number, 2);
						break;
					case 'T':
						score[count++]= (int)Math.pow(number, 3);
						break;
					case '*':
						if(count-1==0){
							score[0] *=2;
						} else {
							score[count-2] *=2;
							score[count-1] *=2;
						}
						break;
				
					case '#':
						score[count-1]*=-1;
						break;
				}
				
			}

		}
		for(int piece : score){
			answer+=piece;
		}
		return answer;
	    }
}
