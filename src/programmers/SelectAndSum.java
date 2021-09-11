package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class SelectAndSum {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] {2,1,3,4,1})));
	}
	
	public static int[] solution(int[] numbers) {
		ArrayList<Integer> progress = new ArrayList<>();
		HashMap<Integer,Integer> map = new HashMap<>();

		for(int i = 0; i<numbers.length; i++){
			for(int j = 0; j<numbers.length; j++){
				if(i!=j){
					int temp = numbers[i]+numbers[j];
					map.put(temp,temp);
				}
			}
		}
		Set<Integer> keyChain =  map.keySet();
		for(Integer piece : keyChain){
			progress.add(piece);
		}
		int[] answer = progress.stream().mapToInt(Integer::intValue).toArray();
		Arrays.sort(answer);

		return answer;
	    }
}
