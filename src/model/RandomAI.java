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

	@Override
	public Point desiredMove(TicTacToeGame theGame) {
		boolean isFilled = true;
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				if(theGame.getTicTacToeBoard()[i][j]=='_')
					isFilled=false;
		if(isFilled)
			throw new IGotNowhereToGoException("");
		int i = randomIndex();
		int j = randomIndex();
		if (theGame.getTicTacToeBoard()[i][j] == '_')
			return new Point(i, j);
		else
			return desiredMove(theGame);
	}
	private int randomIndex(){
		return (int) (Math.random() * 3);
	}
}