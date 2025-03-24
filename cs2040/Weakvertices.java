import java.util.ArrayList;

public class Weakvertices {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        while (true) {
            int n = io.getInt();
            if (n == -1) {
                break;
            }
            int[][] adjmatrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    adjmatrix[i][j] = io.getInt();
                }
            } // take in the matrix
            ArrayList<Integer> wv = new ArrayList<>();
            for (int i = 0; i < n; i++) { // check if i is part of triangle
                boolean isTriangle = false; // initialize with false
                for (int j = 0; j < n; j++) {
                    if (adjmatrix[i][j] == 1) { // means i is connected to j
                        for (int k = j + 1; k < n; k++) { // check the elements after j
                            if (adjmatrix[j][k] == 1 && adjmatrix[i][k] == 1) { // means j is connected to k and i is
                                                                                // connected to k => i is not a wv
                                isTriangle = true;
                                break; // i is part of triangle => dont need to loop and check for the rest
                            }

                        }
                    }
                    if (isTriangle) { // dont need to check for the rest
                        break;
                    }
                }
                if (!isTriangle) { // not triangle
                    wv.add(i); // add to the arraylist
                }
            }
            if (!wv.isEmpty()) {
                for (int vertex : wv) {
                    io.print(vertex + " ");
                }
                io.print("\n");
            }
        }
        io.close();
    }
}