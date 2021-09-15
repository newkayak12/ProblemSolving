package programmers.kakaoLv1.failureRate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class FailureRate {
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
