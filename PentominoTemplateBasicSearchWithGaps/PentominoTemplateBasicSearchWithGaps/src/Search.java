import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/*
 * This class includes the methods to support the search of a solution.
*/
public class Search {
	//Scanner scanner = new Scanner(System.in);

	public static int cnt = 0;
	public static int verticalGridSize = 5;
	public static int horizontalGridSize = 8;
	public static char[] input = { 'I', 'F', 'V', 'Y', 'X', 'T', 'Z', 'N', 'P', 'U', 'W', 'L'};
	public static int[] intInput;
    //Static UI class to display the board
    public static UI ui;

	/*
	 * Helper function which starts a basic search algorithm
	*/
	
public static void search() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Please choose the mode: 1 - choose sizes of the grid, 2 - choose pentominos ");
		int mode = scanner.nextInt();
		scanner.nextLine();
		if(mode == 1){
			//If you want to have sizes as an input 
			System.out.print("Please choose horizontal size: ");
			horizontalGridSize = scanner.nextInt();
			System.out.print("Please choose vertical size: ");
			verticalGridSize = scanner.nextInt();
		}
		if(mode == 2) {
			// If you want to have letters as an input
			System.out.println("Choose some letters: W, Y, I, T, Z, L, X, U, V, P, N, F ");
			String playerMove = scanner.nextLine();
			playerMove = playerMove.toUpperCase(); //We make everything uppercase
			input = playerMove.toCharArray(); //We convert string to array
			horizontalGridSize = input.length; //verticalgridsize is equal to the char array length
			System.out.println(Arrays.toString(input));
		}

		ui = new UI(horizontalGridSize, verticalGridSize, 50);

		// Initialize an empty board
        int[][] field = new int[horizontalGridSize][verticalGridSize];
        for(int i = 0; i < field.length; i++){
            for(int j = 0; j < field[i].length; j++)
            {
                // -1 in the state matrix corresponds to empty square
                // Any positive number identifies the ID of the pentomino
            	field[i][j] = -1;
            }
			scanner.close();
        }
		
        //We create an array list including all our pentominos
	//We create our arraylist by adding the integers we created by converting chars 
        //We start the basic search
        //basicSearch(field);
		intInput = new int[input.length];
		for (int i = 0; i < input.length; i++) {
			intInput[i] = characterToID(input[i]);
		}
		int[][] checkInFile = Save.remember(intInput);
		System.out.println(Arrays.deepToString(checkInFile));
		if (checkInFile != null) {
			ui.setState(checkInFile);
		}
		else{
		ArrayList <Integer> ourPentIDs = new ArrayList(); //(creating arraylist with all pentominos from input)
		for(int i = 0; i < input.length; i++) { //(converting our arraylist from characters into integers)
			ourPentIDs.add(characterToID(input[i]));
		}

		//Collections.reverse(ourPentIDs);
        
        //We call the algorithm which finds solutions
		System.out.println(SearchAlgorithm(field, ourPentIDs)); 

		//We create a count of iterations of the algorithm
		System.out.println(cnt);
	}

}
	
	/**
	 * Get as input the character representation of a pentomino and translate it into its corresponding numerical value (ID)
	 * @param character a character representating a pentomino
	 * @return	the corresponding ID (numerical value)
	 */

private static int characterToID(char character) {
    	int pentID = -1; 
    	if (character == 'X') {
    		pentID = 0;
    	} else if (character == 'I') {
    		pentID = 1;
    	} else if (character == 'Z') {
    		pentID = 2;
    	} else if (character == 'T') {
    		pentID = 3;
    	} else if (character == 'U') {
    		pentID = 4;
     	} else if (character == 'V') {
     		pentID = 5;
     	} else if (character == 'W') {
     		pentID = 6;
     	} else if (character == 'Y') {
     		pentID = 7;
    	} else if (character == 'L') {
    		pentID = 8;
    	} else if (character == 'P') {
    		pentID = 9;
    	} else if (character == 'N') {
    		pentID = 10;
    	} else if (character == 'F') {
    		pentID = 11;
    	} 
        return pentID;
}
	
//We improve the efficiency of our search algorith by branching and backtracking using 
//the recursive search algorithm. We check if the chosen pentomino fits or not, if it fits,
//we add the pentomino in the cloned field (built by cloning the field). To improve the
//efficiency of the search, we check if the next squares of the placed pentominos in the cloned
//field are empty or not, which basically serves as pruning.

