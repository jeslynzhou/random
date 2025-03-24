/*
 * Zhou Jingchu Jeslyn A0275993H
 */

import java.util.ArrayList;
import java.util.LinkedList;

public class Coconut {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int s = io.getInt(); // # of syllables
        int n = io.getInt(); // # of players

        LinkedList<Hand> handsCll = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            handsCll.addLast(new Hand(i + 1, 4));
        }

        while (handsCll.size() > 1) { // check if playerN > 1
            for (int i = 0; i < s - 1; i++) {
                Hand hand = handsCll.removeFirst();
                handsCll.addLast(hand);
            }
            Hand curHand = handsCll.getFirst();
            if (curHand.status == 4) {
                curHand.setStatus(3); // cracked
                handsCll.addFirst(new Hand(curHand.id, 3));
            } else if (curHand.status == 3) {
                curHand.setStatus(2); // down
                handsCll.remove(curHand);
                handsCll.addLast(curHand);
            } else if (curHand.status == 2) { // behind
                curHand.setStatus(1);
                handsCll.remove(curHand);
            }
        }
        io.println(handsCll.getFirst().id);
        io.close();
    }
}

class Hand {
    int id;
    int status;

    Hand(int id, int status) {
        this.id = id;
        this.status = status;
    }

    void setStatus(int status) {
        this.status = status;
    }
}