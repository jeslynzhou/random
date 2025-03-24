import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
take in the bumber of cases
loop through each line to convert the alphabets into numbers
- if the new alphabet added has the same initial number as the last alphabet => add a space btw them
*/

public class T9spelling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Character> alphabets = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            alphabets.add(c); // index: 0-25
        }
        alphabets.add(' '); // add space into the alphabets array
        List<String> t9Sequences = new ArrayList<>(Arrays.asList(
                "2", "22", "222",
                "3", "33", "333",
                "4", "44", "444",
                "5", "55", "555",
                "6", "66", "666",
                "7", "77", "777", "7777",
                "8", "88", "888",
                "9", "99", "999", "9999",
                "0"));
        int casesNum = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < casesNum; i++) {
            String input = sc.nextLine(); // take in the thing u want to convert to numbers
            StringBuilder result = new StringBuilder(); // store the result
            for (char d : input.toCharArray()) {
                int index = alphabets.indexOf(d);
                String newC = t9Sequences.get(index);
                if (!result.isEmpty() && newC.charAt(0) == result.charAt(result.length() - 1)) {
                    result.append(" ");
                }
                result.append(newC);
            }
            System.out.printf("Case #%d: %s%n", i + 1, result.toString());
        }
        sc.close();
    }
}