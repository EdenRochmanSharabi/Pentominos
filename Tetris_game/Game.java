import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.awt.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Game

{

	private static JLabel rules ;
	private static JPanel panelN ;
	private static JLabel labelPentris;
	public static int score = 0; // score in the beginning of every game
	public static int highestScore; // highest score ever gain
    public static final int horizontalGridSize = 5; // horizontal size of the grid
    public static final int verticalGridSize = 15; // vertical size of the grid
	public static int[][] activefield; // active field which is displayed in GUI
    public static UI ui;
	public static ActivePentomino activepentomino; // the pentomino used 
	public static boolean play = false; // state of play/stop button
	public static boolean pause = false; // state of pause/resume button
	public static int count = 0;
	public static boolean checktopleft = true; // true when there is a space for pentonimo to appear in the top left corner (0;0)
	public static boolean pieceplaced = false; // the condition of the current piece: false = piece is not placed yet, true = piece is placed already
	public static int keyinput = 0;
	public static boolean interrupt = false;
	public static int playMode;
	public static int gamespeed;
	static JRadioButton human;
	static JRadioButton lowest;
	static JRadioButton sides;
	static JTextField speed;
	static JButton start;
	/**
	 * The main function which calls the agents and updates the UI.
	 * It also takes care of all the stopping conditions and partly the button handeling 
	 * @field the main field created empty in main
	 */
    public static void gameLoop(int[][] field) 
    {
		pieceplaced = false;
		checktopleft = true;
		while(checktopleft){
			activepentomino = new ActivePentomino();
			if (!checkIfFits(field, activepentomino.getPiece(), 0, 0)) {
				checktopleft = false;
				System.out.println("Lost");
			}
			else{
				pieceplaced = false;
				addPiece(activefield, activepentomino.getPiece(), activepentomino.getPieceId(), activepentomino.GetXPosition(), activepentomino.GetYPosition());
				ui.setState(activefield, score, Game.highestScore);
				char[] inputs = {' '};
				switch (playMode) {
					case 1:
					inputs = Agents.LowestRowBot(field, activepentomino.getPiece(), activepentomino).toCharArray();
						break;
					case 2:
					inputs = Agents.sidesBot(field, activepentomino.getPiece(), activepentomino).toCharArray();
						break;
					default:
						break;
				}
				int countinput = 0;
				try {
					for (int i = 0; i < gamespeed; i++) {
					TimeUnit.MILLISECONDS.sleep(1);
					if(interrupt){
						i = gamespeed+1;
						interrupt = false;
					}
				}} catch (InterruptedException e) {e.printStackTrace();}
				while (!pieceplaced) {
					switch (playMode) {
						case 0:
							pieceplaced = shiftRotatePiece(field, keysToInt(keyinput));
							break;
						default:
							char input = inputs[countinput];
							pieceplaced = shiftRotatePiece(field, inputToInt(input));
							break;
					}
					keyinput = 40;
					ui.setState(activefield, score, Game.highestScore);
					countinput++;
					UI.displayaArea.requestFocus();
					try {
						for (int i = 0; i < gamespeed; i++) {
							TimeUnit.MILLISECONDS.sleep(1);
							if(interrupt){
								i = gamespeed+1;
								interrupt = false;
							}
						}
						
					} catch (InterruptedException e) {e.printStackTrace();}
					while (pause){
						count++;
						count--;
					}
				}
				checkIfRowFull(field);
				activefield = cloneTwoDimensional(field);
				ui.setState(activefield, score, highestScore);
			}
			try {
				saves(highestScore);
			} catch (IOException e) {
				e.printStackTrace();
			}
			remember();
		}
    }
	/**
	 * Before having a connection between arrows and piece movements, we had input from keyboard.
	 * I.e. 'a' for shift left, 'd' for shift right, 's' for shift down on 1 square, ' ' for shift all way down,
	 * 'f' for clockwise rotation and 'g' for anticlockwise rotation
	 * This method aligns a number to every possible input
	 * @param input one of the letters listed above or a space 
	 * @return the corresponding number
	 */
	public static int inputToInt(char input){
		switch (input) {
			case 'a':
				return 0;
			case 's':
				return 2;
			case 'd':
				return 1;
			case ' ':
				return 3;	
			case 'f':
				return 4;	
			case 'g':
				return 5;
		}
		return -1;
	}
	/**
	 * Aligns the arrows and a space bar input to a number which represents the action 
	 * which has to be made with pentomino
	 * @param i number of a key in ASCII code
	 * @return the corresponding number
	 */
	public static int keysToInt(int i){
		switch (i) {
			case 37: //left key <--
				return 0;
			case 39: // right key -->
				return 1;
			case 40: // key down
				return 2;
			case 32: // enter, all way down
				return 3;	
			case 38: // rotate clockwise
				return 4;	
			case 90: // rotate anticlockwise
				return 5;
		}
		return -1;
	}
	/**
	 * Executes the action corresponding to the direction
	 * 0 - shift left, 1 - shift right, 2 - shift down on 1 square, 3 - shift all way down,
	 * 4 - clockwise rotation, 5 - anticlockwise rotation
	 * @param field the main field
	 * @param direction the number which was aligned to arrow and space bar key input in keysToInt function
	 * @return if the piece is not completely placed yet after the performed action, returns false; if it is completely placed, returns true 
	 */
	public static boolean shiftRotatePiece(int[][] field, int direction){
		int preY = activepentomino.GetYPosition();
		int preX = activepentomino.GetXPosition();
		switch (direction) {
			case 0:
				activepentomino.shiftLeft();
				return checkPiecePosition(field, preX, preY, direction);
			case 1:
				activepentomino.shiftRight();
				return checkPiecePosition(field, preX, preY, direction);
			case 2:
				activepentomino.shiftDown();
				return checkPiecePosition(field, preX, preY, direction);
			case 3:
				while (!checkPiecePosition(field, preX, preY, direction)) {
					preY = activepentomino.GetYPosition();
					preX = activepentomino.GetXPosition();
					activepentomino.shiftDown();
				}
				return true;
			case 4:
				activepentomino.rotateClockwise();
				return checkPiecePosition(field, preX, preY, direction);
			case 5:
				activepentomino.rotateAntiClockwise();
				return checkPiecePosition(field, preX, preY, direction);
			default:
				return false;
		}
	}
	/**
	 * Checks if the given pentomino fits the new coordinates. If it fits, pieced was moved successfully, 
	 * but we assume it is not completely placed yet (see return case 1); 
	 * piece was not moved to the new position because of the grid boarders => it is not placed yet, we leave it where it was (see return case 2);
	 * if none of the above is true, it means that it was not possible to move the piece but the reason is not in sides, it means that it fell down completely =>
	 * => piece is placed now (see return case 3)
	 * @param field the main field
	 * @param preX initial x position of the pentomino
	 * @param preY initial y position of the pentomino
	 * @return case 1: return false, case 2: return false, case 3: return true
	 */
	public static boolean checkPiecePosition(int[][] field, int preX, int preY, int direction){
		int x = activepentomino.GetXPosition();
		int [][] piece = activepentomino.getPiece();

			if (checkIfFits(field, activepentomino.getPiece(), activepentomino.GetXPosition(), activepentomino.GetYPosition())) {
				activefield = cloneTwoDimensional(field);
				addPiece(activefield, activepentomino.getPiece(), activepentomino.getPieceId(), activepentomino.GetXPosition(), activepentomino.GetYPosition());
				return false;
			}
			else if((direction >= 0 && direction <= 3) && (x + piece.length > field.length || x < 0)) {
				activefield = cloneTwoDimensional(field);
				activepentomino.takeXPositon(preX);
				activepentomino.takeYPositon(preY);
				addPiece(activefield, activepentomino.getPiece(), activepentomino.getPieceId(), preX, preY);
				return false;
			}		
			else{
				if(direction == 4) activepentomino.rotateAntiClockwise();
				if(direction == 5) activepentomino.rotateClockwise();
				addPiece(field, activepentomino.getPiece(), activepentomino.getPieceId(), preX, preY);
				activepentomino.takeXPositon(preX);
				activepentomino.takeYPositon(preY);
				return true;
			}
	}
	/** 
	 * Find filled rows if there are any and removed them by shifting the whole grid by one row down
	 * @param field the field
	 * @return
	*/
	public static void checkIfRowFull(int[][] field){
		for(int j = 0; j < verticalGridSize; j++){
			int filledCounter = 0;
			for(int i = 0; i < horizontalGridSize; i++){
				if(field[i][j] > -1) filledCounter++;
			}
			// if the whole row is filled we need to remove it
			if(filledCounter == horizontalGridSize){
				score++;
				for(int k = j; k > 0; k--){
					for(int i = horizontalGridSize - 1; i >= 0; i--){
						field[i][k] = field[i][k-1];
					}
				}
				for(int i = 0; i < horizontalGridSize; i++){
					field[i][0] = -1;
				}
			}
		}
	}
	/**
	 * Checks if the piece fits the given x and y coordinates of the grid
	 * @param field the field
	 * @param piece the piece
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return if fits - true, if does not fit - false
	 */
	public static boolean checkIfFits(int[][] field, int[][] piece, int x, int y){
		for(int i = 0; i < piece.length; i++) // loop over y position of pentomino
		{
			for (int j = 0; j < piece[i].length; j++) // loop over x position of pentomino
			{   
				if(piece[i][j] == 1 && x < 0) {
					return false;
				}
				else if (piece[i][j] == 1 && (x + i >= field.length || y + j >= field[0].length || field[x + i][y + j] != -1)){
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * Clones the given two dimensional array
	 * @param array array to clone
	 * @return the cloned array
	 */
	public static int[][] cloneTwoDimensional(int[][] array){
		int[][] clone = new int[array.length][];
		for(int i = 0; i < array.length; i++){
			clone[i]=array[i].clone();
		}
		return clone;
	}  
	/**
	 * Adds a pentomino to the position on the field (overriding current board at that position)
	 * @param field a matrix representing the board to be fulfilled with pentominoes
	 * @param piece a matrix representing the pentomino to be placed in the board
	 * @param pieceID ID of the relevant pentomino
	 * @param x x position of the pentomino
	 * @param y y position of the pentomino
	 */
	public static void addPiece(int[][] field, int[][] piece, int pieceID, int x, int y){
        for(int i = 0; i < piece.length; i++) // loop over x position of pentomino
        {
            for (int j = 0; j < piece[i].length; j++) // loop over y position of pentomino
            {
                if (piece[i][j] == 1)
                {
                    // Add the ID of the pentomino to the board if the pentomino occupies this square
                    field[x + i][y + j] = pieceID;
                }
            }
        }
    }
	/**
	 * Reads the highscore from the File
	 */
	public static void remember(){   
		File file = new File("HighestScore.csv");
		try{  
			Scanner scanner = new Scanner(file);
			highestScore = scanner.nextInt();
			if (score > highestScore) {
				highestScore = score;	
			}
			scanner.close();
		}
		catch(FileNotFoundException e){
            System.exit(0);
        }
	}
	/**
	 * Save the highest score to the HighestScore.csv file
	 * @param highestScore the higest score
	 * @throws IOException
	 */
	public static void saves(int highestScore) throws IOException {
			PrintWriter writer = new PrintWriter("HighestScore.csv", "UTF-8");
			writer.write("" + highestScore);
			writer.println();
			writer.close();
	} 
	/**
	 * Main function. Needs to be executed to start the basic search algorithm
	 * Buttons to play/stop, pause/resume, reset are created in of this method 
	 * Also creates an empty grid in the beginning of the game 
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) {
		
		frame.setSize(500,250);
		frame.setTitle("Pentris Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelN = new JPanel();
	
		rules = new JLabel("Pentris Game");
		rules.setBounds(500, 500, 25, 25);
		panelN.add(rules);

		JLabel comment = new JLabel("Choose the game mode: ");
		JLabel rules = new JLabel("<html>Choose the game mode <br/> <br/>Rules: <br/> <br/>- If human mode was selected: complete row to make them disappear. <br/>- If agent mode was selected: take a beer and enjoy the amazing AI.<br/> <br/></html>");
		ButtonGroup playingMode = new ButtonGroup();
		human = new JRadioButton("Human");
		
		lowest = new JRadioButton("Lowest row agent");
		sides = new JRadioButton("Sides agent");
		playingMode.add(human);
		playingMode.add(lowest);
		playingMode.add(sides);

		JPanel panelC = new JPanel();
		panelC.add(rules);
		panelC.add(comment);
		
		panelC.add(human);
		panelC.add(lowest);
		panelC.add(sides);

		JLabel chooseSpeed = new JLabel("Choose the speed (in ms):");
		speed = new JTextField(10);
		start = new JButton("Play");

		JPanel panelS = new JPanel();
		panelS.add(chooseSpeed);
		panelS.add(speed);
		panelS.add(start);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(panelN, BorderLayout.NORTH);
		mainPanel.add(panelC, BorderLayout.CENTER);
		mainPanel.add(panelS, BorderLayout.SOUTH);
		frame.add(mainPanel);
		
		ActionListener listener = new CLickListener();
		start.addActionListener(listener);
		
		frame.setVisible(true);

		remember();
		try {
			saves(highestScore);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (true) {	
			int[][] field = new int[horizontalGridSize][verticalGridSize];
			for(int i = 0; i < field.length; i++){
				for(int j = 0; j < field[i].length; j++)
				{
					// -1 in the state matrix corresponds to empty square
					// Any positive number identifies the ID of the pentomino
					field[i][j] = -1;
				}
			}
			activefield = cloneTwoDimensional(field);
			if (play) {
				score = 0;
				count++;
				gameLoop(field);  
			}
		}
	}	
	static JFrame frame = new JFrame();
	public static class CLickListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ui = new UI(horizontalGridSize, verticalGridSize, 35);
			UI.button.addActionListener(new ActionListener() { // play/stop button
				public void actionPerformed(ActionEvent e) {
					if (play) {
						play = false;
						UI.button.setText(" PLAY");
					} else {
						play = true;
						UI.button.setText(" STOP");
					}
				}
			});
			UI.pauseButton.addActionListener(new ActionListener() { //pause/resume button
				public void actionPerformed(ActionEvent e) {
					if (pause) {
						pause = false;
						UI.pauseButton.setText(" PAUSE");
					} else {
						pause = true;
						UI.pauseButton.setText(" RESUME");
					}
					System.out.println(pause);
				}
			});
			UI.resetButton.addActionListener(new ActionListener() { // reset button
				public void actionPerformed(ActionEvent e) {
					checktopleft = false;
					pieceplaced = true;
				}
			});
			if (Game.human.isSelected()) {playMode = 0;}
			if (Game.lowest.isSelected()) {playMode = 1;}
			if (Game.sides.isSelected()) {playMode = 2;}
			Game.gamespeed = Integer.parseInt(Game.speed.getText());
			frame.dispose();
		}
	}
}

