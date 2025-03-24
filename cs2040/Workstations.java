import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Workstations {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();
        int m = io.getInt();
        Pair<Integer, Integer>[] arrayR = new Pair[n]; // sort researchers based on arrival time
        PriorityQueue<Integer> pqWS = new PriorityQueue<>(); // store the locktimes for each WS
        for (int i = 0; i < n; i++) {
            int arrT = io.getInt();
            int stayT = io.getInt();
            Pair<Integer, Integer> r = new Pair<>(arrT, arrT + stayT + m);
            arrayR[i] = r;
        }
        Arrays.sort(arrayR, Comparator.comparingInt(pair -> pair.first()));
        int saved = 0;
        for (Pair<Integer, Integer> currentR : arrayR) {
            while (!pqWS.isEmpty() && pqWS.peek() < currentR.first()) {
                pqWS.poll();
            }
            if (!pqWS.isEmpty() && currentR.first() >= (pqWS.peek() - m)) {
                saved++;
                pqWS.poll();
            }
            pqWS.add(currentR.second());
        }
        io.println(saved);
        io.close();
    }
}
