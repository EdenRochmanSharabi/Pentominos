import java.util.Arrays;

public class potential_load_pentomino {
    public  potential_load_pentomino(int[] chromosome, double fitnessValue){
        this.chromosome = chromosome;
        this.fitnessValue = fitnessValue;
        
    }
    public int[] getChromosome(){
        return chromosome;
    }
    
    public double getFitnessValue(){
        return fitnessValue;
    }
    public void changeFitnessValue(double newValue){
        fitnessValue = newValue;
    }
    public void changeChromosome(int[] newChromosome){
        chromosome = Arrays.copyOf(newChromosome, newChromosome.length);
    }
    private int[] chromosome; 
    private double fitnessValue;

}