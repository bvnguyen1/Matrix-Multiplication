import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;
// important: check on the function call of Strassen in the main method

// Program will execute the same matrices for each size 20 times and generate the average.
// Rerun the program for different matrices.
// The runtime of matrices multiplications functions will be timed in Nanosecond.
public class Main {
    static final int TOTAL_REPEAT = 20;
    static final int TOTAL_SETS = 500;
    public static void main(String[] args) {
        String data = "Size,Traditional,Divide & Conquer, Strassen" + "\n";

        // to keep track of the average
        long classicAve = 0;
        long conquerAve = 0;
        long strassenAve = 0;

        int size = 1;
        int n = 1;
        while (n <= TOTAL_SETS) {
            for (int i = 1; i <= 8; i++) {
                // Generates Size
                int exponent = i;
                while (exponent != 0) {
                    size = size * 2;
                    exponent--;
                }
                data = data + Integer.toString(size) + "x" + Integer.toString(size);

                // Generates matrices with size
                int[][] matrix1 = createMatrices(size);
                int[][] matrix2 = createMatrices(size);

                // Classic Multiplication, testing the input 20 times
                for (int j = 0; j < TOTAL_REPEAT; j++) {
                    long startTime = System.nanoTime();
                    Classic(matrix1, matrix2);
                    long endTime = System.nanoTime();       // divide by 1,000,000 to convert to millisecond
                    long duration = (endTime - startTime);  // divide by 1,000,000,000 to convert to second
                    classicAve = classicAve + duration;
                }
                // Calculate and print out the average
                classicAve = classicAve / TOTAL_REPEAT;
                data = data + "," + classicAve;

                // Divide & Conquer Multiplication, testing the input 20 times
                for (int j = 0; j < TOTAL_REPEAT; j++) {
                    long startTime = System.nanoTime();
                    DivideConquer(matrix1, matrix2);
                    long endTime = System.nanoTime();       // divide by 1,000,000 to convert to millisecond
                    long duration = (endTime - startTime);  // divide by 1,000,000,000 to convert to second
                    conquerAve = conquerAve + duration;
                }
                // Calculate and print out the average
                conquerAve = conquerAve / TOTAL_REPEAT;
                data = data + "," + conquerAve;


                // Strassen Multiplication, testing the input 20 times
                for (int j = 0; j < TOTAL_REPEAT; j++) {
                    long startTime = System.nanoTime();
                    Strassen(matrix1.length, matrix1, matrix2, new int[matrix1.length][matrix1.length]);
                    long endTime = System.nanoTime();       // divide by 1,000,000 to convert to millisecond
                    long duration = (endTime - startTime);  // divide by 1,000,000,000 to convert to second

                    strassenAve = strassenAve + duration;
                }
                // Calculate and print out the average
                strassenAve = strassenAve / TOTAL_REPEAT;
                data = data + "," + strassenAve + "\n";

                conquerAve = 0;
                strassenAve = 0;
                classicAve = 0;
                size = 1;

            }
            n++;
            System.out.print(data);
            saveData(data);
            data = "";
        }

    }
    static void saveData(String data) {
            try {
                FileWriter fw = new FileWriter("data1.cvs", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
                pw.print(data);
                pw.flush();
                pw.close();
                System.out.println("Record saved.");
            }catch (Exception E){
                System.out.println("Record not saved.");
            }
    }
    // This function will generates random matrix of size nxn.
    static int[][] createMatrices(int size){
        Random random = new Random();
        int randInt = 0;
        int[][] matrix = new int[size][size];

        for(int i = 0; i < size; i++) {         //rows
            for(int j = 0; j < size; j++) {            // columns
                randInt = random.nextInt(10);  // 0 - 99
                matrix[i][j] = randInt;
            }
        }
        return matrix;
    }

    // This function will use Classic methodology to multiply two matrices.
    static void Classic(int[][] mtx1, int[][] mtx2){
        int[][] answer = new int[mtx1.length][mtx1.length];
        int sum = 0;
        for (int i = 0; i < mtx1.length; i++){
            for (int j = 0; j < mtx2.length; j++){
                for (int k = 0; k < mtx2.length; k++){
                    int product = mtx1[i][k] * mtx2[k][j];
                    sum = sum + product;
                }
                answer[i][j] = sum;
                sum = 0;
            }
        }
    }

    // This function will use Divide & Conquer methodology to multiply two matrices.
    static int[][] DivideConquer(int[][] mtxA, int[][] mtxB){
        int n = mtxA.length;
        int[][] answer = new int[n][n];
        int[][] answer00 = new int[n/2][n/2];
        int[][] answer01 = new int[n/2][n/2];
        int[][] answer10 = new int[n/2][n/2];
        int[][] answer11 = new int[n/2][n/2];

        if (n <= 2) {
            answer[0][0] = (mtxA[0][0]*mtxB[0][0]) + (mtxA[0][1]*mtxB[1][0]);
            answer[0][1] = (mtxA[0][0]*mtxB[0][1]) + (mtxA[0][1]*mtxB[1][1]);
            answer[1][0] = (mtxA[1][0]*mtxB[0][0]) + (mtxA[1][1]*mtxB[1][0]);
            answer[1][1] = (mtxA[1][0]*mtxB[0][1]) + (mtxA[1][1]*mtxB[1][1]);
        }else {
            int[][] mtxA00 = new int[n/2][n/2];
            int[][] mtxB00 = new int[n/2][n/2];
            int[][] mtxA01 = new int[n/2][n/2];
            int[][] mtxB01 = new int[n/2][n/2];
            int[][] mtxA10 = new int[n/2][n/2];
            int[][] mtxB10 = new int[n/2][n/2];
            int[][] mtxA11 = new int[n/2][n/2];
            int[][] mtxB11 = new int[n/2][n/2];

            // Create quarter matrices
            for (int i = 0; i < n/2; i++){
                for (int j = 0; j < n/2; j++) {
                    mtxA00[i][j] = mtxA[i][j];
                    mtxB00[i][j] = mtxB[i][j];
                    mtxA01[i][j] = mtxA[i][n/2+j];
                    mtxB01[i][j] = mtxB[i][n/2+j];
                    mtxA10[i][j] = mtxA[n/2+i][j];
                    mtxB10[i][j] = mtxB[n/2+i][j];
                    mtxA11[i][j] = mtxA[n/2+i][n/2+j];
                    mtxB11[i][j] = mtxB[n/2+i][n/2+j];
                }
            }
            answer00 = addition(DivideConquer(mtxA00, mtxB00), DivideConquer(mtxA01, mtxB10));
            answer01 = addition(DivideConquer(mtxA00, mtxB01), DivideConquer(mtxA01, mtxB11));
            answer10 = addition(DivideConquer(mtxA10, mtxB00), DivideConquer(mtxA11, mtxB10));
            answer11 = addition(DivideConquer(mtxA10, mtxB01), DivideConquer(mtxA11, mtxB11));
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i < n/2 && j < n/2)
                        answer[i][j] = answer00[i][j];
                    else if (i < n/2 && j>= n/2)
                        answer[i][j] = answer01[i][j - n/2];
                    else if (i >= n/2 && j < n/2)
                        answer[i][j] = answer10[i - n/2][j];
                    else
                        answer[i][j] = answer11[i - n/2][j - n/2];
                }
            }
        }
        return answer;
    }

    // This function will use Strassen methodology to multiply two matrices.
    static int[][] Strassen(int n, int[][] mtxA, int[][] mtxB, int[][] mtxC){
        if (n == 2){
            mtxC[0][0] = (mtxA[0][0]*mtxB[0][0]) + (mtxA[0][1]*mtxB[1][0]);
            mtxC[0][1] = (mtxA[0][0]*mtxB[0][1]) + (mtxA[0][1]*mtxB[1][1]);
            mtxC[1][0] = (mtxA[1][0]*mtxB[0][0]) + (mtxA[1][1]*mtxB[1][0]);
            mtxC[1][1] = (mtxA[1][0]*mtxB[0][1]) + (mtxA[1][1]*mtxB[1][1]);
        }else {
            //Partition A and B into 4 sub-matrices
            int[][] mtxA00 = new int[n/2][n/2];
            int[][] mtxB00 = new int[n/2][n/2];
            int[][] mtxA01 = new int[n/2][n/2];
            int[][] mtxB01 = new int[n/2][n/2];
            int[][] mtxA10 = new int[n/2][n/2];
            int[][] mtxB10 = new int[n/2][n/2];
            int[][] mtxA11 = new int[n/2][n/2];
            int[][] mtxB11 = new int[n/2][n/2];
            for (int i = 0; i < n/2; i++){
                for (int j = 0; j < n/2; j++) {
                    mtxA00[i][j] = mtxA[i][j];
                    mtxB00[i][j] = mtxB[i][j];
                    mtxA01[i][j] = mtxA[i][n/2+j];
                    mtxB01[i][j] = mtxB[i][n/2+j];
                    mtxA10[i][j] = mtxA[n/2+i][j];
                    mtxB10[i][j] = mtxB[n/2+i][j];
                    mtxA11[i][j] = mtxA[n/2+i][n/2+j];
                    mtxB11[i][j] = mtxB[n/2+i][n/2+j];
                }
            }
            int[][] P = new int[n/2][n/2];
            Strassen(n/2,addition(mtxA00, mtxA11), addition(mtxB00,mtxB11), P);
            int[][] Q = new int[n/2][n/2];
            Strassen(n/2,addition(mtxA10, mtxA11), mtxB00, Q);
            int[][] R = new int[n/2][n/2];
            Strassen(n/2,mtxA00, subtraction(mtxB01, mtxB11), R);
            int[][] S = new int[n/2][n/2];
            Strassen(n/2,mtxA11, subtraction(mtxB10, mtxB00), S);
            int[][] T = new int[n/2][n/2];
            Strassen(n/2,addition(mtxA00, mtxA01), mtxB11, T);
            int[][] U = new int[n/2][n/2];
            Strassen(n/2,subtraction(mtxA10, mtxA00), addition(mtxB00, mtxB01), U);
            int[][] V = new int[n/2][n/2];
            Strassen(n/2,subtraction(mtxA01, mtxA11), addition(mtxB10, mtxB11), V);

            int[][] C00 = subtraction(addition(P, S), addition(T, V));
            int[][] C01 = addition(R, T);
            int[][] C10 = addition(Q, S);
            int[][] C11 = subtraction(addition(P, R), addition(Q, U));

            // put all the quarter-matrices together
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i < n/2 && j < n/2)
                        mtxC[i][j] = C00[i][j];
                    else if (i < n/2 && j>= n/2)
                        mtxC[i][j] = C01[i][j - n/2];
                    else if (i >= n/2 && j < n/2)
                        mtxC[i][j] = C10[i - n/2][j];
                    else
                        mtxC[i][j] = C11[i - n/2][j - n/2];
                }
            }
        }
        return mtxC;
    }


    // This function will perform matrices addition
    static int[][] addition(int[][] mtxA, int[][] mtxB){
        int n = mtxA.length;
        int[][] answer = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer[i][j] = mtxA[i][j] + mtxB[i][j];
            }
        }
        return answer;
    }

    // This function will perform matrices subtraction
    static int[][] subtraction(int[][] mtxA, int[][] mtxB) {
        int n = mtxA.length;
        int[][] answer = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer[i][j] = mtxA[i][j] - mtxB[i][j];
            }
        }
        return answer;
    }
}
