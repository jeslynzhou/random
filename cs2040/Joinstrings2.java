public class Joinstrings2 {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int wordN = io.getInt();
        if (wordN == 1) {
            io.println(io.getWord());
        } else {
            TailedLinkedList[] tll = new TailedLinkedList[wordN];
            // add each linked list with single node into the array
            for (int i = 0; i < wordN; i++) {
                String str = io.getWord();
                tll[i] = new TailedLinkedList();
                tll[i].insert(new ListNode(str));
            }
            // take in the operations
            for (int j = 0; j < wordN - 1; j++) {
                int f = io.getInt();
                int s = io.getInt();
                tll[f - 1].addTLL(tll[s - 1]);
                if (j == wordN - 2) { // last operation
                    io.println(tll[f - 1].toString());
                }
            }
        }
        io.close();
    }
}

class ListNode {
    String str;
    ListNode next;

    public ListNode(String s) {
        this(s, null);
    }

    public ListNode(String s, ListNode n) {
        str = s;
        next = n;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode n) {
        next = n;
    }

    public String getStr() {
        return str;
    }
}

class TailedLinkedList {
    public ListNode head;
    public ListNode tail;
    public int num_nodes;

    public TailedLinkedList() {
        this.head = null;
        this.tail = null;
        this.num_nodes = 0;
    }

    public void insert(ListNode n) { // insert single new node
        if (head == null) {
            head = n;
            tail = n;
        } else {
            tail.setNext(n);
            tail = n;
        }
        num_nodes++;
    }

    public void addTLL(TailedLinkedList tll) {
        this.tail.setNext(tll.head); // kattis cat
        this.tail = tll.tail; // update tail for kattis cat to be cat
        this.num_nodes += tll.num_nodes;
    }

    @Override
    public String toString() {
        ListNode cur = head;
        StringBuilder strB = new StringBuilder();
        for (int i = 1; i < num_nodes; i++) {
            if (i == 1) {
                strB.append(cur.getStr());
            }
            cur = cur.getNext();
            strB.append(cur.getStr());
        }
        return strB.toString();
    }
}
