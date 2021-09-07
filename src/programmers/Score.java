package programmers;

public class Score {
	public static void main(String[] args) {
		// System.out.println(solution(new int[][] {{100,90,98,88,65},{50,45,99,85,77},{47,88,95,80,67},{61,57,100,80,65}})); 
		System.out.println(solution(new int[][] {{50,50},{90,87}})); 
	}
	public static String solution(int[][] scores) {
		String answer = "";
		double[] sum = new double[scores.length];
		int[] minus = new int[scores.length];
		for(int i =0; i<scores.length; i++){
			for(int p : scores[i]){
				sum[i] += p;
			}
		}

		for(int i = 0; i<scores.length; i++){
			int my = scores[i][i];
			boolean check = true;
			for(int j = 0; j<scores.length; j++){
				if(i!=j&&my<scores[i][j]){
					check = false;
				}
			}

			if(check){
				minus[i] = my;
			}
		}

		for(int a = 0; a<sum.length; a++ ){
			sum[a] -= minus[a];
			if(minus[a]!=0){
				sum[a]/=minus.length-1;
			} else {
				sum[a]/=minus.length;
			}
		}
	

		for(double b : sum){
			if(b>=90){
				answer+="A";
			} else if(b>=80 && b<90){
				answer+="B";
			} else if(b>=70 && b<80 ){
				answer+="C";
			} else if(b>=50 && b<70){
				answer+="D";
			} else if(b<50){
				answer+="F";
			}
		}
		return answer;
	    }
}
