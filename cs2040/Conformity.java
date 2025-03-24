import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Conformity {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int frosh = io.getInt();
        Courses[] combine = new Courses[frosh];
        for (int i = 0; i < frosh; i++) {
            int[] courses = new int[5];
            for (int j = 0; j < 5; j++) {
                courses[j] = io.getInt();
            }
            Arrays.sort(courses);
            Courses c = new Courses(courses); // store the array of course codes
            combine[i] = c; // put the courses tgt
        }
        HashMap<Courses, Integer> hm = new HashMap<Courses, Integer>();
        for (Courses co : combine) {
            if (hm.get(co) == null) {
                hm.put(co, 1);
            } else {
                int newV = hm.get(co) + 1;
                hm.put(co, newV);
            }
        }

        int max = Collections.max(hm.values());
        int output = 0;
        for (int num : hm.values()) {
            if (num == max) {
                output += num;
            }
        }
        io.println(output);
        io.close();
    }
}

class Courses {
    int[] courses;

    Courses(int[] courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Courses other = (Courses) obj;
        return Arrays.equals(courses, other.courses);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(courses);
    }
}