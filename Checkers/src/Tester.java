import java.util.Random;
import java.util.LinkedList;

public class Tester {

	
	static void printActions(LinkedList<Move> move){
		for(Move m: move){
			System.out.println(m.toString());	
		}			
	}
	
	public static void main(String [] args){
		
		GameState checkers = new GameState();
		checkers.printState();
		
		
		//System.out.println("Current Player: " + checkers.player());
		//System.out.println("\n\tThe Game");
		for(int i = 0; i < 200; i++){										//Move m: checkers.actions()
			int num = checkers.actions().size();
			LinkedList<Move> move = checkers.actions();
			Random gen = new Random();
			
			/*if(i == 0 || i == 8 || i == 15){
				//System.out.println("Current Player: " + checkers.player());
				checkers.printState();
				printActions(checkers.actions());
			}
			*/
			
			int index = gen.nextInt(num);
			Move cur = move.remove(index);
			
			System.out.println("\nTurn: " + (i+1));
			System.out.println("Piece: " + cur.getprevMove() + " Move to: " + cur.getCurMove() + " Detect Jump: " + cur.isJump() + "\n");	
			
			
			
			String b = " ";
			if(cur.isJump()){
				b = "t";	
			}
			else{
				b = "f";
			}
			
			
			String act = cur.getprevMove() + " " + cur.getCurMove() + " "  + b;
			
			GameState temp = checkers.result(act);
			checkers = temp;
			//System.out.println("Current Player: " + checkers.player());
			checkers.printState();
		}
		
	}
}
