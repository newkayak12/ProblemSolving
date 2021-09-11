package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class GypClothes {
	public static void main(String[] args) {
		// System.out.println(solution(5, new int[] {2,4}, new int[] {1,3,5}));
		// System.out.println("\n");
		// System.out.println(solution(5, new int[] {2,4}, new int[] {3}));
		// System.out.println("\n");
		// System.out.println(solution(3, new int[] {3}, new int[] {1}));
		// System.out.println("\n");
		// System.out.println(solution(5, new int[] {2,4}, new int[] {1,2,3,5}));

		System.out.println(solution2(5, new int[] {2,4}, new int[] {1,3,5}));
		System.out.println("\n");
		System.out.println(solution2(5, new int[] {2,4}, new int[] {3}));
		System.out.println("\n");
		System.out.println(solution2(3, new int[] {3}, new int[] {1}));
		System.out.println("\n");
		System.out.println(solution2(5, new int[] {2,4}, new int[] {1,2,3,5}));
	}
	public static int solution(int n, int[] lost, int[] reserve){
		int[] arr = new int[n];

		for(int piece : lost) {
			// System.out.println("lost "+piece);
			arr[piece-1] -= 1;
		}
		for(int piece : reserve){
			// System.out.println("reserve "+piece);
			arr[piece-1] += 1;
		}

		// System.out.println("fir : "+Arrays.toString(arr));
		for(int i = 0; i<arr.length; i++){
			int pre  = 0;
			int nex = 0;
			if(i!=0 && i!=arr.length-1){
				pre = arr[i-1];
				nex = arr[i+1];
			}

			if(arr[i] == -1 && (pre==1||nex==1)){
				if(pre == 1){
					arr[i-1] -= 1;
					arr[i] += 1;
					continue;
				} else if(nex == 1){
					arr[i+1] -= 1;
					arr[i] += 1;
					continue;
				}
			}
		}
		int count = 0;
		for(int a : arr){
			if(a<0){
				count++;
			}
		}

		return arr.length-count;
	}

	public static int solution2(int n, int[] lost, int[] reserve){
		int[] copy = new int [n];
		for(int a : lost){
			copy[a-1]  -=1;
		}
		for(int a : reserve){
			copy[a-1] +=1;
		}

		int pr = 0;
		for(int i = 0; i<copy.length; i++){
			if(i==0&&copy[1]==-1&&copy[0]==1){
				copy[1]+=1;
				copy[0]-=1;
			}
			if(i!=0 && i!=copy.length-1){
				if(pr == -1 && copy[i]==1){
					copy[i-1] +=1;
					copy[i] -=1;
				} else if(copy[i+1]==-1 && copy[i] ==1){
					copy[i+1] +=1;
					copy[i] -=1;
				}

			}
			if(i==copy.length-1&&copy[copy.length-2]==-1&&copy[copy.length-1]==1){
				copy[copy.length-2]+=1;
				copy[copy.length-1]-=1;
			}
			pr = copy[i];
		}
		
		int minus =0;
		for(int a :copy){
			if(a==-1){
				minus++;
			}
		}
		
		return copy.length-minus;
	}
}
