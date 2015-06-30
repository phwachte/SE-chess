package de.htwg.xiangqi.model;

import com.google.inject.Injector;

import de.htwg.xiangqi.model.Piece.Player;

/**
 * class Board represents the board
 * 
 * @author P. Wachter
 * 
 */
public class Board {

	private String sessionName;
	private Square[][] board;
	private static final int MAX_ROW = 10;
	private static final int MAX_COL = 9;
	private static final int MIN = 0;
	private static final int ZERO = 0;
	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FOUR = 4;
	private static final int FIVE = 5;
	private static final int SIX = 6;
	private static final int SEVEN = 7;
	private static final int EIGHT = 8;
	private static final int NINE = 9;
	private Piece redGeneral;
	private Piece blackGeneral;
	private int moveCounter;

	/**
	 * create a new board with ten rows and nine columns.
	 */
	public Board() {
		sessionName = null;
		this.board = new Square[MAX_ROW][MAX_COL];
		this.moveCounter = 1;
	}

	/**
	 * @return the id
	 */
	public String getSessionName() {
		return sessionName;
	}

	/**
	 * @param id
	 */
	public void setSessionName(String id) {
		this.sessionName = id;
	}

	/**
	 * getter for the board of type Square[][]
	 * 
	 * @return the board
	 */
	public Square[][] getSquareMatrix() {
		return this.board;
	}

	/**
	 * @param board
	 */
	public void setSquareMatrix(Square[][] board) {
		this.board = new Square[Board.MAX_ROW][Board.MAX_COL];
		for(int i = 0;i < Board.MAX_ROW; i++){
			for(int o = 0; o < Board.MAX_COL; o++){	
				this.board[i][o] = board[i][o];
			}
		}
	}

	/**
	 * @param targetRow
	 * @param targetCol
	 * @return true, if the point is on the board, false, if not
	 */
	public boolean onBoard(int targetRow, int targetCol) {
		return (targetRow >= MIN && targetRow < MAX_ROW && targetCol >= MIN && targetCol < MAX_COL);
	}

	/**
	 * increase the count of valid moves
	 */
	public void increaseMoveCounter() {
		++this.moveCounter;
	}

	/**
	 * get the current move count
	 */
	public int getMoveCounter() {
		return this.moveCounter;
	}
	
	/**
	 * @param moveCounter
	 */
	public void setMoveCounter(int moveCounter) {
		this.moveCounter = moveCounter;
	}
	
	/**
	 * @return the red general piece
	 */
	public Piece getRedGeneral() {
		return this.redGeneral;
	}

	/**
	 * @return the black general piece
	 */
	public Piece getBlackGeneral() {
		return this.blackGeneral;
	}

	/**
	 * @param redGeneral
	 */
	public void setRedGeneral(Piece redGeneral) {
		this.redGeneral = redGeneral;
	}

	/**
	 * @param blackGeneral
	 */
	public void setBlackGeneral(Piece blackGeneral) {
		this.blackGeneral = blackGeneral;
	}

	/**
	 * initialise the board with all red pieces
	 */
	public void setPiecesRed() {
		board[NINE][FOUR] = new Square(new PieceGeneral(NINE, FOUR, Player.RED));
		this.redGeneral = board[NINE][FOUR].getPiece();
		board[NINE][THREE] = new Square(new PieceAdvisor(NINE, THREE,
				Player.RED));
		board[NINE][FIVE] = new Square(new PieceAdvisor(NINE, FIVE, Player.RED));
		board[NINE][TWO] = new Square(new PieceElephant(NINE, TWO, Player.RED));
		board[NINE][SIX] = new Square(new PieceElephant(NINE, SIX, Player.RED));
		board[NINE][ONE] = new Square(new PieceHorse(NINE, ONE, Player.RED));
		board[NINE][SEVEN] = new Square(new PieceHorse(NINE, SEVEN, Player.RED));
		board[NINE][ZERO] = new Square(new PieceChariot(NINE, ZERO, Player.RED));
		board[NINE][EIGHT] = new Square(new PieceChariot(NINE, EIGHT,
				Player.RED));
		board[SEVEN][ONE] = new Square(new PieceCannon(SEVEN, ONE, Player.RED));
		board[SEVEN][SEVEN] = new Square(new PieceCannon(SEVEN, SEVEN,
				Player.RED));
		board[SIX][ZERO] = new Square(new PieceSoldier(SIX, ZERO, Player.RED));
		board[SIX][TWO] = new Square(new PieceSoldier(SIX, TWO, Player.RED));
		board[SIX][FOUR] = new Square(new PieceSoldier(SIX, FOUR, Player.RED));
		board[SIX][SIX] = new Square(new PieceSoldier(SIX, SIX, Player.RED));
		board[SIX][EIGHT] = new Square(new PieceSoldier(SIX, EIGHT, Player.RED));
	}

