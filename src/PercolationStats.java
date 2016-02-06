import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import java.awt.*;
import java.util.Arrays;

/**
 * Created by Admin on 2/6/2016.
 */
public class PercolationStats {
    Percolation Perc; // Stores percolation Grid
    int size;         // Size of the Grid
    int times;        // Number of experiments
    int[] openedNumber; // Number of opened sites for each experiment
    private static final int DELAY = 200;

    public PercolationStats(int N, int T){ // perform T independent experiments on an N-by-N grid
        size = N;
        times = T;
        openedNumber = new int[T];

        for (int i=0; i < T; i++) {

            Percolation Perc = new Percolation(N);

            while (!Perc.percolates()) { // run experiments until Grid  percolates
                int RandomI = StdRandom.uniform(1, N + 1);
                int RandomJ = StdRandom.uniform(1, N + 1);
                if (!Perc.isOpen(RandomI, RandomJ) && !Perc.isFull(RandomI, RandomJ)) {
                    Perc.open(RandomI, RandomJ); // open site chosen at Random
                    PercolationVisualizer.draw(Perc, N);
                    //StdDraw.show(DELAY);
                    openedNumber[i] += 1;  //increase number of opened sites
                }
            }

        }
    }

    public static void main(String[] args) {
        //In in = new In(args[0]);      // input file


        int N = 5;
        int T = 2;
//        while (!StdIn.hasNext()) {
            N = StdIn.readInt();
            T = StdIn.readInt();
//        }

        PercolationStats PercStats = new PercolationStats(N, T);
        System.out.println(Arrays.toString(PercStats.openedNumber));
    }
}
