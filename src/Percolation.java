/**
 * Created by Dmitrii_Miagkov on 1/30/2016.
 *
 * This tools models percolation of a grid NxN size
 *
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean [][] grid;              // holds status open/bloched of a site. Models percolation grid
    private WeightedQuickUnionUF gridUFNC;  // To hold the object without top and bottom sites
    private WeightedQuickUnionUF gridUF;    // With bottom and opened sites
    private int size;               // size of a grid along axis

    public Percolation(int N) { // constructor

        if (N <= 0) throw new IllegalArgumentException("size of grid must be positive");

        size = N;                                           //size of grid
        grid = new boolean[size][size];                     // Array to track if site is blocked or opened
        gridUF = new WeightedQuickUnionUF(size*size + 2); // An Array to be used by Union-Find algorithm
        gridUFNC = new WeightedQuickUnionUF(size*size + 1); // To elimenate backwash

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false; // site initialization
            }
        }
    }

    /**
     * Checks if parameters are within prescribed range
     * @param i - index of site along any axis
     */
    private void checkRange(int i) {
        if (i <= 0 || i > size) throw new IndexOutOfBoundsException("row index i out of bounds");
    }

    /**
     * Converts index from 1 .. N range to 0 .. N-1
     * @param i - index of site along any axis
     * @return index for grid
     */
    private int convertGridIndex(int i) {
        return i-1;
    }

    /**
     * Converts grid index to 1D Array
     *
     * @param i - column in the grid
     * @param j - row in the grid
     * @return - index in the Array for Union-Find operations
     */
    private int convertGridUFIndex(int i, int j) {
        return (i-1)*size + j;
    }

    /**
     * Opens a site at indexes. Unions it with neighbours;
     *
     * @param i - column in the grid
     * @param j - row in the grid
     */
    public void open(int i, int j) {
        checkRange(i); //index check
        checkRange(j); //index check

        int  i1 = convertGridIndex(i);
        int  j1 = convertGridIndex(j);

        int  GridUFPos = convertGridUFIndex(i, j);

        grid[i1][j1] = true; // both indexes starts from zero, one means site is open

        if (i > 1 && grid[i1 - 1][j1]) {
            gridUF.union(GridUFPos, convertGridUFIndex(i - 1, j)); //connect the site above
            gridUFNC.union(GridUFPos, convertGridUFIndex(i - 1, j));
        }

        if (i < size && grid[i1 +1][j1]) {
            gridUF.union(GridUFPos, convertGridUFIndex(i + 1, j)); //connect the site bellow
            gridUFNC.union(GridUFPos, convertGridUFIndex(i + 1, j));

        }

        if (j < size && grid[i1][j1 + 1]) {
            gridUF.union(GridUFPos, convertGridUFIndex(i, j + 1)); //connect the site to the right
            gridUFNC.union(GridUFPos, convertGridUFIndex(i, j + 1));
        }

        if (j > 1 && grid[i1][j1 - 1]) {
            gridUF.union(GridUFPos, convertGridUFIndex(i, j - 1)); //connect the site to the left
            gridUFNC.union(GridUFPos, convertGridUFIndex(i, j - 1));
        } //connect the site to the left

        if (i == 1) {
            gridUF.union(GridUFPos, 0);
            gridUFNC.union(GridUFPos, 0); }

        if (i == size) {
            gridUF.union(GridUFPos, size*size + 1); } //connect bottom row to last, non-grid node

    }

    /**
     * Checks if the site is open
     * @param i - column
     * @param j - row
     * @return
     */
    public boolean isOpen(int i, int j) {
        checkRange(i);
        checkRange(j);

        return grid[convertGridIndex(i)][convertGridIndex(j)];
    }

    /**
     * Checks if the site is Full
     * @param i - column
     * @param j - row
     * @return
     */
    public boolean isFull(int i, int j) {
        checkRange(i);
        checkRange(j);

        return gridUFNC.connected(0, convertGridUFIndex(i, j)); // check if the node at (i, j) is connected to top
    }

    /**
     * Checks if the system percolates
     * @return
     */
    public boolean percolates() {

        return gridUF.connected(0, size*size + 1); // check if top and bottom are connected
    }

    public static void main(String[] args) {
        Percolation perc = new Percolation(-1);
        perc.open(1, 2);
    }
}
