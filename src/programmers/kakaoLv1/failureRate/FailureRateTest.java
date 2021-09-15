package programmers.kakaoLv1.failureRate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FailureRateTest {
	@Test
	 void solu(){
		

		Assertions.assertArrayEquals(new int[]{3, 4, 2, 1, 5}, FailureRate.solution2(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3}));
		Assertions.assertArrayEquals(new int[]{4, 1, 2, 3}, FailureRate.solution2(4, new int[]{4, 4, 4, 4, 4}));
		Assertions.assertArrayEquals(new int[]{7, 6, 5, 4, 3, 2, 1, 8}, FailureRate.solution2(8, new int[]{1, 2, 3, 4, 5, 6, 7}));
	}

	@Test
	void sol(){
		Assertions.assertArrayEquals(new int[]{3, 4, 2, 1, 5}, FailureRate.solution(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3}));
		Assertions.assertArrayEquals(new int[]{4, 1, 2, 3}, FailureRate.solution(4, new int[]{4, 4, 4, 4, 4}));
		Assertions.assertArrayEquals(new int[]{7, 6, 5, 4, 3, 2, 1, 8}, FailureRate.solution(8, new int[]{1, 2, 3, 4, 5, 6, 7}));
	}

	@Test
	void so(){
		FailRetry n = new FailRetry();
		Assertions.assertArrayEquals(new int[]{3, 4, 2, 1, 5}, n.solution(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3}));
		Assertions.assertArrayEquals(new int[]{4, 1, 2, 3}, n.solution(4, new int[]{4, 4, 4, 4, 4}));
		Assertions.assertArrayEquals(new int[]{7, 6, 5, 4, 3, 2, 1, 8}, n.solution(8, new int[]{1, 2, 3, 4, 5, 6, 7}));
	}

}
