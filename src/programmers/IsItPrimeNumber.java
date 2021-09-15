package programmers;

public class IsItPrimeNumber {
	public static void main(String[] args) {
		// System.out.println(solution(new int[]{1,2,3,4}));
		System.out.println(solution(new int[]{1,2,7,6,4}));
	}

	public static int solution(int[] nums) {
		int answer = 0;
		for(int i = 0; i<nums.length; i++){
			
			for(int j = i; j<nums.length; j++){
				if(i!=j){
					for(int z = j; z<nums.length; z++){
						if(i!=z && j!=z){
							int number = nums[i] + nums[j] + nums[z];
							if(isPrime(number)){
								answer++;
							}
						}
					}
				}
			}
		}

		
		return answer;
	}

	public static boolean isPrime(int num){
		boolean value = true;
		for(int i = 2; i<num; i++){
			if(num%i==0){
				value = false;
				break;
			}
		}
		return value;
	}
}
			