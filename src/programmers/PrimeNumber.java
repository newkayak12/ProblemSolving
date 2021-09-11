package programmers;

import java.util.ArrayList;

public class PrimeNumber {
	public static void main(String[] args) {
		// System.out.println(solution(100000));
		// System.out.println(solution2(10));
		// System.out.println(solution2(5));
		// System.out.println(solution2(11));
		System.out.println(solution3(10));
		System.out.println(solution3(5));
		System.out.println(solution3(11));
	}

	public static int solution(int n) {
		ArrayList<Integer> num = new ArrayList<>();
		for(int i = 2; i<=n; i++){
		    int count = 0;
		    for(int j = 2; j<=i; j++){
			if(i%j==0){
			    count++;
			}
		    }
		    if(count == 1){
			num.add(i);
		    }
		}
		return num.size();
	    }




	public static int solution2(int n){
		int answer = 0;
      boolean[] sosu =new boolean [n+1];
      
      for(int i=2; i<=n ; i++)
          sosu[i]=true;
       //에라테네스   
      int root=(int)Math.sqrt(n);
      
     for(int i=2; i<=root; i++){
         if(sosu[i]==true){
             for(int j=i; i*j<=n; j++)
                    sosu[i*j]=false;
         }      
     }
      for(int i =2; i<=n; i++)  { 
          if(sosu[i]==true)
          answer++;
      }
      return answer;
	}



	public static int solution3(int n){
		boolean[] primeLists = new boolean[n+1];
		primeLists[0] = false;
		primeLists[1] = false;
		for(int i=2; i<=n; i++){
			primeLists[i] = true;
		}
		for(int i =2; (i*i)<=n; i++){
			if(primeLists[i]){
				for(int j = (i*i); j<=n; j+=i){
					primeLists[j] =  false;
				}	
			}
		}

		// (int)primeLists.stream().filter(x->x.equals(true)).count();
		int count = 0;
		for(boolean a : primeLists){
			if(a){
				count++;
			}
		}
		return count ;
	}
}

// 15 2,3,5,7,11,13
// 10 2,3,5,7
// 7  2,3,5,7
// 6  2,3,5
// 5  2,3,5

/*
	2>  1
	3>  2
	4>  2
	5>  3
	6>  3
	7>  4
	8>  4
	9>  4
	10> 4
	11> 5
	12> 5
	13> 6
	14> 6
	15> 6
	16> 6
	17> 7
	18> 7
	19> 8
	20> 8
*/
