package programmers;

import java.util.Arrays;

public class LL {
	public static void main(String[] args) {
		// int[][] arr1 = {{1,2},{2,3}};
		// int[][] arr2 = {{3,4},{5,6}};
		// solutions(arr1, arr2);
		
		// System.out.println("+++++++++++++++++++++");
		// int[][] arr3 = {{1},{2}};
		// int[][] arr4 = {{3},{4}};
		// solutions(arr3, arr4);
		// System.out.println();


		// System.out.println(solution2(626331));
		long i = 1;
		int question = 0;
		while(true){

			if(i%2!=0){
				System.out.println(i+":::::");
				if(i%2==0){
					System.out.println("??????????????????????????????");
					question++;
				}
				if(i%2!=1){
					System.out.println("EORROOEROEROEOEOREORERJKLASDfh k;wHJFnJKLAGHSDIlKJDHSJILaDHLK");
				}
				
				
			}
			i++;

			if(i>=8000000){
				System.out.println(question);
			}

		}
	}

	public static int[][] solutions(int[][] array1, int[][] array2) {
		int[][] answer = {};

		for(int i =0; i<array1.length; i++){ 
			for(int j =0; j < array1[i].length; j++ ){
				array1[i][j] = array1[i][j]+array2[i][j];
			}
		}
		return answer;
	    }


	    public static int solution2(int num) {
		int answer = 0;
		System.out.println(num);
		do{
		   if(num%2==0){
		       num = num/2;
		       answer ++;
		       System.out.println(num);
		   } else {
			num = num*3+1;
			answer ++;
			System.out.println(num);
		    }
		}while(num!=1);
		
		return answer;
	    }
}
