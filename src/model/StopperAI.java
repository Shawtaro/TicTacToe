package model;

import java.awt.Point;
import java.util.Random;

/**
 * This TTT strategy tries to prevent the opponent from winning by checking for
 * a space where the opponent is about to win. If none found, it randomly picks
 * a place to win, which an sometimes be a win even if not really trying.
 * 
 * @author mercer
 */
public class StopperAI implements TicTacToeStrategy {

	@Override
	public Point desiredMove(TicTacToeGame theGame) {
		// check rows and cols for win
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (theGame.getTicTacToeBoard()[(i + 2) % 3][j] == '_' && theGame.getTicTacToeBoard()[i][j] == 'O'
						&& theGame.getTicTacToeBoard()[(i + 1) % 3][j] == 'O')
					return new Point((i + 2) % 3, j);
				else if (theGame.getTicTacToeBoard()[i][(j + 2) % 3] == '_' && theGame.getTicTacToeBoard()[i][j] == 'O'
						&& theGame.getTicTacToeBoard()[i][(j + 1) % 3] == 'O')
					return new Point(i, (j + 2) % 3);
		// check diagonals for win
		if (theGame.available(0, 0) && theGame.getTicTacToeBoard()[1][1] == 'O'
				&& theGame.getTicTacToeBoard()[2][2] == 'O')
			return new Point(0, 0);
		if (theGame.available(1, 1) && theGame.getTicTacToeBoard()[0][0] == 'O'
				&& theGame.getTicTacToeBoard()[2][2] == 'O')
			return new Point(1, 1);
		if (theGame.available(2, 2) && theGame.getTicTacToeBoard()[1][1] == 'O'
				&& theGame.getTicTacToeBoard()[0][0] == 'O')
			return new Point(2, 2);
		if (theGame.available(2, 0) && theGame.getTicTacToeBoard()[1][1] == 'O'
				&& theGame.getTicTacToeBoard()[0][2] == 'O')
			return new Point(2, 0);
		if (theGame.available(1, 1) && theGame.getTicTacToeBoard()[2][0] == 'O'
				&& theGame.getTicTacToeBoard()[0][2] == 'O')
			return new Point(1, 1);
		if (theGame.available(0, 2) && theGame.getTicTacToeBoard()[1][1] == 'O'
				&& theGame.getTicTacToeBoard()[2][0] == 'O')
			return new Point(0, 2);
		// look to block an opponent win
		// rows and cols
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (theGame.getTicTacToeBoard()[(i + 2) % 3][j] == '_' && theGame.getTicTacToeBoard()[i][j] == 'X'
						&& theGame.getTicTacToeBoard()[(i + 1) % 3][j] == 'X')
					return new Point((i + 2) % 3, j);
				else if (theGame.getTicTacToeBoard()[i][(j + 2) % 3] == '_' && theGame.getTicTacToeBoard()[i][j] == 'X'
						&& theGame.getTicTacToeBoard()[i][(j + 1) % 3] == 'X')
					return new Point(i, (j + 2) % 3);
		// check diagonals
		/*
		 * my failed attempt at looping the cases below :( for (int i = 0; i <
		 * 3; i++) if (theGame.getTicTacToeBoard()[(i + 3) % 3][(i + 3) % 3] ==
		 * '_' && theGame.getTicTacToeBoard()[i][i] == 'X' &&
		 * theGame.getTicTacToeBoard()[(i + 1) % 3][(i + 1) % 3] == 'X') return
		 * new Point((i + 3) % 3, (i + 3) % 3); else if
		 * (theGame.getTicTacToeBoard()[(i + 3) % 3][(5 - i) % 3] == '_' &&
		 * theGame.getTicTacToeBoard()[i][2 - i] == 'X' &&
		 * theGame.getTicTacToeBoard()[(i + 1) % 3][(1 - i) % 3] == 'X') return
		 * new Point((i + 3) % 3, (5 - i) % 3);
		 */
		if (theGame.available(0, 0) && theGame.getTicTacToeBoard()[1][1] == 'X'
				&& theGame.getTicTacToeBoard()[2][2] == 'X')
			return new Point(0, 0);
		if (theGame.available(1, 1) && theGame.getTicTacToeBoard()[0][0] == 'X'
				&& theGame.getTicTacToeBoard()[2][2] == 'X')
			return new Point(1, 1);
		if (theGame.available(2, 2) && theGame.getTicTacToeBoard()[1][1] == 'X'
				&& theGame.getTicTacToeBoard()[0][0] == 'X')
			return new Point(2, 2);
		if (theGame.available(2, 0) && theGame.getTicTacToeBoard()[1][1] == 'X'
				&& theGame.getTicTacToeBoard()[0][2] == 'X')
			return new Point(2, 0);
		if (theGame.available(1, 1) && theGame.getTicTacToeBoard()[2][0] == 'X'
				&& theGame.getTicTacToeBoard()[0][2] == 'X')
			return new Point(1, 1);
		if (theGame.available(0, 2) && theGame.getTicTacToeBoard()[1][1] == 'X'
				&& theGame.getTicTacToeBoard()[2][0] == 'X')
			return new Point(0, 2);
		// If the AI can not block, look for a win

		// If no block or win is possible, pick a move from those still
		// available
		// random move
		return helperMethod(theGame);
	}

	// returns random move
	private Point helperMethod(TicTacToeGame theGame) {
		int i = randomIndex(theGame);
		int j = randomIndex(theGame);
		if (theGame.getTicTacToeBoard()[i][j] == '_')
			return new Point(i, j);
		else
			return desiredMove(theGame);
	}

	// returns random index within the size of the board
	private int randomIndex(TicTacToeGame theGame) {
		return (int) (Math.random() * theGame.size());
	}
}
