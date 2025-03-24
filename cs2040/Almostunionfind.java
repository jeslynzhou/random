import java.util.Arrays;

public class Almostunionfind {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        while (io.hasMoreTokens()) {
            int n = io.getInt();
            int m = io.getInt();
            UnionFind uf = new UnionFind(n);
            for (int i = 0; i < m; i++) {
                int command = io.getInt();
                if (command == 1) {
                    int f = io.getInt();
                    int s = io.getInt();
                    uf.unionSet(f, s);
                    /*
                     * io.println("p: " + Arrays.toString(uf.p));
                     * io.println("dp: " + Arrays.toString(uf.directp));
                     * io.println("count: " + Arrays.toString(uf.count));
                     * io.println("sum: " + Arrays.toString(uf.sum));
                     */
                } else if (command == 2) {
                    int f = io.getInt();
                    int s = io.getInt();
                    uf.moveSet(f, s);
                    /*
                     * io.println("p: " + Arrays.toString(uf.p));
                     * io.println("dp: " + Arrays.toString(uf.directp));
                     * io.println("count: " + Arrays.toString(uf.count));
                     * io.println("sum: " + Arrays.toString(uf.sum));
                     */
                } else {
                    int output = io.getInt();
                    int c = uf.count[uf.findSet(output)];
                    long s = uf.sum[uf.findSet(output)];
                    io.println(c + " " + s);
                }
            }
        }
        io.close();
    }
}

class UnionFind {
    public int[] p;
    public int[] rank;
    public int[] directp;
    public long[] sum;
    public int[] count;

    public UnionFind(int N) {
        p = new int[N + 1];
        rank = new int[N + 1];
        sum = new long[N + 1];
        count = new int[N + 1];
        directp = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            p[i] = i; // store the parent of i
            rank[i] = 0; // rank is 0
            sum[i] = i; // sum is the integer itself
            count[i] = 1; // count is 1
            directp[i] = i; // directp is i
        }
    }

    public int findSet(int i) { // getting the actual key using second p array
        int actualSet = p[i];
        if (directp[actualSet] == actualSet) {
            return actualSet;
        } else {
            return findInDP(directp[actualSet]);
        }
    }

    public int findInDP(int i) {
        int root = directp[i];
        if (root == i) {
            return root;
        } else {
            return findInDP(root);
        }
    }

    public Boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
    }

    public void unionSet(int i, int j) { // command 1
        if (!isSameSet(i, j)) {
            int x = findSet(i), y = findSet(j); // x and y are the parents of i and j
            // rank is used to keep the tree short
            if (rank[x] > rank[y]) { // move y to x
                p[j] = x; // set the parent of j to be x - not necessary: can remove
                sum[x] += sum[y];// update the sum of x
                count[x] += count[y]; // update count
                directp[y] = x; // update the dp of y to be x
            } else { // move x to y
                p[i] = y; // set the parent of i to be x - not necessary: can remove
                sum[y] += sum[x];// update the sum of y
                count[y] += count[x]; // update count
                directp[x] = y; // update the dp of x to be y
                if (rank[x] == rank[y]) {
                    rank[y] = rank[y] + 1;
                }
            }
        }
    }

    public void moveSet(int i, int j) { // command 2
        if (!isSameSet(i, j)) {
            int x = findSet(i), y = findSet(j); // x and y are the parents of i and j
            p[i] = y;
            count[x]--;
            count[y]++;
            sum[x] -= i;
            sum[y] += i;
        }
    }
}