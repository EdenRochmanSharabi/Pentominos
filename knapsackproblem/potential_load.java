import java.util.Arrays;

public class potential_load {
    public  potential_load(char[] chromosome, double fitnessValue){
        this.chromosome = chromosome;
        this.fitnessValue = fitnessValue;
        
    }
    public char[] getChromosome(){
        return chromosome;
    }
    
    public double getFitnessValue(){
        return fitnessValue;
    }
    public void changeFitnessValue(double newValue){
        fitnessValue = newValue;
    }
    public void changeChromosome(char[] newChromosome){
        chromosome = Arrays.copyOf(newChromosome, newChromosome.length);
    }
    private char[] chromosome; 
    private double fitnessValue;

}