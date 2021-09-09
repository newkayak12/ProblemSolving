package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

public class JobRecommand {
	public static void main(String[] args) {
		System.out.println(solution(new String[] {"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"}, new String[] {"PYTHON", "C++", "SQL"}, new int[] {7,5,5}));
		System.out.println("+===============");
		System.out.println(solution(new String[] {"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"}, new String[] {"JAVA", "JAVASCRIPT"}, new int[] {7, 5}));
	}
	public static String solution(String[] table, String[] languages, int[] preference){
		ArrayList<Integer> scores = new ArrayList<>();
		ArrayList<String> title = new ArrayList<>();
		for(String piece : table){
			String[] tableRow = piece.substring(piece.indexOf(" ")+1).split(" ");
			 title.add(piece.substring(0, piece.indexOf(" ")));
			int number = 0;
			for(int j = 0; j<languages.length; j++){
				for(int i = 0; i<tableRow.length; i++){
					if(languages[j].equals(tableRow[i])){
						number+= (tableRow.length-i)*preference[j];
					}
				}
			}
			System.out.println(number);
			scores.add(number);

		}
		; 
		int first = scores.get(1);
		int second = scores.get(4);
		int third = scores.get(2);
		int fourth = scores.get(3);
		int fifth = scores.get(0);
		scores.clear();

		scores.add(first);
		scores.add(second);
		scores.add(third);
		scores.add(fourth);
		scores.add(fifth);
		Collections.sort(title);
		
		int idx = scores.indexOf(scores.stream().max(new Comparator<Integer>(){
	
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1-o2;
			}
			
		}).get());
		
		System.out.println(scores);
		return title.get(idx);
	}
}
