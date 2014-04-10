import java.util.LinkedList;

public class GameState {

	String[][] _board;
	String _player;
	String p1 = "Black";
	String p2 = "Red";
	LinkedList<Move> _actions;

	/*
	 * Gamestate constructor for any possible state
	 */
	public GameState(String[][] board, String curp) {
		/*
		 * assign values to variables
		 */
		_board = board;
		_player = curp;
	}

	/*
	 * Initial Gamestate constructor Generates and populates the board
	 */
	public GameState() {
		_player = p1;

		/*
		 * Initialize the board
		 */
		String symbol = "";
		_board = new String[8][8];
		for (int row = 0; row < 8; row++) {
			if (row >= 0 && row <= 2) {
				symbol = "b";
			} else if (row >= 5 && row <= 7) {
				symbol = "r";
			}

			if (row == 3 || row == 4) {
				// fill in spaces
				for (int col = 0; col < 8; col++) {
					_board[row][col] = "-";
				}
			} else {
				if (row % 2 == 0) {
					// row is even
					for (int col = 0; col < 8; col++) {
						if (col % 2 == 1) {
							_board[row][col] = symbol;
						} else {
							_board[row][col] = "-";
						}
					}
				} else {
					// row is odd
					for (int col = 0; col < 8; col++) {
						if (col % 2 == 0) {
							_board[row][col] = symbol;
						} else {
							_board[row][col] = "-";
						}
					}
				}
			}

		}
	}

