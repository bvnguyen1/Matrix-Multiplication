public class Traditional {
    public static void main(String[] args){
        System.out.println("---This is Traditional Method of Matrices Multiplication---");
        // Class example
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
        // Testing input
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

        int[][] answer = Classic(mtx1, mtx2);
        printAnswer(answer);
    }

    static int[][] Classic(int[][] mtx1, int[][] mtx2){
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
        return answer;
    }

    // This function print out the answer
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
