package programmers;

import java.util.HashMap;

public class Ponkemon {
	public static void main(String[] args) {
		System.out.println(solution(new int[] {3,1,2,3}));
		System.out.println(solution(new int[] {3,3,3,2,2,4}));
		System.out.println(solution(new int[] {3,3,3,2,2,2}));
	}

	public static int solution(int[] nums){
		int selectCount = nums.length/2;
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int piece : nums){
			map.put(piece, piece);
		}

		int count = map.size();
		int answer = 0;
		if(selectCount>count){
			answer = count;
		} else if( selectCount<count){
			answer = selectCount;
		} else if( selectCount==count){
			answer = count;
		}

		return answer;
	}
}