	public LinkedList<Move> actions() {
		_actions = new LinkedList<Move>();

		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				// System.out.println(row + " " + col);
				if (player().equals(p1)) {
					if (_board[row][col].equals("b")) {
						checkdown(row, col);
					} else if (_board[row][col].equals("B")) {
						checkdown(row, col);
						checkup(row, col);
					}
				} else if (player().equals(p2)) {
					if (_board[row][col].equals("r")) {
						checkup(row, col);
					} else if (_board[row][col].equals("R")) {
						checkdown(row, col);
						checkup(row, col);
					}
				}
			}
		}

		return (_actions);
	}

	public void checkup(int row, int col) {
		int dx1 = row - 1;
		int dy1 = col - 1;
		String prev = row + " " + col;
		String symbol = _board[row][col];
		String next = "";
		/*
		 * Checks if possible moves are in range for Uleft diagonal
		 */
		if (checkEdges(dx1, dy1)) {
			if (_board[dx1][dy1].equals("-")) {
				next = dx1 + " " + dy1;
				Move move = new Move(prev, next, false);
				_actions.addLast(move);
			} else if (!_board[dx1][dy1].equalsIgnoreCase(symbol)) {
				int jx1 = dx1 - 1;
				int jy1 = dy1 - 1;
				boolean truth = checkJump(jx1, jy1); // checks if piece can jump
														// a piece and returns
														// true or not, if true
														// increment next
														// position
				if (truth == true) {
					next = jx1 + " " + jy1;
					Move move = new Move(prev, next, truth);
					_actions.addLast(move);
				} else {
					// No move since can't jump over the piece
				}
			}
		}

		/*
		 * Checks if possible moves are in range for Uright diagonal
		 */
		int dx2 = row - 1;
		int dy2 = col + 1;
		if (checkEdges(dx2, dy2)) {
			if (_board[dx2][dy2].equals("-")) {
				next = dx2 + " " + dy2;
				Move move = new Move(prev, next, false);
				_actions.addLast(move);
			} else if (!_board[dx2][dy2].equalsIgnoreCase(symbol)) {
				int jx2 = dx2 - 1;
				int jy2 = dy2 + 1;
				boolean truth = checkJump(jx2, jy2); // checks if piece can jump
														// a piece and returns
														// true or not, if true
														// increment next
														// position
				if (truth == true) {
					next = jx2 + " " + jy2;
					Move move = new Move(prev, next, truth);
					_actions.addLast(move);
				} else {
					// No move since can't jump over the piece
				}
			}
		}
	}

	public void checkdown(int row, int col) {
		int dx3 = row + 1;
		int dy3 = col - 1;
		String prev = row + " " + col;
		String next = "";
		String symbol = _board[row][col];

		/*
		 * Checks if possible moves are in range for Dleft diagonal
		 */
		if (checkEdges(dx3, dy3)) {
			if (_board[dx3][dy3].equals("-")) {
				next = dx3 + " " + dy3;
				Move move = new Move(prev, next, false);
				_actions.addLast(move);
			} else if (!_board[dx3][dy3].equalsIgnoreCase(symbol)) {
				int jx3 = dx3 + 1;
				int jy3 = dy3 - 1;
				boolean truth = checkJump(jx3, jy3); // checks if piece can jump
														// a piece and returns
														// true or not, if true
														// increment next
														// position
				if (truth == true) {
					next = jx3 + " " + jy3;
					Move move = new Move(prev, next, truth);
					_actions.addLast(move);
				} else {
					// No move since can't jump over the piece
				}
			}
		}

		/*
		 * Checks if possible moves are in range for Dright diagonal
		 */
		int dx4 = row + 1;
		int dy4 = col + 1;

		if (checkEdges(dx4, dy4)) {
			if (_board[dx4][dy4].equals("-")) {
				next = dx4 + " " + dy4;
				Move move = new Move(prev, next, false);
				_actions.addLast(move);
			} else if (!_board[dx4][dy4].equalsIgnoreCase(symbol)) {
				int jx4 = dx4 + 1;
				int jy4 = dy4 + 1;
				boolean truth = checkJump(jx4, jy4); // checks if piece can jump
														// a piece and returns
														// true or not, if true
														// increment next
														// position
				if (truth == true) {
					next = jx4 + " " + jy4;
					Move move = new Move(prev, next, truth);
					_actions.addLast(move);
				} else {
					// No move since can't jump over the piece
				}
			}
		}
	}

	/*
	 * Checks if the position being jumped to is an empty space or not if empty
	 * return true --> else reutrn false
	 */
	public boolean checkJump(int row, int col) {
		if (row >= 0 && row < 8) {
			if (col >= 0 && col < 8) {
				if (_board[row][col].equals("-")) {
					// System.out.println("Space jumping to is " +
					// _board[row][col]);
					return (true);
				}
			}
		}
		return (false);
	}

	/*
	 * Checks to make sure a given coordinate is a valid position on the board
	 */
	public boolean checkEdges(int row, int col) {
		if (row >= 0 && row < 8) {
			if (col >= 0 && col < 8) {
				return (true);
			}
		}
		return (false);
	}

	public String player() {
		return (_player);
	}

	public GameState result(String move) {
		/*
		 * make resulting state given a move
		 */
		int[] coords = convertNum(move);
		boolean jump = checkJump(move);
		String symbol = _board[coords[0]][coords[1]];
		int avgx = (coords[0] + coords[2]) / 2;
		int avgy = (coords[1] + coords[3]) / 2;

		if (coords[0] == coords[2] && coords[1] == coords[3]) {
			GameState gstate = new GameState(_board, player());
			System.out.println("Illegal move");
			return (gstate);
		}

		if(coords[2] == 0 || coords[2] == 7){
			if(symbol.equals("r")){
				symbol = "R";
			}
			else if(symbol.equals("b")){
				symbol = "B";
			}
		}
		
		if (jump == true) {
			_board[coords[0]][coords[1]] = "-";
			_board[avgx][avgy] = "-";
			_board[coords[2]][coords[3]] = symbol;
		} else {
			_board[coords[0]][coords[1]] = "-";
			_board[coords[2]][coords[3]] = symbol;
		}

		
		if (_player.equals(p1)) {
			GameState game = new GameState(_board, p2);
			return (game);
		} else {
			GameState game = new GameState(_board, p1);
			return (game);
		}
	}

	public int[] convertNum(String move) {
		// do substring to get previous and next coordinates
		int r1 = Integer.parseInt(move.substring(0, 1));
		int c1 = Integer.parseInt(move.substring(2, 3));
		int r2 = Integer.parseInt(move.substring(4, 5));
		int c2 = Integer.parseInt(move.substring(6, 7));

		int[] array = new int[4];
		array[0] = r1;
		array[1] = c1;
		array[2] = r2;
		array[3] = c2;

		return (array);
	}

	public boolean checkJump(String move) {
		// do substring on move to get information
		String b = move.substring(8);
		if (b.equals("t")) {
			return (true);
		} else {
			return (false);
		}
	}

	public void printState() {
		/*
		 * prints the current state
		 */
		System.out.println("Current Player: " + player());
		System.out.print(" ");
		for (int i = 0; i < 8; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		for (int row = 0; row < 8; row++) {
			System.out.print(row);
			for (int col = 0; col < 8; col++) {
				System.out.print(_board[row][col] + " ");
			}
			System.out.println();
		}

	}
}
