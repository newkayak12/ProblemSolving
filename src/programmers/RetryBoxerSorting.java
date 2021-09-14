package programmers;

import java.util.Arrays;
import java.util.Comparator;

public class RetryBoxerSorting {
	public static void main(String[] args) {
		// System.out.println(Arrays.toString(solution(new int[] {50,82,75,120}, new String[] {"NLWL", "WNLL", "LWNW", "WWLN"})));
		// System.out.println(Arrays.toString(solution(new int[] {60,70,60}, new String[] {"NNN", "NNN", "NNN"})));
		
		System.out.println("1");
		System.out.println(Arrays.toString(solution2(new int[] {50,82,75,120}, new String[] {"NLWL", "WNLL", "LWNW", "WWLN"})));

		System.out.println("2");
		System.out.println(Arrays.toString(solution2(new int[] {145,92,86}, new String[] {"NLW", "WNL", "LWN"})));
		
		System.out.println("3");
		System.out.println(Arrays.toString(solution2(new int[] {60,70,60}, new String[] {"NNN", "NNN", "NNN"})));


	}

	public static int[] solution2(int[] weights, String[] head2head) {
		int[] answer = new int[weights.length];
		Boxers[] boxer = new Boxers[weights.length];
		for(int i = 0; i<head2head.length; i++){
			boxer[i] = new Boxers(i, head2head[i].split(""), weights);
		}
		Arrays.sort(boxer,new Comparator<Boxers>(){

			@Override
			public int compare(Boxers o1, Boxers o2) {
				// TODO Auto-generated method stub
				if(o1.winRate!=o2.winRate){
					return (int) (o2.winRate-o1.winRate);
				}
				if(o1.winBigger!=o2.winBigger){
					return o2.winBigger-o1.winBigger;
				}
				if(o1.weight!=o2.weight){
					return o2.weight-o1.weight;
				}
				return o1.idx-o2.idx;
			}
			
		});

		
		for(int i = 0; i<answer.length; i++){
			answer[i] = (boxer[i].idx+1);
			System.out.println( boxer[i] );
		}

		return answer;
	}
}
class Boxers{
	int weight = 0;
	int idx = 0;
	int winCount = 0;
	int winBigger = 0;
	double winRate = 0;
	String[] history= {};

	public Boxers(int idx, String[] history, int[] weigths){
		this.idx= idx;
		this.weight = weigths[idx];
		this.history = history;
		int nCounter = 0;
		for(int i =0; i<history.length; i++){
			if(idx!=i && weight<weigths[i] && history[i].equals("W")){
				winBigger++;
			}
			if(history[i].equals("W")){
				winCount++;
			}
			if(history[i].equals("N")){
				nCounter++;
			}
		}
		if(nCounter!=history.length){
			winRate =  ((double)winCount/(history.length-nCounter))*1000000  ;
		} else {
			winRate = 0;
		}
	}

	
}

