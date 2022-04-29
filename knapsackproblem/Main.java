import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import javafx.application.Application;


/**
 * Main
 */
public class Main {
    //high is z
    //wide is y
    //long is x 
    //all of the measures are times 2 so we can use integers 
    public static int lastpiece = 0;
    public static int lastpiecePentomino = 0;
    static int X = 33;
    static int Y = 5;
    static int Z = 8;
    public static int[][][] Container = new int[Z][Y][X];
    public static int[][][] ContainerBestFitness = new int[Z][Y][X];
    static int[] indexes = new int[20];
    final static float volumeA = 16;
    final static float volumeB = 24;
    final static float volumeC = 27;
    final static double volumeTotal = 1320;
    final static int valueA = 3;
    final static int valueB = 4;
    final static int valueC = 5;
    static float[] valueabilityABC = {valueA/volumeA, valueB/volumeB, valueC/volumeC}; // A, the C, then B
    public static void main(String[] args) throws IOException {
        Cargo.config();
        Cargo.config_pentominoes();

        //new Thread(() -> Application.launch(UI.class, args)).start(); //uncomment for graphics

        //uncomment a question for the answer to it  B and D are GeneticAlgorithms and A and C are AlgorithmX
        //so press the corresponding UI button for it

        questionA(20);
        //questionB(10000);
        //questionC(20);
        //questionD(10000);
    }
    /**
     * function for Calculating EmptySquares
     * @param container
     */
    static void CalculateValues(int[][][] container){
        int count = 0;
        for (int i = 0; i < Container.length; i++) {
            for (int j = 0; j < Container[0].length; j++) {
                for (int j2 = 0; j2 < Container[0][0].length; j2++) {
                    if (Container[i][j][j2] == 0) {
                        count++;
                    }
                }
            }
        }
        System.out.println("Amount of empty spaces: " + count);
    }
    static int[][][] questionA(int basecase){
        AlgorithmX.config();
        return AlgorithmX.Algorithmx(basecase);
    }
    static int[][][] questionC(int basecase){
        AlgorithmX.configPentominoes();
        return AlgorithmX.Algorithmx(basecase);
    }
    /**
     * If parcels of type L, P and T represent values of 3, 4 and 5 units respectively, then what is the
     * maximum value that you can store in your cargo-space?
     * Answer: 
     */
    static void questionD(int generations){
        potential_load_pentomino[] pentomino =  GeneticAlgorithmPentomino.geneticAlgorithm(Container, generations, 2);
        int[] intArray = pentomino[0].getChromosome();
        ContainerBestFitness = recursiveAdding_pentomino(0, 0, 0, 0, 0, Container, intArray);
        System.out.println(Arrays.toString(intArray));
        System.out.println(lastpiecePentomino);
        System.out.println((pentomino[0].getFitnessValue()/100)*1320.0 + " while the max score theoretically possible is 1320");
    }
    /**
     * If parcels of type A, B and C represent values of 3, 4 and 5 units respectively, then what is the
     * maximum value that you can store in your cargo-space?
     * Answer: 
     */
    static void questionB(int generations){
        potential_load[] load = GeneticAlgorithm.geneticAlgorithm(Container, generations);
        char[] chararray = load[0].getChromosome();
        ContainerBestFitness = recursiveAdding(0, 0, 0, 0, 0, Container, chararray);
        System.out.println(Arrays.toString(chararray));
        System.out.println(lastpiece);
        System.out.println((load[0].getFitnessValue()/100)*247.5 + " while the max score theoretically possible is 247.5");
    }
    /**
     * Testing tools:
     */
    static void Testing_mutationRate() throws IOException{
        final int generations = 1000;
        final int averageCalculateCycles = 10;
        final double mutationEndValue = 8;
        final double mutationBeginValue = 5.5;
        final double mutationStep = 0.5;
        double mutation = mutationBeginValue;
        int positionOfWhileloop = 0;
        String[] gosha = new String[(int) ((int)(mutationEndValue-mutationBeginValue)/mutationStep)+20];
        while (mutation <= mutationEndValue){
            GeneticAlgorithm.mutationrate = mutation;
            mutation+= mutationStep;
            double average = 0;
            for (int i = 0; i < averageCalculateCycles; i++) {
                average += GeneticAlgorithm.geneticAlgorithm(Container, generations)[0].getFitnessValue();
            }
            average /= averageCalculateCycles;
            System.out.println(average);
            gosha[positionOfWhileloop] = GeneticAlgorithm.mutationrate + ", " + average;
            positionOfWhileloop++;
        }
        PrintWriter print = new PrintWriter("mutationrate.csv");
        for (int i = 0; i < gosha.length; i++) {
            print.println(gosha[i]);
        }
        print.close();
    }


