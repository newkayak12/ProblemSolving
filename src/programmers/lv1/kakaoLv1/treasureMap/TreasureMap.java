package programmers.lv1.kakaoLv1.treasureMap;

import java.util.Arrays;

public class TreasureMap {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(5, new int[] {9, 20, 28, 18, 11}, new int[] {30, 1, 21, 17, 28})));
	}

	public static String[] solution(int n, int[] arr1, int[] arr2) {
		String[] answer = new String[n];
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i<arr1.length; i++){
			String[] firstNumber = toBinary(arr1[i], n);
			String[] secondNumber = toBinary(arr2[i], n);

			System.out.println("fir : " + Arrays.toString(firstNumber));
			System.out.println("sec : " + Arrays.toString(secondNumber));
			for(int j = 0; j<firstNumber.length; j++){
				if(firstNumber[j].equals(secondNumber[j]) && firstNumber[j].equals("0")){
					builder.append(" ");
				} else {
					builder.append("#");
				}
				
			}
			answer[i] = builder.toString();
			builder.setLength(0);

		}
		return answer;
	    }
	public static String[] toBinary(int number, int n ){
		StringBuilder sb = new StringBuilder();

		while(number!=0){
			sb.append(number%2+"");
			number/=2;
		}

		while(sb.length()!=n){
			sb.append("0");
		}
		sb.reverse();

		return sb.toString().split("");
	}

}
