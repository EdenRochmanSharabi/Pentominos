import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Cargo {
    public static ArrayList<int[][][]> result = new ArrayList<int[][][]>();
    public static ArrayList<int[][][]> resultPentominoes = new ArrayList<int[][][]>();
    public static void config_pentominoes(){
        for (int i = 0; i < 56; i++) {
            resultPentominoes.add(allPentominoes(i));
        }
    }
    public static int[][][] allPentominoes(int n){
        switch(n){
            // here starts T
            case 0:
                int[][][] T0 = new int[1][3][3];
                T0[0][0][1] = 6;
                T0[0][1][1] = 6;
                T0[0][2][0] = 6;
                T0[0][2][1] = 6;
                T0[0][2][2] = 6;
                return T0;
            case 1:
                int[][][] T1 = new int[1][3][3];
                T1[0][0][0] = 6;
                T1[0][0][1] = 6;
                T1[0][0][2] = 6;
                T1[0][1][1] = 6;
                T1[0][2][1] = 6;
                return T1;
            case 2:
                int[][][] T2 = new int[1][3][3];
                T2[0][0][0] = 6;
                T2[0][1][0] = 6;
                T2[0][1][1] = 6;
                T2[0][1][2] = 6;
                T2[0][2][0] = 6;
                return T2;
            case 3:
                int[][][] T3 = new int[1][3][3];
                T3[0][0][2] = 6;
                T3[0][1][0] = 6;
                T3[0][1][1] = 6;
                T3[0][1][2] = 6;
                T3[0][2][2] = 6;
                return T3;
            case 4:
                int[][][] T4 = new int[3][3][1];
                T4[2][2][0] = 6;
                T4[1][0][0] = 6;
                T4[1][1][0] = 6;
                T4[1][2][0] = 6;
                T4[0][2][0] = 6;
                return T4;
            case 5:
                int[][][] T5 = new int[3][3][1];
                T5[0][0][0] = 6;
                T5[1][0][0] = 6;
                T5[2][0][0] = 6;
                T5[1][1][0] = 6;
                T5[1][2][0] = 6;
                return T5;
            case 6:
                int[][][] T6 = new int[3][3][1];
                T6[0][1][0] = 6;
                T6[1][1][0] = 6;
                T6[2][0][0] = 6;
                T6[2][1][0] = 6;
                T6[2][2][0] = 6;
                return T6;
            case 7:
                int[][][] T7 = new int[3][3][1];
                T7[0][0][0] = 6;
                T7[0][1][0] = 6;
                T7[0][2][0] = 6;
                T7[1][1][0] = 6;
                T7[2][1][0] = 6;
                return T7;
            // here starts L
            case 8:
                int[][][] L8 = new int[1][4][2];
                L8[0][0][0] = 5;
                L8[0][0][1] = 5;
                L8[0][1][0] = 5;
                L8[0][2][0] = 5;
                L8[0][3][0] = 5;
                return L8;
            case 9:
                int[][][] L9 = new int[1][2][4];
                L9[0][0][0] = 5;
                L9[0][1][0] = 5;
                L9[0][1][1] = 5;
                L9[0][1][2] = 5;
                L9[0][1][3] = 5;
                return L9;
            case 10:
                int[][][] L10 = new int[1][4][2];
                L10[0][3][0] = 5;
                L10[0][3][1] = 5;
                L10[0][2][1] = 5;
                L10[0][1][1] = 5;
                L10[0][0][1] = 5;
                return L10;
            case 11:
                int[][][] L11 = new int[1][2][4];
                L11[0][0][0] = 5;
                L11[0][0][1] = 5;
                L11[0][0][2] = 5;
                L11[0][0][3] = 5;
                L11[0][1][3] = 5;
                return L11;
            case 12:
                int[][][] L12 = new int[1][4][2];
                L12[0][0][0] = 5;
                L12[0][0][1] = 5;
                L12[0][1][1] = 5;
                L12[0][2][1] = 5;
                L12[0][3][1] = 5;
                return L12;
            case 13:
                int[][][] L13 = new int[1][2][4];
                L13[0][0][0] = 5;
                L13[0][1][0] = 5;
                L13[0][0][1] = 5;
                L13[0][0][2] = 5;
                L13[0][0][3] = 5;
                return L13;
            case 14:
                int[][][] L14 = new int[1][4][2];
                L14[0][0][0] = 5;
                L14[0][1][0] = 5;
                L14[0][2][0] = 5;
                L14[0][3][0] = 5;
                L14[0][3][1] = 5;
                return L14;
            case 15:
                int[][][] L15 = new int[1][2][4];
                L15[0][1][0] = 5;
                L15[0][1][1] = 5;
                L15[0][1][2] = 5;
                L15[0][1][3] = 5;
                L15[0][0][3] = 5;
                return L15;
            case 16:
                int[][][] L16 = new int[2][4][1];
                L16[0][0][0] = 5;
                L16[1][0][0] = 5;
                L16[0][1][0] = 5;
                L16[0][2][0] = 5;
                L16[0][3][0] = 5;
                return L16;
            case 17:
                int[][][] L17 = new int[2][1][4];
                L17[0][0][0] = 5;
                L17[1][0][0] = 5;
                L17[0][0][1] = 5;
                L17[0][0][2] = 5;
                L17[0][0][3] = 5;
                return L17;
            case 18:
                int[][][] L18 = new int[2][4][1];
                L18[0][0][0] = 5;
                L18[0][1][0] = 5;
                L18[0][2][0] = 5;
                L18[0][3][0] = 5;
                L18[1][3][0] = 5;
                return L18;
            case 19:
                int[][][] L19 = new int[2][1][4];
                L19[0][0][0] = 5;
                L19[0][0][1] = 5;
                L19[0][0][2] = 5;
                L19[0][0][3] = 5;
                L19[1][0][3] = 5;
                return L19;
            case 20:
                int[][][] L20 = new int[2][4][1];
                L20[0][0][0] = 5;
                L20[1][0][0] = 5;
                L20[1][1][0] = 5;
                L20[1][2][0] = 5;
                L20[1][3][0] = 5;
                return L20;
            case 21:
                int[][][] L21 = new int[2][1][4];
                L21[0][0][0] = 5;
                L21[1][0][0] = 5;
                L21[1][0][1] = 5;
                L21[1][0][2] = 5;
                L21[1][0][3] = 5;
                return L21;
            case 22:
                int[][][] L22 = new int[2][4][1];
                L22[0][3][0] = 5;
                L22[1][0][0] = 5;
                L22[1][1][0] = 5;
                L22[1][2][0] = 5;
                L22[1][3][0] = 5;
                return L22;
            case 23:
                int[][][] L23 = new int[2][1][4];
                L23[0][0][3] = 5;
                L23[1][0][0] = 5;
                L23[1][0][1] = 5;
                L23[1][0][2] = 5;
                L23[1][0][3] = 5;
                return L23;
            case 24:
                int[][][] L24 = new int[4][2][1];
                L24[0][0][0] = 5;
                L24[1][0][0] = 5;
                L24[2][0][0] = 5;
                L24[3][0][0] = 5;
                L24[3][1][0] = 5;
                return L24;
            case 25:
                int[][][] L25 = new int[4][1][2];
                L25[0][0][0] = 5;
                L25[1][0][0] = 5;
                L25[2][0][0] = 5;
                L25[3][0][0] = 5;
                L25[3][0][1] = 5;
                return L25;
            case 26:
                int[][][] L26 = new int[4][2][1];
                L26[0][1][0] = 5;
                L26[1][1][0] = 5;
                L26[2][1][0] = 5;
                L26[3][1][0] = 5;
                L26[3][0][0] = 5;
                return L26;
            case 27:
                int[][][] L27 = new int[4][1][2];
                L27[3][0][0] = 5;
                L27[3][0][1] = 5;
                L27[2][0][1] = 5;
                L27[1][0][1] = 5;
                L27[0][0][1] = 5;
                return L27;
            case 28:
                int[][][] L28 = new int[4][2][1];
                L28[0][0][0]= 5;
                L28[0][1][0]= 5;
                L28[1][0][0]= 5;
                L28[2][0][0]= 5;
                L28[3][0][0]= 5;
                return L28;
            case 29:
                int[][][] L29 = new int[4][1][2];
                L29[0][0][0] = 5;
                L29[0][0][1] = 5;
                L29[1][0][0] = 5;
                L29[2][0][0] = 5;
                L29[3][0][0] = 5;
                return L29;
            case 30:
                int[][][] L30 = new int[4][2][1];
                L30[0][0][0] = 5;
                L30[0][1][0] = 5;
                L30[1][1][0] = 5;
                L30[2][1][0] = 5;
                L30[3][1][0] = 5;
                return L30;
            case 31:
                int[][][] L31 = new int[4][1][2];
                L31[0][0][0] = 5;
                L31[0][0][1] = 5;
                L31[1][0][1] = 5;
                L31[2][0][1] = 5;
                L31[3][0][1] = 5;
                return L31;
            // here starts P
            case 32:
                int[][][] P32 = new int[1][3][2];
                P32[0][0][0] = 4;
                P32[0][1][0] = 4;
                P32[0][1][1] = 4;
                P32[0][2][0] = 4;
                P32[0][2][1] = 4;
                return P32;
            case 33:
                int[][][] P33 = new int[1][2][3];
                P33[0][1][0] = 4;
                P33[0][0][1] = 4;
                P33[0][1][1] = 4;
                P33[0][0][2] = 4;
                P33[0][1][2] = 4;
                return P33;
            case 34:
                int[][][] P34 = new int[1][3][2];
                for(int i = 0; i < P34.length; i++){
                    for(int j = 0; j < P34[i].length; j++){
                        for(int k = 0; k < P34[i][j].length; k++){
                            P34[i][j][k] = 4;
                        }
                    }
                }
                P34[0][2][0] = 0;
                return P34;
            case 35:
                int[][][] P35 = new int[1][2][3];
                for(int i = 0; i < P35.length; i++){
                    for(int j = 0; j < P35[i].length; j++){
                        for(int k = 0; k < P35[i][j].length; k++){
                            P35[i][j][k] = 4;
                        }
                    }
                }
                P35[0][1][2] = 0;
                return P35;
            case 36:
                int[][][] P36 = new int[1][3][2];
                for(int i = 0; i < P36.length; i++){
                    for(int j = 0; j < P36[i].length; j++){
                        for(int k = 0; k < P36[i][j].length; k++){
                            P36[i][j][k] = 4;
                        }
                    }
                }
                P36[0][0][0] = 0;
                return P36;
            case 37:
                int[][][] P37 = new int[1][2][3];
                for(int i = 0; i < P37.length; i++){
                    for(int j = 0; j < P37[i].length; j++){
                        for(int k = 0; k < P37[i][j].length; k++){
                            P37[i][j][k] = 4;
                        }
                    }
                }
                P37[0][1][0] = 0;
                return P37;
            case 38:
                int[][][] P38 = new int[1][3][2];
                for(int i = 0; i < P38.length; i++){
                    for(int j = 0; j < P38[i].length; j++){
                        for(int k = 0; k < P38[i][j].length; k++){
                            P38[i][j][k] = 4;
                        }
                    }
                }
                P38[0][2][1] = 0;
                return P38;
            case 39:
                int[][][] P39 = new int[1][2][3];
                for(int i = 0; i < P39.length; i++){
                    for(int j = 0; j < P39[i].length; j++){
                        for(int k = 0; k < P39[i][j].length; k++){
                            P39[i][j][k] = 4;
                        }
                    }
                }
                P39[0][0][2] = 0;
                return P39;
            case 40:
                int[][][] P40 = new int[2][3][1];
                for(int i = 0; i < P40.length; i++){
                    for(int j = 0; j < P40[i].length; j++){
                        for(int k = 0; k < P40[i][j].length; k++){
                            P40[i][j][k] = 4;
                        }
                    }
                }
                P40[1][0][0] = 0;
                return P40;
            case 41:
                int[][][] P41 = new int[2][1][3];
                for(int i = 0; i < P41.length; i++){
                    for(int j = 0; j < P41[i].length; j++){
                        for(int k = 0; k < P41[i][j].length; k++){
                            P41[i][j][k] = 4;
                        }
                    }
                }
                P41[1][0][0] = 0;
                return P41;
            case 42:
                int[][][] P42 = new int[2][3][1];
                for(int i = 0; i < P42.length; i++){
                    for(int j = 0; j < P42[i].length; j++){
                        for(int k = 0; k < P42[i][j].length; k++){
                            P42[i][j][k] = 4;
                        }
                    }
                }
                P42[1][2][0] = 0;
                return P42;
            case 43:
                int[][][] P43 = new int[2][1][3];
                for(int i = 0; i < P43.length; i++){
                    for(int j = 0; j < P43[i].length; j++){
                        for(int k = 0; k < P43[i][j].length; k++){
                            P43[i][j][k] = 4;
                        }
                    }
                }
                P43[1][0][2] = 0;
                return P43;
            case 44:
                int[][][] P44 = new int[2][3][1];
                for(int i = 0; i < P44.length; i++){
                    for(int j = 0; j < P44[i].length; j++){
                        for(int k = 0; k < P44[i][j].length; k++){
                            P44[i][j][k] = 4;
                        }
                    }
                }
                P44[0][0][0] = 0;
                return P44;
            case 45:
                int[][][] P45 = new int[2][1][3];
                for(int i = 0; i < P45.length; i++){
                    for(int j = 0; j < P45[i].length; j++){
                        for(int k = 0; k < P45[i][j].length; k++){
                            P45[i][j][k] = 4;
                        }
                    }
                }
                P45[0][0][0] = 0;
                return P45;
            case 46:
                int[][][] P46 = new int[2][3][1];
                for(int i = 0; i < P46.length; i++){
                    for(int j = 0; j < P46[i].length; j++){
                        for(int k = 0; k < P46[i][j].length; k++){
                            P46[i][j][k] = 4;
                        }
                    }
                }
                P46[0][2][0] = 0;
                return P46;
            case 47:
                int[][][] P47 = new int[2][1][3];
                for(int i = 0; i < P47.length; i++){
                    for(int j = 0; j < P47[i].length; j++){
                        for(int k = 0; k < P47[i][j].length; k++){
                            P47[i][j][k] = 4;
                        }
                    }
                }
                P47[0][0][2] = 0;
                return P47;
            case 48:
                int[][][] P48 = new int[3][2][1];
                for(int i = 0; i < P48.length; i++){
                    for(int j = 0; j < P48[i].length; j++){
                        for(int k = 0; k < P48[i][j].length; k++){
                            P48[i][j][k] = 4;
                        }
                    }
                }
                P48[2][0][0] = 0;
                return P48;
            case 49:
                int[][][] P49 = new int[3][1][2];
                for(int i = 0; i < P49.length; i++){
                    for(int j = 0; j < P49[i].length; j++){
                        for(int k = 0; k < P49[i][j].length; k++){
                            P49[i][j][k] = 4;
                        }
                    }
                }
                P49[2][0][0] = 0;
                return P49;
            case 50:
                int[][][] P50 = new int[3][2][1];
                for(int i = 0; i < P50.length; i++){
                    for(int j = 0; j < P50[i].length; j++){
                        for(int k = 0; k < P50[i][j].length; k++){
                            P50[i][j][k] = 4;
                        }
                    }
                }
                P50[2][1][0] = 0;
                return P50;
            case 51:
                int[][][] P51 = new int[3][1][2];
                for(int i = 0; i < P51.length; i++){
                    for(int j = 0; j < P51[i].length; j++){
                        for(int k = 0; k < P51[i][j].length; k++){
                            P51[i][j][k] = 4;
                        }
                    }
                }
                P51[2][0][1] = 0;
                return P51;
            case 52:
                int[][][] P52 = new int[3][2][1];
                for(int i = 0; i < P52.length; i++){
                    for(int j = 0; j < P52[i].length; j++){
                        for(int k = 0; k < P52[i][j].length; k++){
                            P52[i][j][k] = 4;
                        }
                    }
                }
                P52[0][1][0] = 0;
                return P52;
            case 53:
                int[][][] P53 = new int[3][1][2];
                for(int i = 0; i < P53.length; i++){
                    for(int j = 0; j < P53[i].length; j++){
                        for(int k = 0; k < P53[i][j].length; k++){
                            P53[i][j][k] = 4;
                        }
                    }
                }
                P53[0][0][1] = 0;
                return P53;
            case 54:
                int[][][] P54 = new int[3][2][1];
                for(int i = 0; i < P54.length; i++){
                    for(int j = 0; j < P54[i].length; j++){
                        for(int k = 0; k < P54[i][j].length; k++){
                            P54[i][j][k] = 4;
                        }
                    }
                }
                P54[0][0][0] = 0;
                return P54;
            case 55:
                int[][][] P55 = new int[3][1][2];
                for(int i = 0; i < P55.length; i++){
                    for(int j = 0; j < P55[i].length; j++){
                        for(int k = 0; k < P55[i][j].length; k++){
                            P55[i][j][k] = 4;
                        }
                    }
                }
                P55[0][0][0] = 0;
                return P55;
            default:
                return null;
        }
    }
    public static int[][][] generatecubes(char c){
        switch (c) {
            case 'A':
                int[][][] A = new int[2][2][4];
                for (int i = 0; i < A.length; i++) {
                    for (int j = 0; j < A.length; j++) {
                        Arrays.fill(A[i][j], 1);
                    }
                }
                return A;
            case 'B':
                int[][][] B = new int[2][3][4];
                for (int i = 0; i < B.length; i++) {
                    for (int j = 0; j < B.length; j++) {
                        Arrays.fill(B[i][j], 2);
                    }
                }
                return B;
            case 'C':
                int[][][] C = new int[3][3][3];
                for (int i = 0; i < C.length; i++) {
                    for (int j = 0; j < C.length; j++) {
                        Arrays.fill(C[i][j], 3);
                    }
                }
                return C;
            default:
                return null;
        }
    }
    public static void config() {
        char[] loopthrough = { 'A', 'B', 'C' };
        int[][][][] end = new int[48][4][4][4];
        int count = 0;
        for (int k = 0; k < 3; k++) {
            int[][][] v = generatecubes(loopthrough[k]);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                   v = pentominoRotateY(v);
                   end[count] = v;
                   count++;
                }
                v = pentominoRotateZ(v);
            }            
        }
        int[][][] o = new int[4][4][4];
        int k = 0;
        for (int i = 0; i < end.length; i++) {
            for (int j = 0; j < end.length; j++) {
                if( i == j) {
                }
                else if(Arrays.deepEquals(end[i], end[j])){
                    end[j] = o;
                }
            }
        }
        for (int i = 0; i < end.length; i++) {
            if (end[i][0][0][0] != 0) {
                result.add(k, end[i]);
                k++;
            }
        }
        System.out.println(result.size());
        try {
            PrintWriter writer = new PrintWriter("output.csv" );
            for (int i = 0; i < end.length; i++) {
                writer.write(Arrays.deepToString(end[i]));
            }
            writer.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    static int[][][] pentominoRotateY(int[][][] Piece){
        int[][][] newPiece = new int[Piece.length][Piece[0][0].length][Piece[0].length];
        for (int i = 0; i < newPiece.length; i++) {
            for (int j = 0; j < newPiece[0].length; j++) {
                for (int j2 = 0; j2 < newPiece[0][0].length; j2++) {
                    newPiece[i][j][j2] = Piece[i][j2][j];
                }
            }
        }
        return newPiece;
    }
    static int[][][] pentominoRotateZ(int[][][] Piece){
        int[][][] newPiece = new int[Piece[0].length][Piece.length][Piece[0][0].length];
        for (int i = 0; i < newPiece.length; i++) {
            for (int j = 0; j < newPiece[0].length; j++) {
                for (int j2 = 0; j2 < newPiece[0][0].length; j2++) {
                    newPiece[i][j][j2] = Piece[j][i][j2];
                }
            }
        }
        return newPiece;
    }
}
