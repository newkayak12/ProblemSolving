package programmers.lv1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DuplicateNumber {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] {1,1,3,3,0,1,1,3,3,3,3,3,3,3,1,3,3,3,1,1,3,3,3,3,3,3,3,3,3,33,1})));
		// System.out.println(Arrays.toString(solution2(new int[] {1,1,3,3,0,1,1,3,3,3,3,3,3,3,1,3,3,3,1,1,3,3,3,3,3,3,3,3,3,33,1})));
	}

	public static int[] solution(int []arr) {
		int[] answer = {};
		
		List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
		List<Integer> newOne = new ArrayList<>();
		newOne.add(list.get(0));
		list.sort(new Comparator<Integer>(){
			int count = 0;
			@Override
			public int compare(Integer o1, Integer o2) {
				if(o1!=o2){
					newOne.add(o1);
					count++;
				} else if( o1 == o2){
					if(newOne.get(count)!=o1){
						newOne.add(o1);
						count++;
					}
				}
				return 0;
			}
			
		});

		answer = newOne.stream().mapToInt(Integer::intValue).toArray();
		return answer;
	    }

	    public static int[] solution2(int []arr) {
		int[] answer = {};
		
		List<Integer> newOne = new ArrayList<>();
		int cutLine = 10;
		for(int num : arr){
			if(cutLine!=num){
				System.out.println("cl :"+cutLine+" : num: "+num);
				newOne.add(num);
				cutLine = num;
			}
		}
		answer = newOne.stream().mapToInt(Integer::intValue).toArray();
		return answer;
	    }
}
