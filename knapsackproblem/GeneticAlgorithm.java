import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class GeneticAlgorithm {
    public static char[] alphabet = new char[7];
    static int PopulationSize = 50; 
    final static int String_length = 83; //Length of string Calculated for A
    static int child_count = 6;
    static int currentChild_count;
    static double mutationrate = 7; //percentage of mutation 
    static int crossoverPosition;

    /**
     * Genetic Algorithm
     * @param Container
     * @return
     */
    public static potential_load[] geneticAlgorithm(int[][][] Container, int generations){
        potential_load[] Population = generatepopulation(Container);
        optimizationSortFitness(Population);
        for (int k = 0; k < generations; k++) { // Number of generations
            int amountOfChildren = PopulationSize - child_count; 
            for (int i = 0; i < amountOfChildren/2; i++) {
                Random rand = new Random();
                crossover(Population[rand.nextInt(6)].getChromosome(), Population[rand.nextInt(6)].getChromosome(), Population);
            }
            for (int i = 0; i < Population.length; i++) {
                Population[i].changeFitnessValue(calculateFitnessWithAddedValue(Population[i].getChromosome(), Container));
            }
            optimizationSortFitness(Population);
            currentChild_count = child_count;
            // System.out.println(mutationrate);
           System.out.println(Population[0].getFitnessValue());
            // System.out.println("mutationratemutationratemutationratemutationrate= "+mutationrate);
        }
        return Population;
    }
    /**
     * Gaussian distribution generator
     * @return random variable generated by a gaussian distributor and shifted to be greater than 2
     */
    public static double next_gaussian() {
    Random random = new Random();
    double x = random.nextGaussian();  // Generation the variable. It generates an initial [-1,1] gaussian distribution
    double y = (x * 0.5) + 2;          // Shift it to be greater than 1
    return Math.rint(y * 100000.0) * 0.00001; // Quantize to step size 0.00001
    } 
    /**
     * TODO Sonya
     * @param parent1
     * @param parent2
     * @param Population
     */
    public static void crossover(char[] parent1, char[] parent2, potential_load[] Population) {
        crossoverPosition = generateCrossoverPosition(parent1);
        char[] child1 = new char[parent1.length];
        char[] child2 = new char[parent1.length];
        // making child 1 and 2
        for (int i = 0; i <= crossoverPosition; i++) {
            Random random2 = new Random();
            if((int)mutationrate > random2.nextInt(100)){
                child1[i] = intToChar(random2.nextInt(7)); // Randomly choose where to cut
                child2[i] = intToChar(random2.nextInt(7));
            }
            else{
            child1[i] = parent1[i];
            child2[i] = parent2[i];
            }
        }
        for(int i = crossoverPosition+1; i < child1.length; i++){
            Random random2 = new Random();
            if((int)mutationrate > random2.nextInt(100)){
                child1[i] = intToChar(random2.nextInt(7));
                child2[i] = intToChar(random2.nextInt(7));
            }
            else{
            child1[i] = parent2[i];
            child2[i] = parent1[i];
            }
        }
        Population[child_count].changeChromosome(child1);
        currentChild_count++; 
        Population[child_count].changeChromosome(child2);
        currentChild_count++;
    }

    /**
     * Simple helper function that returns the correspondent character
     * @param i
     * @return
     */

    public static char intToChar(int i){
        switch(i){
            case 0:
                return 'A';
            case 1:
                return 'B';
            case 2: 
                return 'C';
            case 3: 
                return 'D';
            case 4: 
                return 'E';
            case 5: 
                return 'F';
            case 6: 
                return 'G';
            default:
                return 'z';   
        }
    }

    /**
     * This method generates a random index which will be a crossover point
     * @param parent1
     * @return
     */
    public static int generateCrossoverPosition(char[] parent1) {
        double parts = parent1.length;
        double random = Math.random();
        int numerator = 1;
        while(numerator <= parts){
            if(numerator == 1){
                if(random <= numerator/parts){
                    crossoverPosition = 0;
                    break;
                }
            }
            else{
                if(random > ((numerator-1)/parts) && random <= numerator/parts){
                    crossoverPosition = numerator - 1;
                    break;
                }
            }
            numerator++;
        }
        return crossoverPosition;
    }

    /**
     * Generates the chromoses, why did we do it with characters? no idea.
     * @param Container
     * @return
     */

    public static potential_load[] generatepopulation(int[][][] Container) {
        //Building a char list : alphabet containing letters from A to G.
        for(char c = 'A'; c <= 'G'; c++){
            alphabet[c -'A'] = c;
        }
        // Building Object list : our population
        potential_load[] Population = new potential_load[PopulationSize];
        // Building Random number generator
        int random;
        Random generator = new Random();
        // creating population of individuals built randomly.
        for(int i = 0; i < PopulationSize; i++){
            String Chromosome = "";
            for(int j = 0; j < String_length ; j++){ // length 7 : ABCDEFG
                random = generator.nextInt(7); // Random number generator between 0 and 7.
                Chromosome += alphabet[random];
            }
            Population[i] = new potential_load(Chromosome.toCharArray(), calculateFitnessWithAddedValue(Chromosome.toCharArray(), Container));
        }
        // to print population
       /*
        for(int i = 0; i<Population.length; i++){
                System.out.println(Arrays.toString(Population[i].getChromosome()));
                System.out.println(Population[i].getFitnessValue());
        }
        */

        return Population;
    }

    /**
     * Sorts the Population using the fitness values
     * pretty fast
     * @param Population
     * @return
     */

    static potential_load [] optimizationSortFitness(potential_load[] Population){
        Arrays.sort(Population, (b, a) -> Double.compare(a.getFitnessValue(), b.getFitnessValue()));
        return Population;
    }

    /**
     * Calculates fitness based on the empty squares
     * @param chararray
     * @param addContainer
     * @return
     */

    static double calculateFitness(char[] chararray, int[][][] addContainer){
        int fitness = 0;
        
        for (int i = 0; i < addContainer.length; i++) {
            for (int j = 0; j < addContainer[0].length; j++) {
                for (int j2 = 0; j2 < addContainer[0][0].length; j2++) {
                    addContainer[i][j][j2] = 0;
                }
            }
        }
        addContainer = Main.recursiveAdding(0, 0, 0, 0, 0, addContainer, chararray);
        for (int i = 0; i < addContainer.length; i++) {
            for (int j = 0; j < addContainer[0].length; j++) {
                for (int j2 = 0; j2 < addContainer[0][0].length; j2++) {
                    if(addContainer[i][j][j2] != 0){
                        fitness += 1;
                    }
                }
            }
        }
        return (fitness/Main.volumeTotal)*100.0;
    }
    /**
     * calculates fitness based on the values of the Pieces
     * @param chararray
     * @param addContainer
     * @return
     */
    static double calculateFitnessWithAddedValue(char[] chararray, int[][][] addContainer){
        double fitness = 0;
        Main.lastpiece = 0;
        for (int i = 0; i < addContainer.length; i++) {
            for (int j = 0; j < addContainer[0].length; j++) {
                for (int j2 = 0; j2 < addContainer[0][0].length; j2++) {
                    addContainer[i][j][j2] = 0;
                }
            }
        }
        Main.recursiveAddingValues(0, 0, 0, 0, 0, addContainer, chararray);
        for (int i = 0; i < Main.lastpiece; i++) {
            fitness += calculateValue(chararray[i]);
        }
        return (fitness/247.5)*100.0;
    }
    //just for visuals
    static int calculateValue(char c){
        switch (c) {
            case 'A':
                return 3;
                case 'B':
                return 3;
                case 'C':
                return 4;
                case 'D':
                return 4;
                case 'E':
                return 4;
                case 'F':
                return 4;
                case 'G':
                return 5; 
                default: 
                return 0;
        }
    }
}