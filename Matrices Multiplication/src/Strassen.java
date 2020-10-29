public class Strassen {
    public static void main(String[] args) {
        System.out.println("---This is Strassen Method of Matrices Multiplication---");
        // Testing input
        int[][] mtx1 = new int[][]{
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8}
        };
        int[][] mtx2 = new int[][]{
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8}
        };
        int n = mtx1.length;
        int[][] answer = new int[n][n];
        answer = Strassen(n, mtx1, mtx2, answer);
        printAnswer(answer);
    }

    // This function will perform Strassen Method of Matrices Multiplication
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

    // This method will print out the answer
    static void printAnswer(int[][] answer) {
        int n = answer.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("|");
                System.out.print(answer[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println();
    }

}
