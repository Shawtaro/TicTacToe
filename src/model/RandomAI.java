package model;

import java.awt.Point;

/**
 * This strategy selects the first available move at random. It is easy to beat
 * 
 * @throws IGotNowhereToGoException
 *             whenever asked for a move that is impossible to deliver
 * 
 * @author mercer
 */

// There is an intentional compile time error. Implement this interface
public class RandomAI implements TicTacToeStrategy {

	// Randomly find an open spot while ignoring possible wins and stops.
	// This should be easy to beat as a human.
	public Point desiredMove(TicTacToeGame theGame) throws IGotNowhereToGoException {
		if (theGame.maxMovesRemaining() == 0)
			throw new IGotNowhereToGoException("");

		int i = randomIndex(theGame);
		int j = randomIndex(theGame);

		// checks if random move is available, if not, try another random move
		if (theGame.available(i, j))
			return new Point(i, j);
		else
			return desiredMove(theGame);
	}

	// returns random index within the size of the board
	private int randomIndex(TicTacToeGame theGame) {
		return (int) (Math.random() * theGame.size());
	}
}