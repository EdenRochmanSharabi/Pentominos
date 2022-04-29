public class mutationrateMutator {
    private static double mutationrate;

    public mutationrateMutator(double mutationrate){
        mutationrateMutator.mutationrate = mutationrate;
    }
    public static double getMutation(){
        if(mutationrate<100) mutationrate++;
        return mutationrate;
    }
}
