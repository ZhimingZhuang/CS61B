package hw2;                       

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.IllegalArgumentException;
import java.lang.IndexOutOfBoundsException;
import java.util.Random;

public class Percolation {
	private WeightedQuickUnionUF weigtedJoin;
	private WeightedQuickUnionUF nonPercolate;

	private int N;
	private boolean[] grid;
	private int size;
	private Random randomGenerator;

	//create N-by-N grid, with all sites initially blocked
	public Percolation(int N) {
		size = 0;
		this.N = N;
		if(N <= 0) throw new IndexOutOfBoundsException();

		int total = N * N;
		weigtedJoin = new WeightedQuickUnionUF(total + 2);
		nonPercolate = new WeightedQuickUnionUF(total + 1);
		randomGenerator = new Random();

		for(int i = 0; i < N; i++) {
			weigtedJoin.union(i, total);
			nonPercolate.union(i, total);
		}
		for(int i = total - N; i < total; i++) {
			weigtedJoin.union(i, total + 1);
		}
		grid = new boolean[total];
	}  

	// change 2D coordinate to 1D
	private int xyTo1D(int r, int c) {
		return r * N + c;
	}

	// open the site (row, col) if it is not open already
	public void open(int row, int col) {
		if(row < 0 || row >= N || col < 0 || col >= N) 
			throw new IllegalArgumentException();
		if(isOpen(row, col)) return;
		int index = xyTo1D(row, col);
		grid[index] = true;
		size++;
		// left
		if(col - 1 >= 0 && isOpen(row, col - 1)) {
			weigtedJoin.union(index - 1, index);
			nonPercolate.union(index - 1, index);
		}
		// right
		if(col + 1 < N && isOpen(row, col + 1)) {
			weigtedJoin.union(index + 1, index); 
			nonPercolate.union(index + 1, index);
		}
		// up
		if(row - 1 >= 0 && isOpen(row - 1, col)) {
			weigtedJoin.union(index - N, index);
			nonPercolate.union(index - N, index);
		}
		// down
		if(row + 1 < N && isOpen(row + 1, col)) {
			weigtedJoin.union(index + N, index);
			nonPercolate.union(index + N, index);
		}
	}  

	// is the site (row, col) open?
	public boolean isOpen(int row, int col) {
		if(row < 0 || row >= N || col < 0 || col >= N) 
			throw new IllegalArgumentException();

		int index = xyTo1D(row, col);
		return grid[index];
	}

	// is the site (row, col) full?
	public boolean isFull(int row, int col) {
		if(row < 0 || row >= N || col < 0 || col >= N) 
			throw new IllegalArgumentException();
		int index = xyTo1D(row, col);
		return isOpen(row, col) && nonPercolate.connected(index, N * N);
	}

	// number of open sites
    public int numberOfOpenSites() {
    	return size;
    }   

    // does the system percolate?
    public boolean percolates() {
    	return weigtedJoin.connected(N * N, N * N + 1);
    }   


	// open a random point
	public void openRandom() {
		int total = N * N;
		int index, row, col;
		do {
			index = randomGenerator.nextInt(total);
			row = index / N;
			col = index % N;
		}while(isOpen(row, col) && size < total);
		if(size < total)
			open(row, col);
	}

	// unit testing (not required)
	public static void main(String[] args) {


	}
}                       
