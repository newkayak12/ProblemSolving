package programmers.lv1.kakaoLv1.craneGame;

import java.util.ArrayList;
import java.util.Stack;

public class CraneGame {

    public static int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> result = new Stack<>();
        ArrayList<Stack<Integer>> list = new ArrayList<>(5);
        for(int i = 0; i<board.length; i++){
            list.add(new Stack<Integer>());
        }
        
        for(int i = board.length-1; i>=0; i--){
            for(int j = 0; j<board[i].length; j++){
                list.get(j).push(board[i][j]);
            }
        }
       

        for(int piece : moves){
            
            if(result.isEmpty()){
                while(list.get(piece-1).peek()!=0){
                    list.get(piece-1).pop();
                }
                result.push(list.get(piece-1).pop());
            } else {
                if(result.peek() == list.get(piece-1).peek()){
                    while(list.get(piece-1).peek()!=0){
                        list.get(piece-1).pop();
                    }
                    result.pop();
                    list.get(piece-1).pop();
                    answer++;
                } else {
                    while(list.get(piece-1).peek()!=0){
                        list.get(piece-1).pop();
                    }
                    result.push(list.get(piece-1).pop());
                }
                
            } 

            System.out.println(result);
        }
        
        return answer;
    }
}
