package chap05;

public class Hanoi {
	public static void main(String[] args) {
		Hanoi h = new Hanoi();
		h.move(3, 1, 3);
	}

	public void move(int no, int x, int y){
		// if(no>1){
		// 	move(no-1, x,  6-y-x);
		// 	System.out.println("원반" + no+"를 기둥"+x+"에서 기둥"+y+"으로 옮김");
		// }
		// if(no>1){
		// 	move(no-1, 6-x-y, y);
		// }

		if(no>1){
			move(no-1, x, 6-x-y);
		}
		System.out.println("원반"+no+"를 기둥"+x+"에서 기둥"+y+"으로 옮김");
		if(no>1){
			// System.out.println("원반"+no+"를 기둥"+x+"에서 기둥"+y+"으로 옮김");
			// move(no, x, y);
			move(no-1, 6-x-y, y);
		}
	}
}
