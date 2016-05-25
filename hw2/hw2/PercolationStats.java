package hw2;                       

public class PercolationStats {
    private double[] counts;
    private int times;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T){
        counts = new double[T];
        times = T;
        for(int i = 0; i < T; i++) {
            Percolation p = new Percolation(N);
            int count = 0;
            while (!p.percolates()) {
                p.openRandom();
                count++;
            }
            counts[i] = (double) count / (double) (N * N);
        }
    }
    // sample mean of percolation threshold
    public double mean() {
        double sum = 0;
        for(int i = 0; i < times; i++) {
            sum += counts[i];
        }
        return (double) (sum / times);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double sum = 0;
        double mean = mean();
        for(int i = 0; i < times; i++) {
            sum += (counts[i] - mean) * (counts[i] - mean);
        }
        return (double)Math.sqrt(sum / (times - 1));
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - ((double) 1.96 * stddev() / Math.sqrt(times));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + ((double) 1.96 * stddev() / Math.sqrt(times));
    }

    public static void main(String[] args) {
        PercolationStats test = new PercolationStats(50, 100);
        System.out.println(test.mean());
    }
}                       
