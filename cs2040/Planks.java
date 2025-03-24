import java.util.TreeMap;

public class Planks {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        TreeMap<Plank, Integer> tMap = new TreeMap<>();
        int q = io.getInt();
        for (int i = 0; i < q; i++) {
            String op = io.getWord();
            if (op.equals("a")) {
                int wp = io.getInt();
                long lp = io.getInt();
                Plank p = new Plank(wp, lp);
                if (tMap.get(p) == null) {
                    tMap.put(p, 1);
                } else {
                    int occ = tMap.get(p);
                    tMap.put(p, occ + 1);
                }
            } else {
                long x = io.getInt();
                Plank a = tMap.lowerKey(new Plank(0, x));
                int newOccA = tMap.get(a) - 1;
                if (newOccA == 0) {
                    tMap.remove(a);
                } else {
                    tMap.put(a, newOccA);
                }
                Plank b = tMap.higherKey(new Plank(Integer.MAX_VALUE, x));
                int newOccB = tMap.get(b) - 1;
                if (newOccB == 0) {
                    tMap.remove(b);
                } else {
                    tMap.put(b, newOccB);
                }
                long e = (1 + a.w + b.w) * (1 + Math.abs(a.l - b.l));
                io.println(e);
            }
        }
        io.close();
    }

}

class Plank implements Comparable<Plank> {
    int w;
    long l;

    Plank(int w, long l) {
        this.w = w;
        this.l = l;
    }

    @Override
    public int compareTo(Plank o) {
        if (this.l == o.l) {
            return Integer.compare(o.w, this.w);
        }
        return Long.compare(this.l, o.l);
    }

    @Override
    public String toString() {
        return this.w + " " + this.l;
    }
}