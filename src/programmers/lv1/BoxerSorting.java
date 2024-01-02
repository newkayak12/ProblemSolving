package programmers.lv1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class BoxerSorting {
	public static void main(String[] args) {
		// System.out.println(Arrays.toString(solution(new int[] {50,82,75,120}, new String[] {"NLWL", "WNLL", "LWNW", "WWLN"})));
		// System.out.println(Arrays.toString(solution(new int[] {60,70,60}, new String[] {"NNN", "NNN", "NNN"})));
		
		
		// System.out.println(Arrays.toString(solution2(new int[] {50,82,75,120}, new String[] {"NLWL", "WNLL", "LWNW", "WWLN"})));
		// System.out.println(Arrays.toString(solution2(new int[] {145,92,86}, new String[] {"NLW", "WNL", "LWN"})));
		
		System.out.println(Arrays.toString(solution2(new int[] {60,70,60}, new String[] {"NNN", "NNN", "NNN"})));


	}


	/*
		복서 선수들의 몸무게 weights와, 복서 선수들의 전적을 나타내는 head2head가 매개변수로 주어집니다. 
		복서 선수들의 번호를 다음과 같은 순서로 정렬한 후 return 하도록 solution 함수를 완성해주세요.
		
		1. 전체 승률이 높은 복서의 번호가 앞쪽으로 갑니다. 아직 다른 복서랑 붙어본 적이 없는 복서의 승률은 0%로 취급합니다.
		
		2. 승률이 동일한 복서의 번호들 중에서는 자신보다 몸무게가 무거운 복서를 이긴 횟수가 많은 복서의 번호가 앞쪽으로 갑니다.
		
		3. 자신보다 무거운 복서를 이긴 횟수까지 동일한 복서의 번호들 중에서는 자기 몸무게가 무거운 복서의 번호가 앞쪽으로 갑니다.
		
		4. 자기 몸무게까지 동일한 복서의 번호들 중에서는 작은 번호가 앞쪽으로 갑니다.
	*/

	public static int[] solution(int[] weights, String[] head2head) {
		int[] answer = {};
		String[][] head2Double  = new String[head2head.length][head2head[0].length()];
		
		for(int i =0; i<head2head.length; i++){
			head2Double[i] = head2head[i].split("");
		}
		
		Arrays.sort(head2Double, new Comparator<String[]>(){
			int count  = 0;

			@Override
			public int compare(String[] o1, String[] o2) {
				// TODO Auto-generated method stub
				long o1Count = Arrays.stream(o1).filter(Predicate.isEqual("W")).count();
				long o2Count = Arrays.stream(o2).filter(Predicate.isEqual("W")).count();
				

				if(o2Count != o1Count){
					return (int)(o2Count-o1Count);
				} 
					System.out.println("same");
					return (int)(o2Count-o1Count);
				

				

			}
			
		});
		for(String[] a : head2Double){
			System.out.println(Arrays.toString(a));
		}
		
		return answer;
	    }

	    public static int[] solution2(int[] weights, String[] head2head) {
		Boxer[] boxerList = new Boxer[weights.length];
		for(int i =0; i<weights.length; i++){
			boxerList[i] = new Boxer(i,weights[i],head2head[i].split(""), weights);
		}
		Arrays.sort(boxerList,new Comparator<Boxer>(){

			@Override
			public int compare(Boxer o1, Boxer o2) {
				if(o1.winRate!=o2.winRate){
					return (int) (o2.winRate-o1.winRate);
				} else if(o1.winRate==o2.winRate){
					if(o2.winBigger!=o1.winBigger){
						return o2.winBigger-o1.winBigger;
					} else if(o2.winBigger==o1.winBigger){
						if(o2.weight!=o1.weight){
							return o2.weight-o1.weight;
						} else if(o2.weight==o1.weight){
							return o1.idx-o2.idx;
						}
					}
				}
				return 0;
			}
			
		});
		int[] answer = new int[weights.length];
		
		for(int i =0; i<answer.length; i++){
			answer[i] = boxerList[i].idx+1;
		}
		return answer;
	    }

	    




		
}

class Boxer{
	int idx;
	int weight;
	int winCount = 0;
	int winBigger = 0;
	double winRate = 0;
	String[] history;
	
	public Boxer(int idx, int weight, String[] history, int[] weights){
		this.idx = idx;
		this.weight = weight;
		this.history = history;
		
		int nCounter = 0;
		for(String piece : history){
			if(piece.equals("W")){
				winCount+=1;
			} else if (piece.equals("N")){
				nCounter-=1;
			}
			
		}

		if(history.length == Math.abs(nCounter)){
			nCounter=0;
		}
		this.winRate   = Math.floor(((double)winCount/(history.length+nCounter)*10000))/100;


		for(int i = 0; i<weights.length; i++){
			if(i!=idx && weight<weights[i] && history[i].equals("W")){
				winBigger+=1;
			}

		}

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return idx+" : "+weight+" : "+winCount+" : "+winRate+" : "+Arrays.toString(history) +" :___ "+winBigger;
	}

    }