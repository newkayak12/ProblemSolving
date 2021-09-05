package chap08;

public class BruteForce {
	public static void main(String[] args) {
		BruteForce b = new BruteForce();
		// System.out.println(b.bruteForce("ababcdefgha", "abc"));
		System.out.println(b.bruteForce2("ababcdefgha", "abc"));
	}

	public boolean bruteForce(String target, String key){
		for(int i = 0; i<target.length()-key.length(); i++){
			if(( target.substring(i, i+key.length()).equals(key) )){
				System.out.println("count : "+(i+1));
				return true;
			}
		}

		return false;
	}


	public boolean bruteForce2(String target, String key){
		char[] t = target.toCharArray();
		char[] k = key.toCharArray();

		for(int i =0; i<t.length-k.length; i++){
			int flag = 0;
			for(int j = 0; j<k.length; j++){
				 if(t[i+j]==k[j]){
					flag += 1;
				 }
			}

			if(flag==k.length){
				System.out.println("count : "+ flag);
				return true;
			}
		}

		return false;
	}
}
