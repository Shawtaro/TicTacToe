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
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (theGame.getTicTacToeBoard()[(i + 2) % 3][j] == '_' && theGame.getTicTacToeBoard()[i][j] == 'O'
						&& theGame.getTicTacToeBoard()[(i + 1) % 3][j] == 'O')
					return new Point((i + 2) % 3, j);
				else if (theGame.getTicTacToeBoard()[i][(j + 2) % 3] == '_' && theGame.getTicTacToeBoard()[i][j] == 'O'
						&& theGame.getTicTacToeBoard()[i][(j + 1) % 3] == 'O')
					return new Point(i, (j + 2) % 3);
		// check diagonals
		for (int i = 0; i < 3; i++)
			if (theGame.getTicTacToeBoard()[(i + 3) % 3][(i + 3) % 3] == '_' && theGame.getTicTacToeBoard()[i][i] == 'O'
					&& theGame.getTicTacToeBoard()[(i + 1) % 3][(i + 1) % 3] == 'O')
				return new Point((i + 3) % 3, (i + 3) % 3);
			else if (theGame.getTicTacToeBoard()[(i + 3) % 3][(5 - i) % 3] == '_'
					&& theGame.getTicTacToeBoard()[i][2 - i] == 'O'
					&& theGame.getTicTacToeBoard()[(i + 1) % 3][(3 - i) % 3] == 'O')
				return new Point((i + 3) % 3, (5 - i) % 3);
		// First look to block an opponent win
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (theGame.getTicTacToeBoard()[(i + 2) % 3][j] == '_' && theGame.getTicTacToeBoard()[i][j] == 'X'
						&& theGame.getTicTacToeBoard()[(i + 1) % 3][j] == 'X')
					return new Point((i + 2) % 3, j);
				else if (theGame.getTicTacToeBoard()[i][(j + 2) % 3] == '_' && theGame.getTicTacToeBoard()[i][j] == 'X'
						&& theGame.getTicTacToeBoard()[i][(j + 1) % 3] == 'X')
					return new Point(i, (j + 2) % 3);
		// check diagonals
		for (int i = 0; i < 3; i++)
			if (theGame.getTicTacToeBoard()[(i + 3) % 3][(i + 3) % 3] == '_' && theGame.getTicTacToeBoard()[i][i] == 'X'
					&& theGame.getTicTacToeBoard()[(i + 1) % 3][(i + 1) % 3] == 'X')
				return new Point((i + 3) % 3, (i + 3) % 3);
			else if (theGame.getTicTacToeBoard()[(i + 3) % 3][(5 - i) % 3] == '_'
					&& theGame.getTicTacToeBoard()[i][2 - i] == 'X'
					&& theGame.getTicTacToeBoard()[(i + 1) % 3][(3 - i) % 3] == 'X')
				return new Point((i + 3) % 3, (5 - i) % 3);
		// If the AI can not block, look for a win

		// If no block or win is possible, pick a move from those still
		// available
		// random move
		return helperMethod(theGame);

	}

	private Point helperMethod(TicTacToeGame theGame) {
		int i = randomIndex();
		int j = randomIndex();
		if (theGame.getTicTacToeBoard()[i][j] == '_')
			return new Point(i, j);
		else
			return desiredMove(theGame);
	}

	private int randomIndex() {
		return (int) (Math.random() * 3);
	}
}
