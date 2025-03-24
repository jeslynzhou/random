
// Zhou Jingchu Jeslyn A0275993H
import java.util.Scanner;

public class Peasoup {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Take in the number of restaurants
        int n = scanner.nextInt();
        boolean found = false;

        // Loop through each restaurant to input the menu
        for (int i = 0; i < n; i++) {
            // Read the number of items in the menu
            int k = scanner.nextInt();
            scanner.nextLine();
            // Read the restaurant name
            String rName = scanner.nextLine();

            boolean hasPeaSoup = false;
            boolean hasPancakes = false;

            // Loop through menu items
            for (int j = 0; j < k; j++) {
                String item = scanner.nextLine();
                if (item.equals("pea soup")) {
                    hasPeaSoup = true;
                }
                if (item.equals("pancakes")) {
                    hasPancakes = true;
                }
            }

            if (hasPeaSoup && hasPancakes) {
                System.out.println(rName);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Anywhere is fine I guess");
        }

        scanner.close();
    }
}
