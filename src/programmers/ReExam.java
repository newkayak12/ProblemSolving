package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ReExam {
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
		int[] answer = {};
		int[] studentA = {1,2,3,4,5};
		int[] studentB = {2,1,2,3,2,4,2,5};
		int[] studentC = {3,3,1,1,2,2,4,4,5,5};
		ArrayList<Integer> writeA =new ArrayList<>();
		ArrayList<Integer> writeB =new ArrayList<>();
		ArrayList<Integer> writeC =new ArrayList<>();
		int countA = 0;
		int countB = 0;
		int countC = 0;
		while( writeA.size()  < answers.length && writeB.size() <  answers.length && writeC.size() <  answers.length){
			if(countA==studentA.length){
				countA =0;
			}
			if(countB==studentB.length){
				countB =0;
			}
			if(countC==studentC.length){
				countC =0;
			}

			if(writeA.size()  < answers.length){
				writeA.add(studentA[countA++]);
			}
			if(writeB.size()  < answers.length){
				writeB.add(studentB[countB++]);
			}

			if(writeC.size()  < answers.length){
				writeC.add(studentC[countC++]);
			}

		
		}
		countA = 0;
		countB = 0;
		countC = 0;
		
		for(int i =0; i<answers.length; i++){
			if(writeA.get(i) == answers[i]){
				countA++;
			}
			if(writeB.get(i) == answers[i]){
				countB++;
			}
			if(writeC.get(i) == answers[i]){
				countC++;
			}
		}
		int[][] temp = new int[3][2];
		temp[0][0] = 1;
		temp[0][1] = countA;
		temp[1][0] = 2;
		temp[1][1] = countB;
		temp[2][0] = 3;
		temp[2][1] = countB;

		Arrays.sort(temp, new Comparator<int[]>(){

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o2[1] - o1[1];
			}
			
		});

		ArrayList<Integer> ttemp = new ArrayList<>();
		if(countA!=0){
			ttemp.add(temp[0][0]);
		}
		if(countB!=0){
			ttemp.add(temp[1][0]);
		}
		if(countC!=0){
			ttemp.add(temp[2][0]);
		}
		answer = ttemp.stream().mapToInt(Integer::intValue).toArray();
		return answer;
	}

	public static int[] solution2(int[] answers) {
		int[] answer = {};
		String answerA = "12345";
		String answerB = "21232425";
		String answerC = "3311224455";

		int aCount = 0; 
		int bCount = 0;	
		int cCount = 0;	
		int[] score = new int[3];

		for(int piece : answers){
			if(aCount==answerA.length()){
				aCount = 0;
			}
			if(bCount==answerB.length()){
				bCount = 0;
			}
			if(cCount==answerC.length()){
				cCount = 0;
			}

			if(Character.getNumericValue(answerA.charAt(aCount++))==piece){
				score[0]++;
			}
			if(Character.getNumericValue(answerB.charAt(bCount++))==piece){
				score[1]++;
			}
			if(Character.getNumericValue(answerC.charAt(cCount++))==piece){
				score[2]++;
			}


		}
		
		ArrayList<Integer> leadBoard = new ArrayList<>();
		int max = Arrays.stream(score).max().getAsInt();
		for(int i = 1; i<=score.length; i++){
			if(max == score[i-1]){
				leadBoard.add(i);
			}
		}

		Collections.sort(leadBoard);

		answer = leadBoard.stream().mapToInt(Integer::intValue).toArray();
		return answer;

	}
}
