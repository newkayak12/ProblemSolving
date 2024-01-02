package programmers.lv1;

import java.util.ArrayList;
import java.util.Arrays;

public class ReReExam {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] {1,2,3,4,5})));
		System.out.println("\n");
		System.out.println(Arrays.toString(solution(new int[] {1,3,2,4,2})));
		System.out.println("\n");
		System.out.println(Arrays.toString(solution(new int[] {1,3,2,4,2,1,2,3,2,3,1,3,2,3,1,4,1,2,5,1,2,3,4,5,1,3,2,4,1,2})));
	}
	public static int[] solution(int [] answers){
		int[] tempA = {1,2,3,4,5};
		int[] tempB = {2,1,2,3,2,4,2,5};
		int[] tempC = {3,3,1,1,2,2,4,4,5,5};

		int[] writeA = new int[answers.length];
		int[] writeB = new int[answers.length];
		int[] writeC = new int[answers.length];

		int countA = 0; int countB = 0; int countC = 0;

		for(int i = 0; i<answers.length; i++){
			if(countA==tempA.length){
				countA=0;
			}
			if(countB==tempB.length){
				countB=0;
			}
			if(countC==tempC.length){
				countC=0;
			}

			writeA[i] = tempA[countA++];
			writeB[i] = tempB[countB++];
			writeC[i] = tempC[countC++];
		}

		int[] scores = new int[3];
		for(int i = 0; i<answers.length; i++){
			if(answers[i]==writeA[i]){ scores[0] ++; }
			if(answers[i]==writeB[i]){ scores[1] ++; }
			if(answers[i]==writeC[i]){ scores[2] ++; }
		}



		int max = Arrays.stream(scores).max().getAsInt();
		ArrayList<Integer> result = new ArrayList<>();
		for(int i = 0; i<scores.length; i++){
			if(max==scores[i]){
				result.add((i+1));
			}
		}

		scores =  result.stream().mapToInt(Integer::intValue).toArray();
		Arrays.sort(scores);

		return scores;
	}
}
