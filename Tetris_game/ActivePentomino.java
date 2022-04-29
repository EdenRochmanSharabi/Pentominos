import java.util.Random;

public class ActivePentomino {
    private int xPosition;
    private int yPosition;
    private int[][] Piece;
    private int rotation;
    private int randomint;
    /**
     * contructs a random pentomino with random rotation at 0,0
     */
    public ActivePentomino(){
        
        
        Random rand = new Random();
        randomint = rand.nextInt(12);
        if(randomint == 0){
            rotation = 0;
            Piece = PentominoDatabase.data[randomint][rotation];
        }
        else if(randomint == 1 || randomint == 2){
            rotation = rand.nextInt(2);
            Piece = PentominoDatabase.data[randomint][rotation];
        }
            
        else{
            rotation = rand.nextInt(4);
            Piece = PentominoDatabase.data[randomint][rotation];
        }
            
        yPosition = 0;
        xPosition = 0;
    }
    /**
     * Accessor function for the pieceID
     * @return
     */
    public int getPieceId(){
        return randomint;
    }
    /**
     * Mutator function to move left
     */
    public void shiftLeft(){
        xPosition--;
    }
    /**
     * Mutator function to move right
     */
    public void shiftRight(){
        xPosition++;
    }
    /**
     * Mutator function to move down
     */
    public void shiftDown(){
        yPosition++;
    }
    /**
     * Mutator function to Rotate
     */
    public void rotateClockwise(){
        if(randomint == 0){
            Piece = PentominoDatabase.data[randomint][rotation];
        }
        else{
            rotation++;
            if((randomint == 1 || randomint == 2) && rotation == 2){
                rotation = 0;
            }
            else if((randomint > 2 && randomint < 12) && rotation == 4){
                rotation = 0;
            }
            Piece = PentominoDatabase.data[randomint][rotation];
        }
    }
    /**
     *  Mutator function to Rotate
     */
    public void rotateAntiClockwise(){
        if(randomint == 0){
            Piece = PentominoDatabase.data[randomint][rotation];
        }
        else{
            rotation--;
            if((randomint == 1 || randomint == 2) && rotation == -1){
                rotation = 1;
            }
            else if((randomint > 2 && randomint < 12) && rotation == -1){
                rotation = 3;
            }
            Piece = PentominoDatabase.data[randomint][rotation];
        }
    }
    /**
     * Accessor Function for x coordinate
     */
    public int GetXPosition(){
        return xPosition;
    }
    /**
     * Accessor Function for y coordinate
     */
    public int GetYPosition(){
        return yPosition;
    }
    /**
     * mutator function for the x coordinate
     * @param X
     */
    public void takeXPositon(int X){
        xPosition = X;
    }
    /**
     * mutator function for the y coordinate
     * @param Y
     */
    public void takeYPositon(int Y){
        yPosition = Y;
    }
    /**
     * Accessor for the piece array
     * @return
     */
    public int[][] getPiece(){
        return Piece;
    }
}
