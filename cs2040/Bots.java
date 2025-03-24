import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Bots {
    public static int n, m;
    public static List<Integer>[] g;
    public static List<Integer>[] gPrime;
    public static int[] visited;
    public static Stack<Integer> stackK;
    public static List<List<Integer>> sccList;

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        n = io.getInt();
        m = io.getInt();

        g = new ArrayList[n];
        gPrime = new ArrayList[n];
        visited = new int[n];
        stackK = new Stack<>();
        sccList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
            gPrime[i] = new ArrayList<>();
            visited[i] = 0;
        }

        for (int j = 0; j < m; j++) {
            int from = io.getInt();
            g[from].add(io.getInt());
        } // g

        // transpose g
        for (int a = 0; a < n; a++) { // a here is the one that should be added into the arraylist
            for (int from : g[a]) {
                gPrime[from].add(a);
            }
        }

        for (int c = 0; c < n; c++) {
            if (visited[c] == 0) { // not visited
                DFS(c);
            }
        }

        visited = new int[n];
        while (!stackK.isEmpty()) {
            int v = stackK.pop();
            List<Integer> currentSCC = new ArrayList<>(); // for storing elements in this SCC
            if (visited[v] == 0) {
                DFSrec(v, currentSCC); // update currentSCC while DFS
                sccList.add(currentSCC); // add currentSCC to the sccList
            }
        }

        int numBotNet = 0;
        int numSolobot = 0;
        boolean[] hasIncoming = new boolean[sccList.size()]; // see if someone can send message to this scc

        // check if each scc has someone not from its botnet taht can send message to
        // this scc
        for (int sccID = 0; sccID < sccList.size(); sccID++) {
            List<Integer> currentSCC = sccList.get(sccID);
            Set<Integer> currentSCCSet = new HashSet<>(currentSCC);
            hasIncoming[sccID] = false;

            for (int bot : currentSCC) {
                for (int incoming : gPrime[bot]) {
                    if (!currentSCCSet.contains(incoming)) {
                        hasIncoming[sccID] = true;
                        break;
                    }
                }
                if (hasIncoming[sccID]) {
                    break;
                }
            }
        }

        for (int i = 0; i < sccList.size(); i++) {
            if (!hasIncoming[i]) { // no incoming edges for this SCC
                if (sccList.get(i).size() == 1) {
                    numSolobot++; // solobot
                } else {
                    numBotNet++; // botnet
                }
            }
        }

        io.println(numSolobot + " " + numBotNet);
        io.close();
    }

    public static void DFS(int c) {
        visited[c] = 1;
        for (int neighbour : g[c]) {
            if (visited[neighbour] == 0) {
                DFS(neighbour);
            }
        }
        stackK.push(c); // only push to stack after exploring the neighbours
    }

    public static void DFSrec(int v, List<Integer> currentSCC) {
        visited[v] = 1;
        currentSCC.add(v); // add to current SCC list
        for (int neighbour : gPrime[v]) {
            if (visited[neighbour] == 0) {
                DFSrec(neighbour, currentSCC);
            }
        }
    }
}
