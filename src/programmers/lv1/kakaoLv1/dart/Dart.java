package programmers.lv1.kakaoLv1.dart;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Dart {
//https://school.programmers.co.kr/learn/courses/30/lessons/17682

	/**
	 * <pre>
	 * 카카오톡 게임별의 하반기 신규 서비스로 다트 게임을 출시하기로 했다. 다트 게임은 다트판에 다트를 세 차례 던져 그 점수의 합계로 실력을 겨루는 게임으로, 모두가 간단히 즐길 수 있다.
	 * 갓 입사한 무지는 코딩 실력을 인정받아 게임의 핵심 부분인 점수 계산 로직을 맡게 되었다. 다트 게임의 점수 계산 로직은 아래와 같다.
	 * 다트 게임은 총 3번의 기회로 구성된다.
	 * 각 기회마다 얻을 수 있는 점수는 0점에서 10점까지이다.
	 * 점수와 함께 Single(S), Double(D), Triple(T) 영역이 존재하고 각 영역 당첨 시 점수에서 1제곱, 2제곱, 3제곱 (점수1 , 점수2 , 점수3 )으로 계산된다.
	 * 옵션으로 스타상(*) , 아차상(#)이 존재하며 스타상(*) 당첨 시 해당 점수와 바로 전에 얻은 점수를 각 2배로 만든다. 아차상(#) 당첨 시 해당 점수는 마이너스된다.
	 * 스타상(*)은 첫 번째 기회에서도 나올 수 있다. 이 경우 첫 번째 스타상(*)의 점수만 2배가 된다. (예제 4번 참고)
	 * 스타상(*)의 효과는 다른 스타상(*)의 효과와 중첩될 수 있다. 이 경우 중첩된 스타상(*) 점수는 4배가 된다. (예제 4번 참고)
	 * 스타상(*)의 효과는 아차상(#)의 효과와 중첩될 수 있다. 이 경우 중첩된 아차상(#)의 점수는 -2배가 된다. (예제 5번 참고)
	 * Single(S), Double(D), Triple(T)은 점수마다 하나씩 존재한다.
	 * 스타상(*), 아차상(#)은 점수마다 둘 중 하나만 존재할 수 있으며, 존재하지 않을 수도 있다.
	 * 0~10의 정수와 문자 S, D, T, *, #로 구성된 문자열이 입력될 시 총점수를 반환하는 함수를 작성하라.
	 * 입력 형식
	 * "점수|보너스|[옵션]"으로 이루어진 문자열 3세트.
	 * 예) 1S2D*3T
	 * 점수는 0에서 10 사이의 정수이다.
	 * 보너스는 S, D, T 중 하나이다.
	 * 옵선은 *이나 # 중 하나이며, 없을 수도 있다.
	 * </pre>
	 */

	@Nested
	public class TestCases {
		@Test
		@DisplayName("1S2D*3T - 37")
		void case1 () {
			String dartResult = "1S2D*3T";
			int result = 37;

			Assertions.assertEquals(result, solution(dartResult));
		}
		@Test
		@DisplayName("1D2S#10S - 9")
		void case2 () {
			String dartResult = "1D2S#10S";
			int result = 9;
			Assertions.assertEquals(result, solution(dartResult));
		}
		@Test
		@DisplayName("1D2S0T - 3")
		void case3 () {
			String dartResult = "1D2S0T";
			int result = 3;
			Assertions.assertEquals(result, solution(dartResult));
		}
		@Test
		@DisplayName("1S*2T*3S - 23")
		void case4 () {
			String dartResult = "1S*2T*3S";
			int result = 23;
			Assertions.assertEquals(result, solution(dartResult));
		}
		@Test
		@DisplayName("1D#2S*3S - 5")
		void case5 () {
			String dartResult = "1D#2S*3S";
			int result = 5;
			Assertions.assertEquals(result, solution(dartResult));
		}
		@Test
		@DisplayName("1T2D3D# - -4")
		void case6 () {
			String dartResult = "1T2D3D#";
			int result = -4;
			Assertions.assertEquals(result, solution(dartResult));
		}
		@Test
		@DisplayName("1D2S3T* - 59")
		void case7 () {
			String dartResult = "1D2S3T*";
			int result = 59;
			Assertions.assertEquals(result, solution(dartResult));
		}
	}

	public  int solution(String dartResult) {


		int[] numbers = new int[3];
		int idx = 0;
		for ( int i = 0; i < dartResult.length(); i ++ ) {
			char now = dartResult.charAt(i);

			if( Character.isDigit(now) ) {
				if( now == '1' && dartResult.charAt(i + 1) == '0') {
					i += 1;
					numbers[idx] = 10;
				} else {
					numbers[idx] = now - 48;
				}
				idx += 1;
			}

			if( Character.isAlphabetic(now)) {
				switch (now) {
					case 'S':
						numbers[idx - 1] = (int) Math.pow(numbers[idx - 1], 1);
						break;
					case 'D':
						numbers[idx - 1] = (int) Math.pow(numbers[idx - 1], 2);
						break;
					case 'T':
						numbers[idx - 1] = (int) Math.pow(numbers[idx - 1], 3);
						break;
				}
			}

			if( now == '#' || now == '*') {
				switch (now) {
					case '#':
						numbers[idx - 1] = numbers[idx - 1] * -1;
						break;
					case '*':{
						if( idx - 1 > 0 ) numbers[idx - 2] = numbers[idx - 2] * 2;
						numbers[idx - 1] = numbers[idx - 1] * 2;
					}
				}

			}

		}


		int answer = 0;
		for( int number : numbers ) answer += number;


		System.out.println(Arrays.toString(numbers));
		return answer;
	}


	public  int solutionSthngFail(String dartResult) {
		char[] dart = dartResult.toCharArray();
		int answer = 0;

		int[] numbers = new int[3];

		int index = 0;
		char score = 0;
		char operator = 0;

		numbers[index++] = dartResult.charAt(0) - 48;

		for( int i = 1; i < dart.length; i ++  ) {
			char now = dart[i];

			if( Character.isDigit(now) ) {
				numbers = this.calc( numbers, index, score, operator);
				score = 0;
				operator = 0;

				if( now == '1' && dart[i + 1] == '0') {
					numbers[index] = 10;
					i += 2;
				} else numbers[index ++] = now - 48;
			}



			if( now == 'S' || now == 'D' || now == 'T') {
				score = now;
			}
			if( now == '#' || now == '*') {
				operator = now;
			}
		}

		numbers = this.calc( numbers, index, score, operator);


		for ( int number : numbers ) {
			answer += number;
		}


		return answer;
	}
	private int[] calc( int[] numbers,int index, char score, char operator) {

		switch (score) {
			case 'S':
				numbers[index - 1] = numbers[index - 1];
				break;
			case 'D':
				numbers[index - 1] = (int) Math.pow(numbers[index - 1], 2.0);
				break;
			case 'T':
				numbers[index - 1] = (int) Math.pow(numbers[index - 1], 3.0);
				break;
		}

		switch (operator) {
			case '#':
				numbers[index - 1] *= -1;
				break;
			case '*':
				numbers[index - 1] *= 2;
				if( index - 1 > 0) numbers[index - 2] *= 2;
				break;
		}



		return numbers;
	}

	class Success {
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
			for (int i = 0; i < dartResult.length(); i++) {
				char charValue = dartResult.charAt(i);
				if (Character.isDigit(charValue)) {
					if (charValue == '1' && dartResult.charAt(i + 1) == '0') {
						number = 10;
						i++;

					} else {
						number = Character.getNumericValue(charValue);
					}
				} else {
					switch (charValue) {
						case 'S':
							score[count++] = (int) Math.pow(number, 1);
							break;
						case 'D':
							score[count++] = (int) Math.pow(number, 2);
							break;
						case 'T':
							score[count++] = (int) Math.pow(number, 3);
							break;
						case '*':
							if (count - 1 == 0) {
								score[0] *= 2;
							} else {
								score[count - 2] *= 2;
								score[count - 1] *= 2;
							}
							break;

						case '#':
							score[count - 1] *= -1;
							break;
					}

				}

			}
			for (int piece : score) {
				answer += piece;
			}
			return answer;
		}
	}
}