public static boolean SearchAlgorithm(int[][] field, ArrayList <Integer> ourPentIDs) {
		ui.setState(field); //This is to visualize

		//Base case: if the whole field is filled, we found a solution yay!
		if(checkIfFilled(field)){
			if (horizontalGridSize == input.length) {
				try {
					Save.saves(field, intInput);
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					System.out.println("error with file or encoding");
				}
			}
			ui.setState(field); //visualization 
			return true;
		}
		
		//Recursive part: creating branches and backtracking
		for(int x = 0; x < field.length; x++) {  //loop over x index in field
			for(int y = 0; y < field[x].length; y++) {  //loop over y index in field
				for(Integer pentID: ourPentIDs) {  //next steps are made for every pentomino
					for(int mutationID = 0; mutationID < PentominoDatabase.data[pentID].length; mutationID++){ //for every mutation
						int[][] piece = PentominoDatabase.data[pentID][mutationID]; //create a set of pentominos choosing type of pentomino and type of mutation
						if(checkIfFits(field, piece, x, y)){ //use chekIfFits to chek whether we can place the chosen pemtonino starting at sell (x,y) in field
							
							int[][] field_clone = cloneTwoDimensional(field); //make a clone of field
							addPiece(field_clone, piece, pentID, x, y); //add the pentomino to the cloned version
							cnt++; //increase the number of iterations
							ArrayList<Integer> ourNewPentIDs = (ArrayList<Integer>) ourPentIDs.clone(); // make a clone of list with pentominos
							ourNewPentIDs.remove(pentID); //remove the used pentomino

							if(checkNextSquaresEmpty(field_clone)) //some improvement of search
								if(SearchAlgorithm(field_clone, ourNewPentIDs)) //recursive call
									return true;
						}
					}	
				}
			}
		}
	    return false;
}

//We create a method to check if the field is filled. If the field is filles, the method returns
//boolean value of true. If it is not, then false. We understand if the field is filled or not
//by checking if the field value is -1. We defined empty squares as -1 at the beginning.
public static boolean checkIfFilled(int[][] field) {
		for (int i = 0; i < horizontalGridSize; i++) {
			for (int j = 0; j < verticalGridSize; j++) {
				if(field[i][j] == -1) 
					return false;
			}
		}
	return true;
}

