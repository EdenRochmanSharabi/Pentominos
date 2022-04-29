public class AgentFunctions {
    public AgentFunctions(){
        finalstring = " ";
    }
    /**
     * Function that checks if the postion calculated by the agent is possible and returns the series of input if it is
     * @param field
     * @param piece
     * @param y
     * @param x
     * @param activePentomino
     * @return
     */
    public String recursiveEscape(int[][] field, int[][] piece, int y, int x, ActivePentomino activePentomino){
        if (y == 0 && x == 0) {
            return finalstring;
        }
        if (y > 0 && Game.checkIfFits(field, piece, x, y-1)) {
            finalstring += "s";
            recursiveEscape(field, piece, y-1, x, activePentomino);
        }
        else if(Game.checkIfFits(field, piece, x-1, y) && finalstring.charAt(finalstring.length()-1) != 'a'){
            finalstring += "d";
            recursiveEscape(field, piece, y, x-1, activePentomino); 
        }
        else if(Game.checkIfFits(field, piece, x+1, y) && finalstring.charAt(finalstring.length()-1) != 'd'){
            finalstring += "a";
            recursiveEscape(field, piece, y, x+1, activePentomino); 
        }
            
        return finalstring;
    }
    /**
     * Function which assigns values to different sides of the Pentomino
     * @param field
     * @param piece
     * @param x
     * @param y
     * @return
     */
    public static int countingFilledStuff(int[][] field, int[][] piece, int x, int y){
        int count = 0;
            for (int i = 0; i < piece.length; i++) {
                for (int j = 0; j < piece[0].length; j++) {
                    if (piece[i][j] > 0) {
                        try{if(field[i+x-1][j+y] > -1){count+=leftsides;}}
                        catch(ArrayIndexOutOfBoundsException e){count+=leftsidesoutofbounds;}
                        try{if(field[i+x+1][j+y] > -1){count+=rightsides;}}
                        catch(ArrayIndexOutOfBoundsException e){count+=rightsidesoutofbounds;}
                        try{if(field[i+x][j+y-1] > -1){count+=up;}}
                        catch(ArrayIndexOutOfBoundsException e){count+=upoutofbounds;}
                        try{if(field[i+x][j+y+1] > -1){count+=bottompiece;} }
                        catch(ArrayIndexOutOfBoundsException e){count+=bottomofbounds;}
                    }
                }
            }
        return count;
    }
    public static int rightsides = 6;
    public static int leftsides = 6;
    public static int bottomofbounds = 7;
    public static int bottompiece = 8;
    public static int leftsidesoutofbounds = 5;
    public static int rightsidesoutofbounds = 5;
    public static int upoutofbounds = 1;
    public static int up = 1;
    private static String finalstring;
}
