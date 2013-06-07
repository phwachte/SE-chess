package de.htwg.xiangqi.tui;

import java.util.Scanner;
import de.htwg.xiangqi.controller.BoardManager;
import de.htwg.xiangqi.entities.Piece;
import de.htwg.xiangqi.entities.Piece.Player;
import de.htwg.xiangqi.entities.Square;

public class TUIApplication {
	
	private static final int ROW = 10;
	private static final int COL = 9;
	private static final Scanner EINGABE = new Scanner(System.in);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BoardManager bm = new BoardManager();
		bm.setStartBoard();
		System.out.println(printBoard(bm.getBoard()));
		int choosenRow;
		int choosenCol;
		int targetRow;
		int targetCol;
		Piece choosen;
		boolean continueMoving = true;
		
		while (continueMoving) {
			System.out.println(playersTurn(bm));
			choosenRow = EINGABE.nextInt();
			choosenCol = EINGABE.nextInt();
			targetRow = EINGABE.nextInt();
			targetCol = EINGABE.nextInt();
			choosen = bm.choosenPiece(choosenRow, choosenCol);
			
			if (choosen == null) {
				System.out.println("No piece on choosen point, try again!");
				continue;
			}
			if (!bm.validChoose(choosen)) {
				System.out.println("Invalid choose of piece, choose your own piece!");
				continue;
			}
			if (!bm.validMove(choosen, targetRow, targetCol)) {
				System.out.println("Invalid move, try again!");
				continue;
			}
			if (bm.movePiece(choosenRow, choosenCol, targetRow, targetCol)) {
				bm.increaseMoveCounter();
				System.out.println(printBoard(bm.getBoard()));
			} else {
				System.out.println("Invalid move, try again!");
				continue;
			}
			
			Piece general = bm.isCheckmate();
			if (general != null) {
				continueMoving = false;
				System.out.println(winnerMessage(general));
			}
		}
		
	}

	public static String printBoard(Square[][] board) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		int j;
		sb.append("   0   1   2   3   4   5   6   7   8\n");
		while (i < ROW) {
			j = 0;
			sb.append(i).append(" ");
			while (j < COL) {
				sb.append("[");
				Piece piece = board[i][j].getPiece();
				if (piece != null) {
					if (piece.getPlayer() == Player.RED) {
						sb.append("R").append(piece.getPieceType());
					} else {
						sb.append("B").append(piece.getPieceType());
					}
				} else {
					sb.append("  ");
				}
				sb.append("]");
				++j;
			}
			sb.append("\n");
			++i;
		}
		return sb.toString();
	}
	
	public static String playersTurn(BoardManager bm) {
		StringBuilder sb = new StringBuilder();
		if (bm.getPlayersTurn() == 1) {
			sb.append(bm.getMoveCounter()).append(": Player Red, choose piece and point: ");
		} else {
			sb.append(bm.getMoveCounter()).append(": Player Black, choose piece and point: ");
		}
		return sb.toString();
	}
	
	public static String winnerMessage(Piece general) {
		StringBuilder sb = new StringBuilder();
		if (general.getPlayer() == Player.RED) {
			sb.append("Congratulation Player Black, you are the winner!");
		} else {
			sb.append("Congratulation Player Red, you are the winner!");
		}
		return sb.toString();
	}
	
}