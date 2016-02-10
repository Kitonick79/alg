import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by Miagkov Dmitrii  on 2/6/2016.
 * <p>
 * This is the main class for statistical analysis of percolation of grid,
 * it takes two parameters:
 * N - dimension of a Grid
 * T - number of experiments
 */
public class PercolationStats {
    private Percolation perc;       // Stores percolation Grid
    private int size;               // Size of the Grid
    private int times;              // Number of experiments
    private double[] openedFrac;    // Fraction of opened sites is each experiment

    public PercolationStats(int N, int T) { // perform T independent experiments on an N-by-N grid
        size = N; //initialization of Grid size
        times = T; // initialization of number of experiments
        openedFrac = new double[times]; // setting of array's dimension

        if (size <= 0 || times <= 0)
            throw new IllegalMonitorStateException("Size and/or number of experiments// are less or equal to zero");

        for (int i = 0; i < times; i++) {

            perc = new Percolation(size);

            while (!perc.percolates()) { // run experiments until Grid  percolates
                int RandomI = StdRandom.uniform(1, N+1);
                int RandomJ = StdRandom.uniform(1, N+1);
                if (!perc.isOpen(RandomI, RandomJ) && !perc.isFull(RandomI, RandomJ)) {
                    perc.open(RandomI, RandomJ); // open site chosen at Random
                    openedFrac[i] += 1 / ((double) size * (double) size);  //increase number of opened sites
                }
            }

        }
    }

    public double mean() { // sample mean of percolation threshold

        return StdStats.mean(openedFrac);
    }

    public double stddev() {// sample standard deviation of percolation threshold

        return StdStats.stddev(openedFrac);
    }

    public double confidenceLo() {// low  endpoint of 95% confidence interval

        return (mean()-(1.96 * Math.sqrt(stddev())) / Math.sqrt(times));
    }

    public double confidenceHi() {// high endpoint of 95% confidence interval

        return (mean()+(1.96 * Math.sqrt(stddev())) / Math.sqrt(times));
    }


    public static void main(String[] args) { // test client
        int N = StdIn.readInt();
        int T = StdIn.readInt();

        PercolationStats PercStats = new PercolationStats(N, T);
        // System.out.println(Arrays.toString(PercStats.openedNumber));
        System.out.println("mean = "+Double.toString(PercStats.mean()));
        System.out.println("stddev = "+Double.toString(PercStats.stddev()));
        System.out.println("95% confidence interval = " + Double.toString(PercStats.confidenceLo()) + ", "
                + Double.toString(PercStats.confidenceHi()));


    }
}
