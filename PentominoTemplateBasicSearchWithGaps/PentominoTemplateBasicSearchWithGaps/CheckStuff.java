import java.util.Arrays;

public class CheckStuff{
    public static void main(String[] args) {
        int[][] firstM = {{1, 2, 3}, {1, 3, 3}, {1, 2, 3}};
        int[][] secondM = {{3, 3, 3}, {2, 3, 2}, {1, 1, 1}};

        //System.out.println(Arrays.deepEquals(firstM, secondM));
        System.out.println(isEqual(firstM, secondM));
    }



    public static boolean isEqual(int[][] data1, int[][] data2)
    {
    	if(data1[0].length != data2.length && data1.length != data2.length)
            return false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                switch (j) {
                    case 0:
                        if(Arrays.deepEquals(data1, horizontalFlip(verticalFlip(rotate(data2, i)))))
                            return true;
                    case 1:
                        if(Arrays.deepEquals(data1, rotate(data2, i)))
                            return true;
                    case 2:
                        if(Arrays.deepEquals(data1, verticalFlip(rotate(data2, i))))
                            return true;
                    case 3:
                        if(Arrays.deepEquals(data1, horizontalFlip(rotate(data2, i))))
                            return true;
                }
            }
        }
        return false;
    }


    public static int[][] horizontalFlip(int[][] data)
    {
        //make a matrix of the same size
        int[][] returnData = new int[data.length][data[0].length];
        //flip the matrix to the return matrix
        for(int i=0;i<data.length;i++)
        {
            for(int j=0;j<data[i].length;j++)
            {
                returnData[i][j]=data[data.length-i-1][j];
            }
        }
        return returnData;
    }

    public static int[][] verticalFlip(int[][] data)
    {
        //make a matrix of the same size
        int[][] returnData = new int[data.length][data[0].length];
        //flip the matrix to the return matrix
        for(int i=0;i<data.length;i++)
        {
            for(int j=0;j<data[i].length;j++)
            {
                returnData[i][j]=data[i][data[i].length-j-1];
            }
        }
        return returnData;
    }

    public static int[][] rotate(int[][] data, int rotation)
    {
        int [][] tempData1 = new int[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                tempData1[i][j]=data[i][j];
            }
        }

        //do it for the amount of times it needs to be rotated
        for(int k=0;k<rotation;k++) {
            //make a matrix of the same size
            int[][] tempData2 = new int[tempData1.length][tempData1[0].length];
            //rotate it once and put it in tempData
            for (int i = 0; i < tempData1.length; i++) {
                for (int j = 0; j < tempData1[i].length; j++) {
                    tempData2[i][j] = tempData1[j][tempData1.length - i - 1];
                }
            }
            //put it back in the starting matrix so you can do it again
            for (int i = 0; i < tempData1.length; i++) {
                for (int j = 0; j < tempData1[i].length; j++) {
                    tempData1[i][j] = tempData2[i][j];
                }
            }
        }
        return tempData1;
    }
}
