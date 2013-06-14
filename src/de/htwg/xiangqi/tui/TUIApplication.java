package de.htwg.xiangqi.tui;

import java.util.Scanner;

import de.htwg.xiangqi.controller.BoardManager;

public final class TUIApplication {

	private static final int ROW = 10;
	private static final int COL = 9;
	private static final Scanner EINGABE = new Scanner(System.in);

	private TUIApplication() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BoardManager bm = new BoardManager();
		bm.setStartBoard();
		System.out.println(printBoard(bm));
		int choosenRow;
		int choosenCol;
		int targetRow;
		int targetCol;
		boolean continueMoving = true;

		while (continueMoving) {
			System.out.println(playersTurn(bm));
			choosenRow = EINGABE.nextInt();
			choosenCol = EINGABE.nextInt();
			targetRow = EINGABE.nextInt();
			targetCol = EINGABE.nextInt();

			if (bm.choosenPiece(choosenRow, choosenCol) == null) {
				printMessage("No piece on choosen point, try again!");
				continue;
			}
			if (!bm.validChoose(bm.choosenPiece(choosenRow, choosenCol))) {
				printMessage("Invalid choose of piece, choose your own piece!");
				continue;
			}
			if (!bm.validMove(bm.choosenPiece(choosenRow, choosenCol),
					targetRow, targetCol)) {
				printMessage("Invalid move, try again!");
				continue;
			}
			if (bm.movePiece(choosenRow, choosenCol, targetRow, targetCol)) {
				bm.increaseMoveCounter();
				printMessage(printBoard(bm));
			} else {
				printMessage("Invalid move, try again!");
				continue;
			}

			if (bm.isCheckmate() != 'n') {
				continueMoving = false;
				printMessage(winnerMessage(bm.isCheckmate()));
			}
		}

	}

	public static String printBoard(BoardManager bm) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		int j;
		sb.append("   0   1   2   3   4   5   6   7   8\n");
		while (i < ROW) {
			j = 0;
			sb.append(i).append(" ");
			while (j < COL) {
				sb.append("[");
				sb.append(bm.getOutput(i, j));
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
			sb.append(bm.getMoveCounter()).append(
					": Player Red, choose piece and point: ");
		} else {
			sb.append(bm.getMoveCounter()).append(
					": Player Black, choose piece and point: ");
		}
		return sb.toString();
	}

	public static String winnerMessage(char general) {
		StringBuilder sb = new StringBuilder();
		if (general == 'r') {
			sb.append("Congratulation Player Black, you are the winner!");
		} else {
			sb.append("Congratulation Player Red, you are the winner!");
		}
		return sb.toString();
	}
	
	private static void printMessage(String str) {
		System.out.println(str);
	}

}