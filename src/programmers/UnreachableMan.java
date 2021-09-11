package programmers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UnreachableMan {
	public static void main(String[] args) {
		
		System.out.println(solution(new String[] {"mislav", "stanko", "mislav", "ana"}, new String[] { "stanko",  "ana" ,"mislav"}));
		System.out.println("\n");
		System.out.println(solution(new String[] {"marina", "josipa", "nikola", "vinko", "filipa"}, new String[] { "josipa", "filipa", "marina", "nikola"}));
	}
	
	public static String solution(String[] participant, String[] completion) {
		String answer="";
		HashMap<String,Integer> map = new HashMap<>();
		for(String parti: participant){
			map.put(parti, map.get(parti)==null? 1: map.get(parti)+1);
		}
		for(String comp : completion){
			map.put(comp, map.get(comp)-1);
		}
		
		Set<Map.Entry<String,Integer>> entries = map.entrySet();
		for(Map.Entry<String,Integer> piece : entries){
			String key = piece.getKey();
			if(map.get(key)>0){
				answer = key;
			}
		}
		
		return answer;
	    }
}
