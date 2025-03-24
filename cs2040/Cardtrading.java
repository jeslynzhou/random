import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/*
T - card types in total
D - deck (a set of cards)
a deck has C combos if C is the largest int that
=> for C different card types, D contains >= 2 cards of that type ()
N - number of cards A has
AIM: 
    EXACTLY K combos -- for K card types, D contains >= 2 cards for each type
    minimize spending
    doesnt care about cards that do not contritbue

Transactions: 1. buy up to 2 cards of ith type at ai each; 2. sell all his cards of ith type for bi coins each

Input:
N T K
N cards A has
next T lines: 2 integers each line
a1 b1
a2 b2
...
aT bT

*/

public class Cardtrading {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int t = sc.nextInt();
        int k = sc.nextInt();
        List<Integer> existingCards = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            existingCards.add(0);
        }

        // Cards that A has
        for (int i = 0; i < n; i++) {
            int card = sc.nextInt() - 1;
            int currentCount = existingCards.get(card);
            existingCards.set(card, currentCount + 1);
        }

        // load the buy sell price
        List<BuySellPrice> buySellPrices = new ArrayList<>();
        for (int j = 1; j < t + 1; j++) {
            long bp = sc.nextInt();
            long sp = sc.nextInt();
            buySellPrices.add(new BuySellPrice(j, bp, sp));
        }

        sc.close();

        // consider how many extra cards i need to make it k combos and the oc
        List<OC> oCForEachType = new ArrayList<>();
        for (BuySellPrice bsp : buySellPrices) {
            int existingN = existingCards.get(bsp.type - 1);
            int extraRequired = 2 - existingN;
            long cost = 0;
            long profit = 0;
            cost += bsp.buy * extraRequired;
            profit += bsp.sell * existingN;
            long opportunityC = profit + cost;
            OC oc = new OC(bsp.type, opportunityC, profit, cost);
            oCForEachType.add(oc);
        }

        // keep the k card types with the smallest oc and sell the rest
        Collections.sort(oCForEachType);
        // keep the costs for these card types and add the profits of the rest
        long totalC = 0;
        for (int m = 0; m < k; m++) {
            totalC += oCForEachType.get(m).cost;
        }

        long totalP = 0;
        for (int p = k; p < oCForEachType.size(); p++) {
            totalP += oCForEachType.get(p).profit;
        }

        long optimizedP = totalP - totalC;
        System.out.println(optimizedP);
    }

}

class BuySellPrice {
    int type;
    long buy;
    long sell;

    BuySellPrice(int type, long buy, long sell) {
        this.type = type;
        this.buy = buy;
        this.sell = sell;
    }
}

class OC implements Comparable<OC> {
    int type;
    long oc;
    long profit;
    long cost;

    OC(int type, long oc, long profit, long cost) {
        this.type = type;
        this.oc = oc;
        this.profit = profit;
        this.cost = cost;
    }

    public int compareTo(OC other) {
        return Long.compare(this.oc, other.oc);
    }
}