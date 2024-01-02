package programmers.lv1.kakaoLv1.failureRate;


public class FailureRateTest {
	 void solu(){
		

		System.out.println(new int[]{3, 4, 2, 1, 5}== FailureRate.solution2(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3}));
		System.out.println(new int[]{4, 1, 2, 3}== FailureRate.solution2(4, new int[]{4, 4, 4, 4, 4}));
		System.out.println(new int[]{7, 6, 5, 4, 3, 2, 1, 8}== FailureRate.solution2(8, new int[]{1, 2, 3, 4, 5, 6, 7}));
	}

	void sol(){
		System.out.println(new int[]{3, 4, 2, 1, 5}== FailureRate.solution(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3}));
		System.out.println(new int[]{4, 1, 2, 3}== FailureRate.solution(4, new int[]{4, 4, 4, 4, 4}));
		System.out.println(new int[]{7, 6, 5, 4, 3, 2, 1, 8}== FailureRate.solution(8, new int[]{1, 2, 3, 4, 5, 6, 7}));
	}

	void so(){
		FailRetry n = new FailRetry();
		System.out.println(new int[]{3, 4, 2, 1, 5} == n.solution(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3}));;
		System.out.println(new int[]{4, 1, 2, 3} == n.solution(4, new int[]{4, 4, 4, 4, 4}));
		System.out.println(new int[]{7, 6, 5, 4, 3, 2, 1, 8}== n.solution(8, new int[]{1, 2, 3, 4, 5, 6, 7}));
	}

}