//We created a method to make a clone of the input array. We use this algorithm in our 
//recursive search algorithm
public static int[][] cloneTwoDimensional(int[][] array) {
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

public static void addPiece(int[][] field, int[][] piece, int pieceID, int x, int y) {
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

//We create this agorithm to check if we can we can place the pemtonino we chose starting 
//at a (x,y) point of the field. 
public static boolean checkIfFits(int[][] field, int[][] piece, int x, int y) {
        for(int i = 0; i < piece.length; i++) // loop over x position of pentomino
        {
            for (int j = 0; j < piece[i].length; j++) // loop over y position of pentomino
            {
                if (piece[i][j] == 1 && (x + i >= field.length || y + j >= field[0].length || field[x + i][y + j] != -1)){
					return false;
                }
            }
        }
	    return true;
}
//This algorithm is intended to increase the efficiency of the search algorithm by 
//stopping the search ie. Pruning in case there is no available space.
public static boolean checkNextSquaresEmpty(int[][] new_field) {
		for (int i = 0; i < new_field.length; i++) {
			for (int j = 0; j < new_field[0].length; j++) {
				if (new_field[i][j] == -1) {
					return checkEmpty(i, j, new_field);
				}
			}
		}
		return true;
}

public static boolean checkEmpty(int i, int j, int[][] new_field) {
	
		if(i == 0 && j == 0){
			if (new_field[i][j+1] == -1 || new_field[i+1][j] == -1) {}
			else{return false;}
		}
		else if (i == 0 && j == new_field[0].length-1){
			if (new_field[i][j-1] == -1 || new_field[i+1][j] == -1) {}
			else{return false;}
		}
		else if (j == 0 && i == new_field.length-1){
			if (new_field[i][j+1] == -1 || new_field[i-1][j] == -1) {}
			else{return false;}
		}
		else if (i == 0){
			if (new_field[i][j-1] == -1 || new_field[i][j+1] == -1 || new_field[i+1][j] == -1) {}
			else{return false;}
		}
		else if (j == 0){
			if (new_field[i][j+1] == -1 || new_field[i-1][j] == -1 ||new_field[i+1][j] == -1) {}
			else{return false;}
		}
		else if (i == new_field.length-1 && j == new_field[i].length-1){
			if (new_field[i-1][j] == -1 || new_field[i][j-1] == -1) {}
			else{return false;}
		}
		else if (i == new_field.length-1){
			if (new_field[i][j+1] == -1 || new_field[i-1][j] == -1 || new_field[i][j-1] == -1) {}
			else{return false;}
		}
		else if (j == new_field[i].length-1){
			if ( new_field[i-1][j] == -1 || new_field[i+1][j] == -1 || new_field[i][j-1] == -1) {}
			else{return false;}
		}
		else{
			if (new_field[i][j+1] == -1 || new_field[i-1][j] == -1 || new_field[i+1][j] == -1 || new_field[i][j-1] == -1) {}
			else{return false;}
		} 
		return true;
}

/*public static boolean twoEmptySquares(int[][] field){
		boolean sameRow = false;
		for(int i = 0; i < field.length; i++){
			for(int j = 0; j < field[i].length - 1; j++){
				if(field[i][j] == -1)
			}
		}
}*/

/**
* Main function. Needs to be executed to start the basic search algorithm
*/

public static void main(String[] args) {
	search();
}

/*
	 * Basic implementation of a search algorithm. It is not a bruto force algorithms (it does not check all the posssible combinations)
	 * but randomly takes possible combinations and positions to find a possible solution.
	 * The solution is not necessarily the most efficient one
	 * This algorithm can be very time-consuming
	 * @param field a matrix representing the board to be fulfilled with pentominoes
*/

private static void basicSearch(int[][] field) {
    	Random random = new Random();
    	boolean solutionFound = false;
    	
    	while (!solutionFound) {
    		solutionFound = true;
    		
    		//Empty board again to find a solution
			for (int i = 0; i < field.length; i++) {
				for (int j = 0; j < field[i].length; j++) {
					field[i][j] = -1;
				}
			}
    		
    		//Put all pentominoes with random rotation/flipping on a random position on the board
    		for (int i = 0; i < input.length; i++) {
    			
    			//Choose a pentomino and randomly rotate/flip it
    			int pentID = characterToID(input[i]);
    			int mutation = random.nextInt(PentominoDatabase.data[pentID].length);
    			int[][] pieceToPlace = PentominoDatabase.data[pentID][mutation];

    			//Randomly generate a position to put the pentomino on the board
    			int x;
    			int y;
    			if (horizontalGridSize < pieceToPlace.length) {
    				//this particular rotation of the piece is too long for the field
    				x=-1;
    			} else if (horizontalGridSize == pieceToPlace.length) {
    				//this particular rotation of the piece fits perfectly into the width of the field
    				x = 0;
    			} else {
    				//there are multiple possibilities where to place the piece without leaving the field
    				x = random.nextInt(horizontalGridSize-pieceToPlace.length+1);
    			}

    			if (verticalGridSize < pieceToPlace[0].length) {
    				//this particular rotation of the piece is too high for the field
    				y=-1;
    			} else if (verticalGridSize == pieceToPlace[0].length) {
    				//this particular rotation of the piece fits perfectly into the height of the field
    				y = 0;
    			} else {
    				//there are multiple possibilities where to place the piece without leaving the field
    				y = random.nextInt(verticalGridSize-pieceToPlace[0].length+1);
    			}
    		
    			//If there is a possibility to place the piece on the field, do it
    			if (x >= 0 && y >= 0) {
	    			addPiece(field, pieceToPlace, pentID, x, y);
	    		} 
    		}

    		//Check whether complete field is filled
    		for (int i = 0; i < horizontalGridSize; i++) {
				for (int j = 0; j < verticalGridSize; j++) {
					if(field[i][j] == -1) //-1 because this was defined at the start
						solutionFound = false;
				}
			}
    		
    		if (solutionFound) {
    			//display the field
    			ui.setState(field); 
    			System.out.println("Solution found");
				
				//System.exit(0);
    			 break;
				
    		}
			
    	}
}

}
