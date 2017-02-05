package view;

/*
 * AltView.java
 * @author Shawtaroh Granzier-Nakajima
 * 2/3/17
 * CS335, Project 3
 * Implements alternate view for tictactoe game
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.OurObserver;
import model.ComputerPlayer;
import model.TicTacToeGame;

@SuppressWarnings("serial")
public class TextFieldView extends JPanel implements OurObserver {

	private TicTacToeGame theGame;
	private JTextArea game = new JTextArea();
	private ComputerPlayer computerPlayer;
	private int height, width;
	private JButton move = new JButton("make the move");
	JTextField row = new JTextField();
	JTextField col = new JTextField();

	public TextFieldView(TicTacToeGame TicTacToeGame, int width, int height) {
		theGame = TicTacToeGame;
		this.height = height;
		this.width = width;
		computerPlayer = theGame.getComputerPlayer();
		initializeButtonPanel();
	}

	// updates button text in b/w view changes to update in case of win/tie
	public void updateText() {
		if (theGame.didWin('X'))
			this.move.setText("X Wins");
		if (theGame.didWin('O'))
			this.move.setText("O Wins");
		if (theGame.tied())
			this.move.setText("Tied");
	}

	private void initializeButtonPanel() {

		this.setLayout(null);

		JPanel altPanel = new JPanel();
		altPanel.setLocation(0, 0);
		altPanel.setSize(width, height);
		altPanel.setBackground(Color.CYAN);
		altPanel.setLayout(new BorderLayout());

		Font myFont = new Font("Courier", Font.BOLD, 42);

		row.setSize(50, 25);
		col.setSize(50, 25);
		row.setLocation(15, 15);
		col.setLocation(15, 45);

		JLabel rowLabel = new JLabel("row");
		JLabel colLabel = new JLabel("column");
		rowLabel.setSize(50, 25);
		colLabel.setSize(50, 25);
		rowLabel.setLocation(75, 15);
		colLabel.setLocation(75, 45);
		this.add(rowLabel);
		this.add(colLabel);
		this.add(row);
		this.add(col);

		move.setSize(125, 25);
		move.setLocation(125, 30);
		move.addActionListener(new ButtonListener());
		this.add(move);

		game.setLayout(null);
		game.setSize(width - 125, height - 170);
		game.setFont(myFont);
		game.setText(theGame.toString());
		game.setLocation(50, 75);
		this.add(game, BorderLayout.SOUTH);

		this.add(altPanel);

	}

	// Handles updating the game when button clicked
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// first catch bad inputs, then check if available and make move,
			// else "move not available"
			if (theGame.stillRunning() == false)
				return;
			int c = 0, r = 0;
			try {
				r = Integer.parseInt(row.getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Invalid input");
				return;
			}
			try {
				c = Integer.parseInt(col.getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Invalid input");
				return;
			}
			if (r > 2 || r < 0 || c > 2 || c < 0) {
				JOptionPane.showMessageDialog(null, "Invalid move");
				return;
			} else if (theGame.available(r, c)) {
				// System.out.printf("AVAILABLE, row= %d col= %d \n",r,c);
				theGame.choose(r, c);
				if (theGame.tied()) {
					move.setText("Tied");
					return;
				}
				if (theGame.didWin('X')) {
					move.setText("X wins");
					return;
				}

			} else {
				JOptionPane.showMessageDialog(null, "Move not available");
				return;
			}
			// Now computer plays their turn
			Point play = computerPlayer.desiredMove(theGame);
			theGame.choose(play.x, play.y);
			if (theGame.didWin('O'))
				move.setText("O wins");
		}
	}

	// updates the board
	public void update() {
		// TODO Auto-generated method stub
		game.setText(theGame.toString());
		if (theGame.stillRunning())
			move.setText("Make the move");
	}

}