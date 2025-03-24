public class Longwait2 {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int q = io.getInt(); // num of operations
        int k = io.getInt(); // k for members
        int array1[] = new int[q * 2];
        int array2[] = new int[q * 2];
        int start1 = q;
        int end1 = q - 1;
        int start2 = q;
        int end2 = q - 1;
        for (int i = 0; i < q; i++) {
            String f = io.getWord();
            if (f.equals("faster")) {
                k--;
                if (end1 - start1 + 1 > k) { // number of elements in array1 > k
                    // remove the last element of array 1
                    int last1 = array1[end1];
                    array1[end1] = 0;
                    end1--;
                    // and add front to array 2
                    start2--;
                    array2[start2] = last1;
                }

            } else if (f.equals("slower")) {
                k++;
                if (end2 - start2 >= 0) { // if there are elements in array 2
                    // remove the first element in array 2
                    int first2 = array2[start2];
                    array2[start2] = 0;
                    start2++;
                    // add back the first element in array 1
                    end1++;
                    array1[end1] = first2;
                }

            } else if (f.equals("findID")) {
                int pos = io.getInt();
                if (pos - 1 <= end1 - start1) {
                    io.println(array1[start1 + pos - 1]);
                } else {
                    io.println(array2[start2 + (pos - (end1 - start1 + 1)) - 1]);
                }
            } else {
                int id = io.getInt();
                if (f.equals("queue")) {
                    if (end1 - start1 + 1 < k && end2 - start2 < 0) { // if fewer than k e in array 1 and no element in
                                                                      // array 2, add back to array 1
                        end1++;
                        array1[end1] = id;
                    } else { // else add back to array 2
                        end2++;
                        array2[end2] = id;
                    }
                } else if (f.equals("member")) {
                    if (end1 - start1 + 1 < k && end2 - start2 < 0) {// if array 1 fewer than k members, and array 2 has
                                                                     // zero members
                        // add Back to array 1
                        end1++;
                        array1[end1] = id;
                    } else if (end1 - start1 + 1 >= k) { // if array 1 has k or more than k(?) addFront to array2
                        start2--;
                        array2[start2] = id;
                    }
                } else { // if vip, add front to array 1
                    start1--;
                    array1[start1] = id;
                    if (end1 - start1 + 1 > k) {
                        // remove the last element and add front to array 2
                        int last1 = array1[end1];
                        array1[end1] = 0;
                        end1--;
                        start2--;
                        array2[start2] = last1;
                    }
                }

            }
        }
        io.close();
    }
}
