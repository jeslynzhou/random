import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Lostmap {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int villages = io.getInt();
        int[][] distances = new int[villages + 1][villages + 1];
        for (int i = 1; i <= villages; i++) {
            for (int j = 1; j <= villages; j++) {
                distances[i][j] = io.getInt();
            }
        }
        PriorityQueue<Path> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[villages + 1];
        ArrayList<PathPair> mst = new ArrayList<>();
        visited[1] = true; // start with 1
        for (int i = 2; i <= villages; i++) {
            pq.add(new Path(1, i, distances[1][i]));
        }

        while (!pq.isEmpty()) {
            Path curr = pq.poll();
            if (!visited[curr.v2]) {
                visited[curr.v2] = true;
                mst.add(new PathPair(curr.v1, curr.v2));

                for (int i = 1; i <= villages; i++) {
                    if (distances[curr.v2][i] != 0 && !visited[i]) {
                        pq.add(new Path(curr.v2, i, distances[curr.v2][i]));
                    }
                }
            }
        }
        Collections.sort(mst);
        for (PathPair p : mst) {
            io.println(p.v1 + " " + p.v2);
        }
        io.close();
    }
}

class Path implements Comparable<Path> {
    int v1;
    int v2;
    int d;

    Path(int v1, int v2, int d) {
        this.v1 = v1;
        this.v2 = v2;
        this.d = d;
    }

    @Override
    public int compareTo(Path other) {
        return Integer.compare(this.d, other.d);
    }
}

class PathPair implements Comparable<PathPair> {
    int v1;
    int v2;

    PathPair(int v1, int v2) {
        if (v1 > v2) {
            int temp = v1;
            v1 = v2;
            v2 = temp;
        }
        this.v1 = v1;
        this.v2 = v2;
    }

    @Override
    public int compareTo(PathPair other) {
        return Integer.compare(this.v1, other.v1);
    }
}