public class Islands {
    public static int row, col;

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        row = io.getInt();
        col = io.getInt();
        String[][] matrix = new String[row][col];
        // take in the matrix
        for (int i = 0; i < row; i++) {
            String line = io.getWord();
            String[] eachLetter = line.split(""); // split the word
            for (int j = 0; j < col; j++) {
                matrix[i][j] = eachLetter[j];
            }
        }
        // if there is L, then i need to change the surrounding Cs into Ls
        for (int a = 0; a < row; a++) {
            for (int b = 0; b < col; b++) {
                if (matrix[a][b].equals("L")) {
                    cToL(a, b, matrix);
                }
            }
        }

        // if there is a L, numIsland++
        // perform linkLands on L, and mark the land processed as M (lToM)
        // for loop so every L can be processed
        int numIsland = 0;
        for (int c = 0; c < row; c++) {
            for (int d = 0; d < col; d++) {
                if (matrix[c][d].equals("L")) {
                    numIsland++;
                    lToM(c, d, matrix);
                }
            }
        }

        io.println(numIsland);
        io.close();
    }

    public static void cToL(int a, int b, String[][] matrix) {
        matrix[a][b] = "L";
        if (a - 1 >= 0 && matrix[a - 1][b].equals("C")) { // up
            cToL(a - 1, b, matrix);
        }
        if (a + 1 < row && matrix[a + 1][b].equals("C")) { // down
            cToL(a + 1, b, matrix);
        }
        if (b - 1 >= 0 && matrix[a][b - 1].equals("C")) { // left
            cToL(a, b - 1, matrix);
        }
        if (b + 1 < col && matrix[a][b + 1].equals("C")) { // right
            cToL(a, b + 1, matrix);
        }
    }

    public static void lToM(int c, int d, String[][] matrix) {
        matrix[c][d] = "M";
        if (c - 1 >= 0 && matrix[c - 1][d].equals("L")) { // up
            lToM(c - 1, d, matrix);
        }
        if (c + 1 < row && matrix[c + 1][d].equals("L")) { // down
            lToM(c + 1, d, matrix);
        }
        if (d - 1 >= 0 && matrix[c][d - 1].equals("L")) { // left
            lToM(c, d - 1, matrix);
        }
        if (d + 1 < col && matrix[c][d + 1].equals("L")) { // right
            lToM(c, d + 1, matrix);
        }
    }
}