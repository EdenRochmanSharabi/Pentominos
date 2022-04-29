import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Random;

public class AlgorithmX {
    public static LinkedList<int[][][]> allPossiblePositions = new LinkedList<int[][][]>();
    public static LinkedList<LinkedList<int[][][]>> recursiveList = new LinkedList<LinkedList<int[][][]>>();
    public static ArrayList<Integer> endResult = new ArrayList<Integer>();
    public static LinkedList<int[][][]> tempArrayList = new LinkedList<int[][][]>();
    static int[][][] Container = new int[Main.Z][Main.Y][Main.Z];
    public static int emptyCount =0;
    public static int emptiestCount = 10000;
    public static int Basecase = 0;
    public static LinkedList<Integer> optionCount = new LinkedList<Integer>();
    public static void config(){
        
       for (int i = 0; i < Container[0].length; i++) {
           for (int j = 0; j < Container.length; j++) {
               for (int j2 = 0; j2 < Container[0][0].length; j2++) {
                   for (int k = 0; k < Cargo.result.size(); k++) {
                        if (Main.checkIfFits(j2, i, j, Cargo.result.get(k), Container)) {
                            addItem(j2, i, j, Cargo.result.get(k), k+1, Container);
                            allPossiblePositions.add(Container);
                            Container = new int[Main.Z][Main.Y][Main.X];
                        }
                    }
               }
           }
       }
    }
    public static void configPentominoes(){
        for (int i = 0; i < Container[0].length; i++) {
            for (int j = 0; j < Container.length; j++) {
                for (int j2 = 0; j2 < Container[0][0].length; j2++) {
                    for (int k = 0; k < Cargo.resultPentominoes.size(); k++) {
                         if (Main.checkIfFits(j2, i, j, Cargo.resultPentominoes.get(k), Container)) {
                             addItem(j2, i, j, Cargo.resultPentominoes.get(k), k+1, Container);
                             allPossiblePositions.add(Container);
                             Container = new int[Main.Z][Main.Y][Main.X];
                         }
                     }
                }
            }
        }
     }

    static int[][][] Algorithmx(int basecase){
        recursiveList.add(allPossiblePositions);
        optionCount.add(0);
        Basecase = basecase;
        recursiveAlgoX(0);
        return Container;
    }
    static ArrayList<Integer> recursiveAlgoX(int indexofarrays){
       // System.out.println(recursiveList.size());
        //System.out.println(recursiveList.get(indexofarrays).size());
        int options = recursiveList.get(indexofarrays).size();
        for (int i = 0; i < Container.length; i++) {
            for (int j = 0; j < Container[0].length; j++) {
                for (int j2 = 0; j2 < Container[0][0].length; j2++) {
                    if (Container[i][j][j2] == 0) {
                        emptyCount++;
                    }
                }
            }
        }
        if (emptyCount != emptiestCount) {
            emptiestCount = emptyCount;
            System.out.println(emptyCount);
            Main.ContainerBestFitness = Container;
        }
        if (emptyCount <= Basecase) {
            System.out.println(Arrays.deepToString(Container));
            return endResult;
        }
        
        else if( options == 0 || (optionCount.get(indexofarrays) >= options)){
            //System.out.println(Arrays.deepToString(Container));
            deleteContainer(recursiveList.get(indexofarrays-1).get(optionCount.get(indexofarrays-1)), Container);
            //System.out.println(Arrays.deepToString(Container));
            for (int i = 0; i < indexofarrays-1 - optionCount.size(); i++) {
                optionCount.remove(indexofarrays+i);
            }
            int i = 1;
            for ( i = 1; i < indexofarrays; i++) {
                if (recursiveList.get(indexofarrays-i).size() <= optionCount.get(indexofarrays-i)) {
                    optionCount.add(optionCount.get(indexofarrays-i), optionCount.get(indexofarrays-i) + 1);
                    break;
                }
            }
            emptyCount = 0;
            return recursiveAlgoX(indexofarrays-i);
        }
        else{
        addContainer(recursiveList.get(indexofarrays).get(optionCount.get(indexofarrays)), 1, Container);
        //endResult.add(optionCount.get(indexofarrays));
        //System.out.println(Arrays.deepToString(Container));
        for (int i = 0; i < recursiveList.get(indexofarrays).size(); i++) {
            if (checkIfFitsContainers(recursiveList.get(indexofarrays).get(i),Container)) {
                tempArrayList.add(recursiveList.get(indexofarrays).get(i));
            }
        }
        //System.out.println(tempArrayList.size());
        LinkedList<int[][][]> clone = new LinkedList<int[][][]>();
        clone.addAll(tempArrayList);
        recursiveList.add(clone);
        tempArrayList.clear();
        //System.out.println(recursiveList.get(1).size());
        //
        int place = indexofarrays + 1;
        optionCount.add(place,0);
        emptyCount = 0;
        return recursiveAlgoX(indexofarrays+=1);
    }
       

    }
    static boolean checkIfFitsContainers(int[][][] container, int[][][] addContainer){
        for (int i = 0; i < addContainer.length; i++) {
            for (int j = 0; j < addContainer[0].length; j++) {
                for (int j2 = 0; j2 < addContainer[0][0].length; j2++) {
                    try {
                        if (addContainer[i][j][j2] > 0  && container[i][j][j2] != 0){
                            //System.out.println(Arrays.deepToString(container));
                            //System.out.println(Arrays.deepToString(addContainer));
                            return false;
                        }
                    } catch (Exception e) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    static void addItem(int x, int y, int z, int[][][] Piece, int pieceID,int[][][] addContainer){
        for (int i = 0; i < Piece.length; i++) {
            for (int j = 0; j < Piece[0].length; j++) {
                for (int j2 = 0; j2 < Piece[0][0].length; j2++) {
                    if (Piece[i][j][j2] != 0){
                    Container[z+i][y+j][x+j2] = pieceID;
                    }
                }
            }
        }
    }
    static void addContainer( int[][][] Piece, int pieceID,int[][][] addContainer){
        for (int i = 0; i < Piece.length; i++) {
            for (int j = 0; j < Piece[0].length; j++) {
                for (int j2 = 0; j2 < Piece[0][0].length; j2++) {
                    if(Piece[i][j][j2] != 0){                   
                         Container[i][j][j2] = Piece[i][j][j2];
                    }
                }
            }
        }
    }
    static void deleteContainer( int[][][] Piece,int[][][] Container){
        for (int i = 0; i < Piece.length; i++) {
            for (int j = 0; j < Piece[0].length; j++) {
                for (int j2 = 0; j2 < Piece[0][0].length; j2++) {
                    if(Piece[i][j][j2] != 0){                   
                         Container[i][j][j2] = 0;
                    }
                }
            }
        }
    }
}
