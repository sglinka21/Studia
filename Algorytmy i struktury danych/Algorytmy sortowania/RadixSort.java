import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RadixSort {
    public static void radixsort(int[] input,  List<Long> stats) {
        int BASE = 10;
        boolean maxBase = false;
        int tmp = 0, base = 1, compares = 0, moves = 0;

        List<Integer>[] bucket = new ArrayList[BASE];
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new ArrayList<Integer>();
        }

        while (!maxBase) {
            maxBase = true;

            for (Integer i : input) {
                tmp = i / base;
                bucket[tmp % BASE].add(i);
                moves++;
                compares++;
                if (maxBase && tmp > 0) {
                    maxBase = false;
                }
            }

            int a = 0;
            for (int b = 0; b < BASE; b++) {
                for (Integer i : bucket[b]) {
                    moves++;
                    input[a] = i;
                    a++;
                }
                bucket[b].clear();
            }
            base *= BASE;
        }
        stats.set(0, stats.get(0) + compares);
        stats.set(1, stats.get(1) + moves);
    }

    public static void radixsort2(int[] input, List<Long> stats) {
        int BASE = 10;
        boolean maxBase = false;
        int tmp = 0, base = 1, compares = 0, moves = 0;

        List<Integer>[] bucket = new ArrayList[BASE];
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new ArrayList<Integer>();
        }

        while (!maxBase) {
            maxBase = true;

            for (Integer i : input) {
                tmp = i / base;
                System.out.println("Patrzymy na: " + i + " baza " + base + " tymczasowo " + tmp);
                bucket[tmp % BASE].add(i);
                System.out.println("Wrzucamy " + i + " do kosza " + tmp % BASE);
                moves++;
                compares++;
                if (maxBase && tmp > 0) {
                    maxBase = false;
                }
            }

            int a = 0;
            for (int b = 0; b < BASE; b++) {
                for (Integer i : bucket[b]) {
                    System.out.println("Przenosimy " + i + " z kosza " + b);
                    moves++;
                    input[a] = i;
                    a++;
                }
                bucket[b].clear();
            }
            base *= BASE;
        }
        stats.set(0, stats.get(0) + compares);
        stats.set(1, stats.get(1) + moves);
        System.out.println("Najwieksza liczba jest mniejsza od " + base/BASE + " wiec konczymy");
        System.out.println("Liczba porownan: " + compares + " liczba przestawien " + moves);
    }


    public static void main(String[] args) {

        for(int size=100; size<=100000; size=size+100) {
            int option = Integer.parseInt(args[0]);
            //int size = Integer.parseInt(args[1]);


            Random rn = new Random();
            int random;

            int[] radixSort = new int[size];
            List<Long> stats = new ArrayList<Long>();
            stats.add((long) 0);
            stats.add((long) 0);

            int repeat = 10;

            for (int l = 0; l < repeat; l++) {
                if (option == 0) {
                    for (int i = 0; i < size; i++) {
                        radixSort[i] = i;

                    }
                } else if (option == 1) {
                    for (int i = 0; i < size; i++) {
                        random = rn.nextInt(100000);
                        radixSort[i] = random;

                    }
                } else {
                    for (int i = 0; i < size; i++) {
                        radixSort[i] = size - i;

                    }
                }
                //System.out.println(Arrays.toString(radixSort));
                radixsort(radixSort, stats);
                //System.out.println("po posortowaniu:");
                //System.out.println(Arrays.toString(radixSort));


            }
            System.out.println(stats.get(0) / repeat);
            //System.out.println("Compares " + stats.get(0)/repeat + " moves " + stats.get(1)/repeat);

        }
    }
}

