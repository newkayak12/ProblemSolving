package programmers;

import java.util.ArrayList;
import java.util.Arrays;

public class Exam {
	public static void main(String[] args) {
		// System.out.println(Arrays.toString(solution(new int[] {1,2,3,4,5})));
		// System.out.println("\n");
		// System.out.println(Arrays.toString(solution(new int[] {1,3,2,4,2})));
		// System.out.println("\n");
		// System.out.println(Arrays.toString(solution(new int[] {1,3,2,4,2,1,2,3,2,3,1,3,2,3,1,4,1,2,5,1,2,3,4,5,1,3,2,4,1,2})));
		
		System.out.println(Arrays.toString(solution2(new int[] {1,2,3,4,5})));
		System.out.println("\n");
		System.out.println(Arrays.toString(solution2(new int[] {1,3,2,4,2})));
		System.out.println("\n");
		System.out.println(Arrays.toString(solution2(new int[] {1,3,2,4,2,1,2,3,2,3,1,3,2,3,1,4,1,2,5,1,2,3,4,5,1,3,2,4,1,2})));
		
	}
	public static int[] solution(int[] answers) {
		int[] patternA = {};
		int[] patternB = {};
		int[] patternC = {};

		String patA = "12345";
		String patB = "21232425";
		String patC = "3311224455";

		// System.out.println("answer : "+answers.length+" A : "+patternA.length+" B : "+patternB.length+" C : "+patternC.length);
		// System.out.println("A : " +answers.length/patA.length()+" B : " +answers.length/patB.length()+" C : " +answers.length/patC.length());
		int aleng = patA.length();
		int bleng = patB.length();
		int cleng = patC.length();
		for(int i =0; i<answers.length/aleng; i++){
			patA +=patA;
		}
		for(int i =0; i<answers.length/bleng; i++){
			patB +=patB;
		}
		for(int i =0; i<answers.length/cleng; i++){
			patC +=patC;
		}
		System.out.println("answer : "+answers.length+" A : "+patternA.length+" B : "+patternB.length+" C : "+patternC.length);
		
		patternA = Arrays.stream(patA.split("")).mapToInt(Integer::parseInt).toArray();
		patternB = Arrays.stream(patB.split("")).mapToInt(Integer::parseInt).toArray();
		patternC = Arrays.stream(patC.split("")).mapToInt(Integer::parseInt).toArray();
		

		int scoreA = 0;
		int scoreB = 0;
		int scoreC = 0;
		for(int i =0; i<answers.length; i++){
			if(answers[i]==patternA[i]){
				scoreA++;
			}
			if(answers[i]==patternB[i]){
				scoreB++;
			}
			if(answers[i]==patternC[i]){
				scoreC++;
			}
		}
		int[] result = {scoreA, scoreB, scoreC};
		int max = Arrays.stream(result).max().getAsInt();
		ArrayList<Integer> rr = new ArrayList<>();
		for(int i =0; i<result.length; i++){
			if(max==result[i]){
				rr.add(i+1);
			}
		}

		return rr.stream().mapToInt(Integer::intValue).toArray();
	    }	


	    public static int[] solution2(int[] answers) {

		return new int[] {};
	    }
}
