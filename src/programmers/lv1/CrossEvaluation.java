package programmers.lv1;

import java.util.Arrays;

public class CrossEvaluation {
	
	// {{100,50,47,61,24},{90,45,88,57,90},{98,99,95,100,94},{88,85,80,80,75},{65,77,67,65,65}}
	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{100,90,98,88,65},{50,45,99,85,77},{47,88,95,80,67},{61,57,100,80,65},{24,90,94,75,65}}));
		System.out.println(solution(new int[][] {{50,90},{50,87}}));


	}

	public static String solution(int[][] scores){
		int[][] ref = new int[scores.length][scores.length];
		for(int i = 0; i<scores.length; i++){
			for(int j = 0; j<scores.length; j++){
				ref[i][j] = scores[j][i];
			}
		}

		for(int i = 0; i<ref.length; i++){
			int my = scores[i][i];
			Arrays.sort(ref[i]);
			
			if(ref[i][0] == my && ref[i][1] != ref[i][0]){
				ref[i][0] = -1;
			}
			if(ref[i][ref.length-1] == my && ref[i][ref.length-1] != ref[i][ref.length-2]){
				ref[i][ref.length-1] = -1;
			}
			
		}
		StringBuilder sb = new StringBuilder();
		for(int[] a : ref){
			double number = 0;
			int people = 0;
			
			for(int b : a)	{
				if(b!=-1){
					number+=b;
					people+=1;
				}
			}

			number/=people;

			if(number>=90){
				sb.append("A");
			}else if(number<90 && number>=80){
				sb.append("B");
			}else if(number<80 && number>=70){
				sb.append("C");
			}else if(number<70 && number>=50){
				sb.append("D");
			} else {
				sb.append("F");
			}
		}
		return sb.toString();
	}
} 