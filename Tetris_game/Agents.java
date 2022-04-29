//import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Agents{
    /**
     * Initializes the basic values
     */
    public Agents(){
        endString = "";
        onebyonepiece[0][0] = 1;
    }
    /**
     * Agents that places the piece in the lowest row possible
     * @param field
     * @param piece
     * @param activePentomino
     * @return
     */
    public static String LowestRowBot(int[][] field, int[][] piece, ActivePentomino activePentomino){
        boolean answer = true;
        ArrayList<Integer> blacklist = new ArrayList<Integer>();
        while (answer) {
            int[] Finalplace = {0, 0, 0};
            for (int k = 0; k < 4; k++) {
            piece = activePentomino.getPiece();
            for (int i = 0; i < (field[0].length-piece[0].length)+1; i++) {
                for (int j = 0; j < (field.length-piece.length)+1; j++) {
                    if (Game.checkIfFits(field, piece, j, i)){ 
                        boolean notblacklisted = true;
                            if(Finalplace[0] < i ){
                                for(int u = 0; u < blacklist.size(); u+=2){
                                    if (blacklist.get(u) == i && blacklist.get(u+1) == j){
                                        notblacklisted = false;
                                        System.out.println("eh");
                                    }
                                }
                                if(notblacklisted){
                                    Finalplace[0] = i;
                                    Finalplace[1] = j;
                                    Finalplace[2] = k;
                                }
                            }
                        }
                    }
                }
                activePentomino.rotateClockwise();
            }
            for (int i = 0; i < Finalplace[2]; i++) {
                activePentomino.rotateClockwise();
            }
            System.out.println(Arrays.toString(Finalplace));
            AgentFunctions therecursive = new AgentFunctions();
            endString = therecursive.recursiveEscape(field, activePentomino.getPiece(), Finalplace[0], Finalplace[1], activePentomino);
            System.out.println(endString);
            if (endString.length() >= Finalplace[0]) {
                answer = false;
            }
            else{
                blacklist.add(Finalplace[0]);
                blacklist.add(Finalplace[1]);
            }
            for (int i = 0; i < Finalplace[2]; i++) {
                activePentomino.rotateAntiClockwise();
            }
            for (int i = 0; i < Finalplace[2]; i++) {
                endString += "f";
            }
        }
        StringBuilder sb = new StringBuilder(endString);
        sb.reverse();
        endString = sb.toString();
        endString += "s";
        return endString ;
        }
    /**
     * Agent that gives values to the most optimal positions by giving the contact points values
     * @param field
     * @param piece
     * @param activePentomino
     * @return
     */
    public static String sidesBot(int[][] field, int[][] piece, ActivePentomino activePentomino){
        boolean answer = true;
        int filledsquares = 0;
        ArrayList<Integer> blacklist = new ArrayList<Integer>();
        while (answer) {
            int[] Finalplace = {0, 0, 0, 0};
            for (int k = 0; k < 4; k++) {
            piece = activePentomino.getPiece();
            for (int i = 0; i < (field[0].length-piece[0].length)+1; i++) {
                for (int j = 0; j < (field.length-piece.length)+1; j++) {
                    if (Game.checkIfFits(field, piece, j, i)){ 
                        boolean notblacklisted = true;
                            filledsquares = AgentFunctions.countingFilledStuff(field, piece, j, i);
                            if(Finalplace[3] < filledsquares){
                                for(int u = 0; u < blacklist.size(); u+=2){
                                    if (blacklist.get(u) == i && blacklist.get(u+1) == j){
                                        notblacklisted = false;
                                        System.out.println("eh");
                                    }
                                }
                                if(notblacklisted){
                                    Finalplace[0] = i;
                                    Finalplace[1] = j;
                                    Finalplace[2] = k;
                                    Finalplace[3] = filledsquares;
                                }
                            }
                        }
                    }
                }
                activePentomino.rotateClockwise();
            }
            for (int i = 0; i < Finalplace[2]; i++) {
                activePentomino.rotateClockwise();
            }
            System.out.println(Arrays.toString(Finalplace));
            AgentFunctions therecursive = new AgentFunctions();
            endString = therecursive.recursiveEscape(field, activePentomino.getPiece(), Finalplace[0], Finalplace[1], activePentomino);
            
            if (endString.length() >= Finalplace[0]) {
                answer = false;
            }
            else{
                blacklist.add(Finalplace[0]);
                blacklist.add(Finalplace[1]);
            }
            for (int i = 0; i < Finalplace[2]; i++) {
                activePentomino.rotateAntiClockwise();
            }
            for (int i = 0; i < Finalplace[2]; i++) {
                endString += "f";
            }
        }
        StringBuilder sb = new StringBuilder(endString);
        sb.reverse();
        endString = sb.toString();
        endString += "s";
        System.out.println(endString);
        return endString ;
        }
    private static int[][] onebyonepiece = new int[1][1];
    private static String endString;
 }
