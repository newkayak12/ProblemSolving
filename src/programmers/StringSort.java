package programmers;

import java.util.Arrays;
import java.util.Comparator;

public abstract class StringSort {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution2(new String[] {"abce","abcd","cdx"}, 2)));

	}
	
	public static String[] solution(String[] strings, int n) {
		Arrays.sort(strings, new Comparator<String>(){

			@Override
			public int compare(String o1, String o2) {
			
				
				int result = 0;
				do{
					result = o1.charAt(n)-o2.charAt(n);
					System.out.println(o1.charAt(n)+" : "+o2.charAt(n)+" : "+result);
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(result==0){
						int point = o1.length();

						if(o1.length()>o2.length()){
							point = o2.length();
						}
						for(int i =0; i<o1.length(); i++ ){
							result = o1.charAt(i)-o2.charAt(i);
						}
					};
				}while(result==0);
				
				
				return result;
			}
			
		});

		return strings;
	    }

	    public static String[] solution2(String[] arr , int c){
		Arrays.sort(arr,new Comparator<String>(){

			@Override
			public int compare(String o1, String o2) {
				int comp = o1.charAt(c)- o2.charAt(c);
				if(comp ==0){
					int fir = o1.length();
					int sec = o2.length();
					int smaller = fir>=sec? sec:fir;

					for(int z = 0; z<smaller; z++){
						comp = o1.charAt(z)- o2.charAt(z);

						if(comp!=0){
							break;
						}
					}

				}
				return comp;
			}
			
		});

		return arr;
	    }
}
