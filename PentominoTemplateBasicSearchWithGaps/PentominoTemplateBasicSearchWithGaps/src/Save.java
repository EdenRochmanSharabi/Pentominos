import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Saves the solutions which are generated
 */
public class Save {
    //public static int[] input = {1,2,4,6,7};
    public static ArrayList <String> name = new ArrayList(); 
    public static int[][] remember(int[] input){
         
        File file = new File("Solutions.csv");
        try{    
        Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) // For each line in the CSV file
            {
                // Read the line, and convert the string to a list of numbers

                String value = scanner.nextLine();
                name.add(value);
                String[] values = value.split(",");
                int length = Integer.valueOf(values[0]);
                int[] pentID = new int[length];
                int[][] field = new int[length][5];
                for (int i = 0; i < pentID.length; i++) {
                    pentID[i] = Integer.valueOf(values[i+1]);
                }
                for (int i = 0; i < length; i++) {
                    for (int j = 0; j < 5; j++) {
                        field[i][j] = Integer.valueOf(values[((i*5)+j)+1+length]);
                    }
                }
                //TODO input should not have order matter
                Arrays.sort(input);
                if(Arrays.equals(pentID, input)){
                    return field;
                }

            }

        }
        catch(FileNotFoundException e){
            System.exit(0);
        }
        int[][] hello = null;
        return hello;
    }
    public static void saves(int[][] field, int[] input) throws FileNotFoundException, UnsupportedEncodingException{

        PrintWriter writer = new PrintWriter("Solutions.csv", "UTF-8");
        for (int i = 0; i < name.size(); i++) {
            writer.write(name.get(i));
            writer.println();
        }
        Arrays.sort(input);
        String inputstring = Arrays.toString(input);
        String fieldstring = Arrays.deepToString(field);
        inputstring = inputstring.replace("[", "");
        inputstring = inputstring.replace("]", "");
        inputstring = inputstring.replace(" ", "");
        fieldstring = fieldstring.replace("[","");
        fieldstring = fieldstring.replace("]","");
        fieldstring = fieldstring.replace(" ","");


        writer.write(input.length+","+inputstring+","+fieldstring);      
        writer.close();
        
    }
    public static void main(String[] args)  throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("hey");
    }     
}