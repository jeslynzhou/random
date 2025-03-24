import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/*
take in the runners
assume each runner is the 1st one and pick the rest of the top 4 runners and calculate the time required time
loop through each runner and keep track of the best performance
*/

public class Bestrelayteam {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalNum = sc.nextInt();
        sc.nextLine();

        List<String> runners = new ArrayList<>();
        List<Double> time1 = new ArrayList<>();
        List<Double> time2 = new ArrayList<>();

        for (int i = 0; i < totalNum; i++) {
            String[] input = sc.nextLine().split(" ");
            runners.add(input[0]);
            time1.add(Double.parseDouble(input[1]));
            time2.add(Double.parseDouble(input[2]));
        }

        double bestR = Double.MAX_VALUE;
        List<String> runnerCombination = new ArrayList<>();
        // assume each runner is the 1st one
        for (int j = 0; j < totalNum; j++) {
            double record = time1.get(j);
            List<String> combination = new ArrayList<>();
            combination.add(runners.get(j));

            List<Double> sorted2 = new ArrayList<>(time2);
            List<String> sortedRunners = new ArrayList<>(runners);
            sorted2.remove(j);
            sortedRunners.remove(j);

            List<Runner> sortedRunnerAndTime2 = new ArrayList<>();
            for (int k = 0; k < sorted2.size(); k++) {
                sortedRunnerAndTime2.add(new Runner(sortedRunners.get(k), sorted2.get(k)));
            }

            Collections.sort(sortedRunnerAndTime2);

            for (int k = 0; k < 3; k++) {
                record += sortedRunnerAndTime2.get(k).time;
                combination.add(sortedRunnerAndTime2.get(k).runner);
            }

            if (record < bestR) {
                bestR = record;
                runnerCombination = combination;
            }
        }

        sc.close();

        System.out.println(bestR);
        for (String s : runnerCombination) {
            System.out.println(s);
        }
    }
}

class Runner implements Comparable<Runner> {
    String runner;
    double time;

    Runner(String runner, double time) {
        this.runner = runner;
        this.time = time;
    }

    public int compareTo(Runner other) {
        return Double.compare(this.time, other.time);
    }
}
