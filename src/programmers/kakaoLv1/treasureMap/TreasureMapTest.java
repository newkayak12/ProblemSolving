package programmers.kakaoLv1.treasureMap;


public class TreasureMapTest {
	TreasureMap tm = new TreasureMap();

	public void treasureMapTest(){
		String[] expect = {"#####","# # #","### #","#  ##","######"};
		String[] actual = tm.solution(5, new int[] {9, 20, 28, 18, 11}, new int[] {30, 1, 21, 17, 28});

	}
}
