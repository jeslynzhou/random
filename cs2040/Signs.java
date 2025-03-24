/*
take in the number of signs
sorted based on the middle letter (even: 2; odd: 1)
if same middle letters => same order as given input
*/

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Signs {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();
        List<Word> wordL = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String input = io.getWord();
            int mid = input.length() / 2;
            String middle = "";
            if (input.length() % 2 == 0) {
                middle = input.substring(mid - 1, mid + 1); // 4/2 =2
            } else {
                middle = Character.toString(input.charAt(mid));
            }
            Word word = new Word(input, middle, i);
            wordL.add(word);
        }
        Collections.sort(wordL);
        for (Word w : wordL) {
            io.println(w.word);
        }
        io.close();
    }
}

class Word implements Comparable<Word> {
    String word;
    String middle;
    int order;

    Word(String word, String middle, int order) {
        this.word = word;
        this.middle = middle;
        this.order = order;
    }

    public int compareTo(Word otherW) {
        if (this.middle.compareTo(otherW.middle) == 0) {
            return Integer.compare(this.order, otherW.order);
        } else {
            return this.middle.compareTo(otherW.middle);
        }
    }
}