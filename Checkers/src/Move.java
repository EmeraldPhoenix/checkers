
public class Move {

	private String _prev;
	private String _next;
	private boolean _jump;
	
	public Move(String prev, String next, boolean jump){
		_prev = prev;
		_next = next;
		_jump = jump;
	}
	
	public String getprevMove(){
		return(_prev);
	}
	
	public String getCurMove(){
		return(_next);
	}
	
	public boolean isJump(){
		return(_jump);
	}
	
	public String toString(){
		String str = "";
		str = "Piece: " + this.getprevMove() + " Move to: " + this.getCurMove() + " Detect Jump: " + this.isJump();
		return(str);
	}
}