    public static int charToInt(char c) {
        switch (c) {
            case 'A': 
                return 0;
            case 'B': 
                return 1;
            case 'C': 
                return 2;
            case 'D': 
                return 3;
            case 'E': 
                return 4;
            case 'F': 
                return 5;
            case 'G': 
                return 6;
            default:
                return -1;
        }
    }
    /**
     * Eden
     * @param BubbleSort algortim.
     * @return Brute force, very unoptimized but good for comparing
     */
    static potential_load [] bubbleSortFitness(potential_load Population []){
        double temp=0;
        for(int i = 0; i<Population.length-1; i++){
            if (Population[i].getFitnessValue()<Population[i+1].getFitnessValue()){ 
                temp = Population[i].getFitnessValue();
                Population[i].changeFitnessValue(Population[i+1].getFitnessValue());
                Population[i+1].changeFitnessValue(temp);
            }
        }
        
        return Population;
    }
    /**
     * adds the Pieces in a ordered way for our GA to work with all 4 variants for 4 different questions
     * @param x
     * @param y
     * @param z
     * @param piece
     * @param prepiece
     * @param addContainer
     * @param charArray
     * @return
     */
    public static int[][][] recursiveAdding_pentomino(int x, int y, int z, int piece, int prepiece, int[][][] addContainer , int[] charArray){ 
        if (x >= X && y >= Y && z >= Z || piece >= charArray.length -1) { // base case
            return addContainer;
        }
        else if (checkIfFits(x, y, z, Cargo.resultPentominoes.get(charArray[piece]), addContainer)) {
            addItem(x, y, z, Cargo.resultPentominoes.get(charArray[piece]), charArray[piece]+1, addContainer);
            if (x + 1< X) {
                return recursiveAdding_pentomino(x+1, y, z, piece+1, prepiece, addContainer, charArray);
            }
            else if (y + 1< Y) {
                return recursiveAdding_pentomino(0, y + 1, z, piece+1, prepiece, addContainer, charArray);
            }
            else if (z + 1 < Z) {
                return recursiveAdding_pentomino(0, 0, z + 1, piece+1, prepiece, addContainer, charArray);
            }
        }
        else if (x + 1< X) {
            return recursiveAdding_pentomino(x+1, y, z, piece, prepiece, addContainer, charArray);
        }
        else if (y + 1 < Y) {
            return recursiveAdding_pentomino(0, y + 1, z, piece, prepiece, addContainer, charArray);
        }
        else if (z + 1 < Z) {
            return recursiveAdding_pentomino(0, 0, z + 1, piece, prepiece, addContainer, charArray);
        }
        //recursiveAdding(x+1, y, z, piece, prepiece, addContainer, charArray);
        return addContainer;
    }
    public static int[][][] recursiveAdding(int x, int y, int z, int piece, int prepiece, int[][][] addContainer , char[] charArray){ 
        if (x >= X && y >= Y && z >= Z || piece >= charArray.length -1) { // base case
            return addContainer;
        }
        else if (checkIfFits(x, y, z, Cargo.result.get(charToInt(charArray[piece])), addContainer)) {
            addItem(x, y, z, Cargo.result.get(charToInt(charArray[piece])), charToInt(charArray[piece])+1, addContainer);
            if (x + 1< X) {
                return recursiveAdding(x+1, y, z, piece+1, prepiece, addContainer, charArray);
            }
            else if (y + 1< Y) {
                return recursiveAdding(0, y + 1, z, piece+1, prepiece, addContainer, charArray);
            }
            else if (z + 1 < Z) {
                return recursiveAdding(0, 0, z + 1, piece+1, prepiece, addContainer, charArray);
            }
        }
        else if (x + 1< X) {
            return recursiveAdding(x+1, y, z, piece, prepiece, addContainer, charArray);
        }
        else if (y + 1 < Y) {
            return recursiveAdding(0, y + 1, z, piece, prepiece, addContainer, charArray);
        }
        else if (z + 1 < Z) {
            return recursiveAdding(0, 0, z + 1, piece, prepiece, addContainer, charArray);
        }
        //recursiveAdding(x+1, y, z, piece, prepiece, addContainer, charArray);
        return addContainer;
    }
    public static int recursiveAddingValues(int x, int y, int z, int piece, int prepiece, int[][][] addContainer , char[] charArray){ 

        if ((x >= X && y >= Y && z >= Z )|| (piece >= charArray.length - 1)) { // base case
            return piece;
        }
        else if (checkIfFits(x, y, z, Cargo.result.get(charToInt(charArray[piece])), addContainer)) {
            addItem(x, y, z, Cargo.result.get(charToInt(charArray[piece])), charToInt(charArray[piece])+1, addContainer);
            lastpiece++;
            if (x < X) {
                return recursiveAddingValues(x+1, y, z, piece+1, prepiece, addContainer, charArray);
            }
            else if (y < Y) {
                return recursiveAddingValues(0, y + 1, z, piece+1, prepiece, addContainer, charArray);
            }
            else if (z  < Z) {
                return recursiveAddingValues(0, 0, z + 1, piece+1, prepiece, addContainer, charArray);
            }
        }
        else if (x < X) {
            return recursiveAddingValues(x+1, y, z, piece, prepiece, addContainer, charArray);
        }
        else if (y < Y) {
            return recursiveAddingValues(0, y + 1, z, piece, prepiece, addContainer, charArray);
        }
        else if (z < Z) {
            return recursiveAddingValues(0, 0, z + 1, piece, prepiece, addContainer, charArray);
        }
        //recursiveAdding(x+1, y, z, piece, prepiece, addContainer, charArray);
        return piece;
    }
    public static int recursiveAddingValues_pentomino(int x, int y, int z, int piece, int prepiece, int[][][] addContainer , int[] intArray){ 

        if ((x >= X && y >= Y && z >= Z )|| (piece >= intArray.length - 1)) { // base case
            return piece;
        }
        else if (checkIfFits(x, y, z, Cargo.resultPentominoes.get(intArray[piece]), addContainer)) {
            addItem(x, y, z, Cargo.resultPentominoes.get(intArray[piece]), intArray[piece]+1, addContainer);
            lastpiecePentomino++;
            if (x < X) {
                return recursiveAddingValues_pentomino(x+1, y, z, piece+1, prepiece, addContainer, intArray);
            }
            else if (y < Y) {
                return recursiveAddingValues_pentomino(0, y + 1, z, piece+1, prepiece, addContainer, intArray);
            }
            else if (z  < Z) {
                return recursiveAddingValues_pentomino(0, 0, z + 1, piece+1, prepiece, addContainer, intArray);
            }
        }
        else if (x < X) {
            return recursiveAddingValues_pentomino(x+1, y, z, piece, prepiece, addContainer, intArray);
        }
        else if (y < Y) {
            return recursiveAddingValues_pentomino(0, y + 1, z, piece, prepiece, addContainer, intArray);
        }
        else if (z < Z) {
            return recursiveAddingValues_pentomino(0, 0, z + 1, piece, prepiece, addContainer, intArray);
        }
        return piece;
    }
    /**
     * Rafael
     * find solution by brute force
     * @param listofpieces
     * @param addContainer
     */
    static void bruteForce(char[] listofpieces, int[][][] addContainer){
        int listcount = 0;
        boolean stop = false;
        while (!stop) {
        for (int i = 0; i < addContainer.length; i++) {
            for (int j = 0; j < addContainer[0].length; j++) {
                for (int j2 = 0; j2 < addContainer[0][0].length; j2++) {
                    if (listcount == listofpieces.length-1) {
                        System.out.println(Arrays.deepToString(addContainer));
                        System.out.println("solved");
                        stop = true;
                        break;
                    }
                    if (checkIfFits(i,j,j2,Cargo.generatecubes(listofpieces[listcount]), addContainer)) {
                        int[][][] add = Cargo.generatecubes(listofpieces[listcount]);
                        addItem(i,j,j2,add, add[0][0][0], addContainer);
                        System.out.println(Arrays.deepToString(addContainer));
                        listcount++;
                    }
                    }
                }
            }
        }
    
    }
    /**
     * Sonya
     * maxTable is a 2d array where i (rows) are all the elements we have (I ordered them like: "all A, all B, all C" cause it's decreasing
     * order of ratio "value/volume"); j (columns) are volumes taken. In this table you'll find maximum sum value possible for the given i and j
     * @param Na - number of A parcels
     * @param Nb - number of B parcels
     * @param Nc - number C parcels
     */
    static void checkIfPossible(int Na, int Nb, int Nc) {
        if(Na * volumeA + Nb * volumeB + Nc * volumeC <= volumeTotal){
            System.out.println("In theory it is possible to fit them all");
        }
        else{
            System.out.println("It's impossible to fit all the elements in the box");
            }
        int[][] maxTable = new int [Na + Nb + Nc][(int) (volumeTotal + 1)];
        for(int i = 0; i < maxTable.length; i++)
            for(int j = 0; j < maxTable[i].length; j++)
                maxTable[i][j] = -1;
        // I needed both volume and value for A, B and C so I created an array for easier access
        int[][] featuresOfElementsOrderedByTheirProfit = new int[Na + Nb + Nc][2];
        for(int i = 0; i < Na; i++){
            featuresOfElementsOrderedByTheirProfit[i][0] = 16; // A volume
            featuresOfElementsOrderedByTheirProfit[i][1] = 3; // A value
        }
        for(int i = Na; i < Na + Nc; i++){
            featuresOfElementsOrderedByTheirProfit[i][0] = 27; // C volume
            featuresOfElementsOrderedByTheirProfit[i][1] = 5; // C value
        }
        for(int i = Na + Nc; i < featuresOfElementsOrderedByTheirProfit.length; i++){
            featuresOfElementsOrderedByTheirProfit[i][0] = 24; // B volume
            featuresOfElementsOrderedByTheirProfit[i][1] = 4; // B value 
        }
        System.out.println(fillingMaxTable(Na + Nb + Nc - 1, (int)volumeTotal, maxTable, featuresOfElementsOrderedByTheirProfit));
        System.out.println(Arrays.deepToString(maxTable));
        // This part needs some help. I want to check that we have the whole maxTable filled
        // but idk how to do it without making a while loop with recursive function
        // that sounds bad, i know
        boolean flag = true;
        while(flag){
            int countforstop = 0;
            int i = 0;
            int j = 0;
            for(; i < maxTable.length; i++){
                for(; j < maxTable[i].length; j++){
                    if(maxTable[i][j] == -1){ //weird stuff here
                        fillingMaxTable(i, j, maxTable, featuresOfElementsOrderedByTheirProfit);
                    }
                    else{
                        countforstop++;
                    }
                }
            }
            if(countforstop == maxTable.length+maxTable[0].length-2){
                flag = false;
            }
        }
    }
     /**
     * Sonya 
     * Finds the best possible theoretical value we can fit for parcels
     * @param i
     * @param j
     * @param maxTable
     * @param featuresOfElementsOrderedByTheirProfit
     * @return
     */
    static int fillingMaxTable(int i, int j, int[][] maxTable, int[][] featuresOfElementsOrderedByTheirProfit){
        // base case
        if((i == 0 && j < featuresOfElementsOrderedByTheirProfit[0][0]) || j == 0){
            maxTable[i][j] = 0;
            return maxTable[i][j];
        }
        // also base case
        if(i == 0 && j >= featuresOfElementsOrderedByTheirProfit[0][0]){
            maxTable[i][j] = featuresOfElementsOrderedByTheirProfit[0][1];
            return maxTable[i][j]; 
        }
        // recursive part 
        else{
            if (j < featuresOfElementsOrderedByTheirProfit[i][0]){
                return fillingMaxTable(i - 1, j, maxTable, featuresOfElementsOrderedByTheirProfit);
            }
            else{
               return Math.max((featuresOfElementsOrderedByTheirProfit[i][1] + fillingMaxTable(i - 1, j - featuresOfElementsOrderedByTheirProfit[i][0], maxTable, featuresOfElementsOrderedByTheirProfit)), fillingMaxTable(i - 1, j, maxTable, featuresOfElementsOrderedByTheirProfit));
            }
        }
    }
    /**
     * Rafael
     * @param x
     * @param y
     * @param z
     * @param Piece
     * @param addContainer
     * @return
     */
    static boolean checkIfFits(int x, int y, int z, int[][][] Piece, int[][][] addContainer){
        for (int i = 0; i < Piece.length; i++) {
            for (int j = 0; j < Piece[0].length; j++) {
                for (int k = 0; k < Piece[0][0].length; k++){ 
                    try {
                        if (addContainer[z+i][y+j][x+k] > 0 && Piece[z+i][y+j][x+k] != 0)
                        {
                            return false;
                        }
                    } catch (Exception e) {
                        return false;
                        //TODO: handle exception
                    }

                }
            }
            
        }
        return true;
    }
    /**
     * Rafael
     * @param x
     * @param y
     * @param z
     * @param Piece
     * @param pieceID
     * @param addContainer
     */
    static void addItem(int x, int y, int z, int[][][] Piece, int pieceID,int[][][] addContainer){
        for (int i = 0; i < Piece.length; i++) {
            for (int j = 0; j < Piece[0].length; j++) {
                for (int j2 = 0; j2 < Piece[0][0].length; j2++) {
                    Container[z+i][j+y][j2+x] = pieceID;
                }
            }
        }
    }
}
