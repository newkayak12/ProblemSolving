package programmers.lv1.kakaoLv1.failureRate;

import java.util.Arrays;
import java.util.Comparator;

public class FailRetry {
	public static void main(String[] args) {
		FailRetry n = new FailRetry();
		System.out.println(Arrays.toString(n.solution(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3})));
	}
	
	public int[] solution(int N, int[] stages) {
		int[] answer = new int[N];
		int total = stages.length;
		Commander[] arr = new Commander[N];
		for(int i = 1; i<=N; i++){
			int count = 0;
			for(int a : stages){
				if(i<a){
					break;
				}
				if(i == a){
					count ++;
				}
			}
			arr[i-1] = new Commander(i, count, total);
			total -=count;

		}
		
		Arrays.sort(arr, new Comparator<Commander>(){

			@Override
			public int compare(Commander o1, Commander o2) {
				// TODO Auto-generated method stub
				if(o1.rate<o2.rate){
					return 1;
				} else if(o1.rate>o2.rate){
					return -1;
				} else {
					if(o1.idx > o2.idx){
						return 1;
					} else if(o1.idx < o2.idx){
						return -1;
					}
				}

				return 0;
			}
			
		});

		for(int i =0; i<arr.length; i++){
			answer[i] = arr[i].idx;			
		}
		return answer;
	}
}

class Commander {
	public int idx = 0;
	public double rate = 0;

	public Commander(int idx, int count, int total){
		this.idx = idx;
		this.rate = (double)count /total;
	}

	
}
