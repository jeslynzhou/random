import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Humancannonball {
    public static int cannonNum;

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        Location start = new Location(io.getDouble(), io.getDouble());
        Location destination = new Location(io.getDouble(), io.getDouble());

        cannonNum = io.getInt();
        Location[] locations = new Location[cannonNum + 2];
        locations[0] = start;
        locations[cannonNum + 1] = destination;
        for (int i = 1; i < cannonNum + 1; i++) {
            Location cannon = new Location(io.getDouble(), io.getDouble());
            locations[i] = cannon;
        }

        // for each location, store the outgoing edges
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < cannonNum + 2; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < cannonNum + 2; i++) { // from
            for (int j = 0; j < cannonNum + 2; j++) { // to
                if (i != j) {
                    double running = locations[i].shortestD(locations[j]) / 5.0;
                    graph.get(i).add(new Edge(i, j, running));

                    if (i > 0 && i <= cannonNum) { // if the starting point is a cannon
                        double launch = Math.abs(locations[i].shortestD(locations[j]) - 50) / 5.0 + 2;
                        graph.get(i).add(new Edge(i, j, launch));
                    }
                }
            }
        }
        double output = dijkstra(graph, cannonNum + 2, 0, cannonNum + 1);
        io.println(output);
        io.close();
    }

    static double dijkstra(List<List<Edge>> graph, int totalLocationsNum, int start, int destination) {
        double[] minTime = new double[totalLocationsNum];
        Arrays.fill(minTime, Double.MAX_VALUE);
        minTime[start] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(start, 0));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int currLoc = current.loc;

            if (current.time > minTime[currLoc]) { // dont need to update
                continue;
            }

            // else
            for (Edge edge : graph.get(currLoc)) {
                int nextLoc = edge.to; // get the next location
                double newTime = minTime[currLoc] + edge.time; // time taken to next location

                if (newTime < minTime[nextLoc]) { // update min
                    minTime[nextLoc] = newTime;
                    pq.add(new Pair(nextLoc, newTime));
                }
            }
        }

        return minTime[destination];
    }
}

class Location {
    double x;
    double y;

    Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double shortestD(Location other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
}

class Edge {
    int from;
    int to;
    double time;

    Edge(int from, int to, double time) {
        this.from = from;
        this.to = to;
        this.time = time;
    }
}

class Pair implements Comparable<Pair> {
    int loc;
    double time;

    Pair(int loc, double time) {
        this.loc = loc;
        this.time = time;
    }

    public int compareTo(Pair other) {
        return Double.compare(this.time, other.time);
    }
}