package chap05;

public class Recursive {
	

	public static void main(String[] args) {
		Recursive rec = new Recursive();

		System.out.println("\nfactorial");
		System.out.println(rec.factorial(10));
		System.out.println("\ncommon factor");
		System.out.println(rec.getCommenFactor(22, 8));

	}


	public int factorial(int num){
		if(num > 0){
			return num*factorial(num-1);
		} 
		return 1;
	}

	public int getCommenFactor(int x, int y){
		// 유클리드 호제법..?
		if(y==0){
			return x;
		}

		return getCommenFactor(y, x%y);
	}
}
