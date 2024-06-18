package programmers.lv1.kakaoLv1.failureRate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

public class FailureRate {
//https://school.programmers.co.kr/learn/courses/30/lessons/42889

 /**
 * <pre>
 * 슈퍼 게임 개발자 오렐리는 큰 고민에 빠졌다. 그녀가 만든 프랜즈 오천성이 대성공을 거뒀지만, 요즘 신규 사용자의 수가 급감한 것이다. 원인은 신규 사용자와 기존 사용자 사이에 스테이지 차이가 너무 큰 것이 문제였다.
 * 이 문제를 어떻게 할까 고민 한 그녀는 동적으로 게임 시간을 늘려서 난이도를 조절하기로 했다. 역시 슈퍼 개발자라 대부분의 로직은 쉽게 구현했지만, 실패율을 구하는 부분에서 위기에 빠지고 말았다. 오렐리를 위해 실패율을 구하는 코드를 완성하라.
 * 실패율은 다음과 같이 정의한다.
 * 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수
 * 전체 스테이지의 개수 N, 게임을 이용하는 사용자가 현재 멈춰있는 스테이지의 번호가 담긴 배열 stages가 매개변수로 주어질 때, 실패율이 높은 스테이지부터 내림차순으로 스테이지의 번호가 담겨있는 배열을 return 하도록 solution 함수를 완성하라.
 * 제한사항
 * 스테이지의 개수 N은 1 이상 500 이하의 자연수이다.
 * stages의 길이는 1 이상 200,000 이하이다.
 * stages에는 1 이상 N + 1 이하의 자연수가 담겨있다.
 * 각 자연수는 사용자가 현재 도전 중인 스테이지의 번호를 나타낸다.
 * 단, N + 1 은 마지막 스테이지(N 번째 스테이지) 까지 클리어 한 사용자를 나타낸다.
 * 만약 실패율이 같은 스테이지가 있다면 작은 번호의 스테이지가 먼저 오도록 하면 된다.
 * 스테이지에 도달한 유저가 없는 경우 해당 스테이지의 실패율은 0 으로 정의한다.
 * </pre>
 */

 @Nested
 class TestCases {
	 @Test
	 public void case1 () {
		 int N = 5;
		 int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
		 int[] result = {3, 4, 2, 1, 5};

		 Assertions.assertArrayEquals(result, solution(N, stages));
	 }

	 @Test
	 public void case2 () {
		 int N = 4;
		 int[] stages = {4, 4, 4, 4, 4};
		 int[] result = {4, 1, 2, 3};

		 Assertions.assertArrayEquals(result, solution(N, stages));
	 }
 }

 public static  int[] solution(int N, int[] stages) {
	 double[][] container = new double[N][2];
	 int[] answer = new int[N];

	 int size = stages.length;
	 for ( int i = 1; i <= N; i ++ ) {
		 int count = 0;
		 for (int j = 0; j < stages.length; j ++ ) {
			 if( i == stages[j] ) count++;
		 }

		 double[] element = new double[2];
		 element[0] = i;
		 if( size != 0 ) {
			 element[1] = count / (size * 1.0);
		 }
		 else if( size == 0 ) element[1] = 0.0;


		 container[i - 1] = element;
		 size -= count;

	 }

	 Arrays.sort(container, (o1, o2) -> {
		 double result =  (o1[1] - o2[1]);
		 if (result != 0.0) return result > 0 ? -1 : 1;
		 else {
			 return (int)(o1[0] - o2[0]);
		 }

	 });

	 for( int i = container.length - 1; i >= 0; i --) answer[i] = (int) container[i][0];

	 return answer;
 }

 static class Success {
	public static void main(String[] args) {
		// System.out.println(Arrays.toString(solution(5, new int[] {2,1,2,6,2,4,3,3})));
		// System.out.println("||\n");
		// System.out.println(Arrays.toString(solution(4, new int[] {4,4,4,4,4})));;
		// System.out.println("||\n");
		// System.out.println(Arrays.toString(solution(5, new int[] {2,1,2,4,2,4,3,3})));


		// solution2(20, new int[] {6,6,6,6,6, 6,6,6,6,6, 6,6,6,6,6, 6,6,6,6,6});
		// solution2(20, new int[] {1,1,1,1,1, 1,1,1,1,1, 1,1,1,1,1, 1,1,1,1,1});
		solution2(20, new int[] {1});

		// System.out.println(Arrays.toString(solution2(5, new int[] {2,1,2,6,2,4,3,3})));
		// System.out.println("||\n");
		// System.out.println(Arrays.toString(solution2(4, new int[] {4,4,4,4,4})));;
		// System.out.println("||\n");
		// System.out.println(Arrays.toString(solution2(5, new int[] {2,1,2,4,2,4,3,3})));
		// System.out.println("||\n");
		// System.out.println(Arrays.toString(solution2(5, new int[] {1,2,2,1,3})));

		/*
		5 : 1,1 :  2/5
		3 : 2,2 :  2/3
		1 : 3,  :  1/1

		*/
	}

	public static int[] solution(int N, int[] stages) {
		int[] answer = new int[N];
		int total = stages.length;
		double[][] temp = new double[N][2];

		for(int i = 1; i<=N; i++){
			int count = 0;
			double[] element = new double[2];
			for(int j = 0; j<stages.length; j++){
				if(i==stages[j]){
					count++;
				}
			}
			element[0] = i;
			if(total!=0){
				element[1] = (double)count/total;
			}
			if(total == 0){
				element[1] = 0;
			}
			temp[i-1] = element;
			total-=count;
		}
		Arrays.sort(temp, new Comparator<double[]>(){
			@Override
			public int compare(double[] o1, double[] o2) {
				// TODO Auto-generated method stub
				if(o2[1] != o1[1]){
					return (int)((o2[1]-o1[1])*1000000000);
				}

					return (int)(o1[0]-o2[0]);
			}
		});
		int tmpCount = 0;
		for(double[] p : temp){
			answer[tmpCount++] = (int)p[0];
		}
		return answer;
		}

	public static int[] solution2(int N, int[] stages) {
	int[] answer= new int[N];
	double[][] temp = new double[N][2];
	Arrays.sort(stages);
	int total = stages.length;
	for(int i = 1; i<=N; i++){
		int count = 0;
		for(int piece : stages){
			if(i==piece){
				count++;
			}
		}
		temp[i-1][0] = i;
		if(total != 0 && count != 0 ){
			temp[i-1][1] = ((double)count/total);
			total-=count;
		} else if(total == 0 || count == 0){
			temp[i-1][1] = 0;
		}

	}
	Arrays.sort(temp, new Comparator<double[]>(){

		@Override
		public int compare(double[] o1, double[] o2) {
			// TODO Auto-generated method stub
			// if(o1[1] != o2[1]) {
			// 	return (int)((o2[1] - o1[1])*100000000);
			// }
			// return (int)(o1[0]-o2[0]);

			if(o1[1]<o2[1]){
				return 1;
			} else if(o1[1]>o2[1]){
				return -1;
			} else {
				if(o1[0]> o2[0]){
					return 1;
				} else if(o1[0] < o2[0]){
					return -1;
				}
			}

			return 0;
		}

	});
	int count = 0;
	for(double[] piece : temp){
		answer[count++] = (int)piece[0];
	}

	return answer;
	}
 }
}
