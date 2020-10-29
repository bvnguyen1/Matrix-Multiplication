public class DivideAndConquer {
    public static void main(String[] args) {
        System.out.println("---This is Divide & Conquer Method of Matrices Multiplication---");


//        int[][] mtx1 = new int[][]{
//                {1,1,1,0},
//                {1,1,1,0},
//                {1,1,1,0},
//                {0,0,0,0}
//        };
//        int[][] mtx2 = new int[][]{
//                {1,1,1,0},
//                {1,1,1,0},
//                {1,1,1,0},
//                {0,0,0,0}
//        };


        int[][] mtx1 = new int[][]{
                {1,2,3,4,5,6,7,8},
                {1,2,3,4,5,6,7,8},
                {1,2,3,4,5,6,7,8},
                {1,2,3,4,5,6,7,8},
                {1,2,3,4,5,6,7,8},
                {1,2,3,4,5,6,7,8},
                {1,2,3,4,5,6,7,8},
                {1,2,3,4,5,6,7,8}
        };
        int[][] mtx2 = new int[][]{
                {1,2,3,4,5,6,7,8},
                {1,2,3,4,5,6,7,8},
                {1,2,3,4,5,6,7,8},
                {1,2,3,4,5,6,7,8},
                {1,2,3,4,5,6,7,8},
                {1,2,3,4,5,6,7,8},
                {1,2,3,4,5,6,7,8},
                {1,2,3,4,5,6,7,8}
        };
        int[][] answer = multiply(mtx1, mtx2);
        printAnswer(answer);
    }

    // This function will use Divide & Conquer methodology to multiply two matrices.
    static int[][] multiply(int[][] mtxA, int[][] mtxB){
        int n = mtxA.length;
        System.out.println(n);
        int[][] answer = new int[n][n];
        int[][] answer00 = new int[n/2][n/2];
        int[][] answer01 = new int[n/2][n/2];
        int[][] answer10 = new int[n/2][n/2];
        int[][] answer11 = new int[n/2][n/2];

        if (n <= 2) {
            System.out.println("in if");
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
            answer00 = addition(multiply(mtxA00, mtxB00), multiply(mtxA01, mtxB10));
            answer01 = addition(multiply(mtxA00, mtxB01), multiply(mtxA01, mtxB11));
            answer10 = addition(multiply(mtxA10, mtxB00), multiply(mtxA11, mtxB10));
            answer11 = addition(multiply(mtxA10, mtxB01), multiply(mtxA11, mtxB11));
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


