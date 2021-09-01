package chap01;

public class Star {
	public static void main(String[] args) {
		for(int i=0; i<5; i++){
			for(int j =0; j<5; j++){
				if(j==0){
					for(int z=0; z<5-i; z++){
						System.out.print(" ");
					}
				} 
			
				System.out.print(" *");
				if(i==j){
					System.out.println("");
					break;
				}
				
			}
		}

		System.out.println(" ");

		for(int i=0; i<5; i++){
			for(int j=5; j>0; j--){
				if(j==5){
					for(int z=0; z<5-(5-i); z++){
						System.out.print(" ");
					}
				}

				System.out.print(" *");
				
				if(i==j-1){
					System.out.println(" ");
					break;
				}
			}
		}
	}
}
