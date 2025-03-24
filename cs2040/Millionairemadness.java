import java.util.PriorityQueue;

public class Millionairemadness {
    public static int m, n;

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        m = io.getInt();
        n = io.getInt();

        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = io.getInt();
            }
        }

        int minHeight = findMinHeight(matrix);
        io.println(minHeight);
        io.close();
    }

    public static int findMinHeight(int[][] matrix) {
        int minHeight = 0;
        boolean[][] visited = new boolean[m][n];
        Element startingElement = new Element(0, 0, matrix[0][0], 0);
        PriorityQueue<Element> pq = new PriorityQueue<>();
        pq.add(startingElement);
        while (!pq.isEmpty() && !visited[m - 1][n - 1]) {
            Element from = pq.poll();

            if (visited[from.x][from.y]) {
                continue;
            }

            visited[from.x][from.y] = true;
            minHeight = Math.max(minHeight, from.jump);
            if (from.x - 1 >= 0) { // up
                pq.add(new Element(from.x - 1, from.y, matrix[from.x - 1][from.y],
                        matrix[from.x - 1][from.y] - from.h));
            }
            if (from.x + 1 < m) { // down
                pq.add(new Element(from.x + 1, from.y, matrix[from.x + 1][from.y],
                        matrix[from.x + 1][from.y] - from.h));
            }
            if (from.y - 1 >= 0) { // left

                pq.add(new Element(from.x, from.y - 1, matrix[from.x][from.y - 1],
                        matrix[from.x][from.y - 1] - from.h));

            }
            if (from.y + 1 < n) { // right

                pq.add(new Element(from.x, from.y + 1, matrix[from.x][from.y + 1],
                        matrix[from.x][from.y + 1] - from.h));

            }
        }
        return minHeight;
    }

}

class Element implements Comparable<Element> {
    int x, y, h, jump;

    Element(int x, int y, int h, int jump) {
        this.x = x;
        this.y = y;
        this.h = h;
        this.jump = jump;
    }

    public int compareTo(Element other) {
        return Integer.compare(this.jump, other.jump);
    }
}