	/**
	 * initialise the board with all black pieces
	 */
	public void setPiecesBlack() {
		board[ZERO][FOUR] = new Square(new PieceGeneral(ZERO, FOUR,
				Player.BLACK));
		this.blackGeneral = board[ZERO][FOUR].getPiece();
		board[ZERO][THREE] = new Square(new PieceAdvisor(ZERO, THREE,
				Player.BLACK));
		board[ZERO][FIVE] = new Square(new PieceAdvisor(ZERO, FIVE,
				Player.BLACK));
		board[ZERO][TWO] = new Square(
				new PieceElephant(ZERO, TWO, Player.BLACK));
		board[ZERO][SIX] = new Square(
				new PieceElephant(ZERO, SIX, Player.BLACK));
		board[ZERO][ONE] = new Square(new PieceHorse(ZERO, ONE, Player.BLACK));
		board[ZERO][SEVEN] = new Square(new PieceHorse(ZERO, SEVEN,
				Player.BLACK));
		board[ZERO][ZERO] = new Square(new PieceChariot(ZERO, ZERO,
				Player.BLACK));
		board[ZERO][EIGHT] = new Square(new PieceChariot(ZERO, EIGHT,
				Player.BLACK));
		board[TWO][ONE] = new Square(new PieceCannon(TWO, ONE, Player.BLACK));
		board[TWO][SEVEN] = new Square(
				new PieceCannon(TWO, SEVEN, Player.BLACK));
		board[THREE][ZERO] = new Square(new PieceSoldier(THREE, ZERO,
				Player.BLACK));
		board[THREE][TWO] = new Square(new PieceSoldier(THREE, TWO,
				Player.BLACK));
		board[THREE][FOUR] = new Square(new PieceSoldier(THREE, FOUR,
				Player.BLACK));
		board[THREE][SIX] = new Square(new PieceSoldier(THREE, SIX,
				Player.BLACK));
		board[THREE][EIGHT] = new Square(new PieceSoldier(THREE, EIGHT,
				Player.BLACK));
	}

	/**
	 * initialise all points of the board without pieces with empty squares
	 */
	public void fillBoard() {
		int i = 0, j;
		while (i < MAX_ROW) {
			j = 0;
			while (j < MAX_COL) {
				if (board[i][j] == null) {
					board[i][j] = new Square(null);
				}
				++j;
			}
			++i;
		}
	}

	@Override
	public Board clone() throws CloneNotSupportedException{
		Board b = new Board();
		b.moveCounter = moveCounter;
		b.setSessionName(this.sessionName);
		b.fillBoard();
		
		for (int i = 0; i < MAX_ROW; ++i) {
			for (int o = 0; o < MAX_COL; ++o) {
				if(board[i][o].getPiece() == null){
					board[i][o] = new Square(null);
				}else{
					Piece p = board[i][o].getPiece();
					b.board[i][o] = new Square(p.clone());
				}
			}
		}	
		b.redGeneral = findGeneral(Player.RED);
		b.blackGeneral = findGeneral(Player.BLACK);
		return b;
	}
	
	private PieceGeneral findGeneral(Player color){
		for(int i =0; i < Board.getMaxRow(); i++){
			for(Square sq : board[i]){
				if(sq.getPiece() instanceof PieceGeneral){
					if(sq.getPiece().getPlayer() == color){
						return (PieceGeneral) sq.getPiece();
					}
				}
			}
		}
		return null;
	}

	public static int getMaxRow() {
		return MAX_ROW;
	}

	public static int getMaxCol() {
		return MAX_COL;
	}
	
	@Override
	public String toString(){
		return sessionName;
	}
}
