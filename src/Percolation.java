/**
 * Created by Dmitrii_Miagkov on 1/30/2016.
 *
 * This tools models percolation of a Grid NxN size
 *
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean [][] Grid;              // holds status open/bloched of a site. Models percolation Grid
    private WeightedQuickUnionUF GridUFNC;  // To hold the object without top and bottom sites
    private WeightedQuickUnionUF GridUF;    // With bottom and opened sites
    private int size;               // size of a Grid along axis

    public Percolation(int N) { // constructor
        size = N;                                           //size of Grid
        Grid = new boolean[size][size];                     // Array to track if site is blocked or opened
        GridUF = new WeightedQuickUnionUF (size*size + 2); // An Array to be used by Union-Find algorithm
        GridUFNC = new WeightedQuickUnionUF (size*size + 1); // To elimenate backwash

        if (N < 0) throw new IllegalArgumentException("size of Grid must be positive");

        for (int i=0; i < N; i++){
            for (int j=0; j < N; j++){
                Grid[i][j] = false; // site initialization
            }
        }
    }

    /**
     * Checks if parameters are within prescribed range
     * @param i - index of site along any axis
     */
    private void checkRange(int i){
        if (i <= 0 || i > size) throw new IndexOutOfBoundsException("row index i out of bounds");
    }

    /**
     * Converts index from 1 .. N range to 0 .. N-1
     * @param i - index of site along any axis
     * @return index for Grid
     */
    private int convertGridIndex(int i){
        return i-1;
    }

    /**
     * Converts Grid index to 1D Array
     *
     * @param i - column in the Grid
     * @param j - row in the Grid
     * @return - index in the Array for Union-Find operations
     */
    private int convertGridUFIndex(int i, int j) {
        return (i-1)*size + j;
    }

    /**
     * Opens a site at indexes. Unions it with neighbours;
     *
     * @param i - column in the Grid
     * @param j - row in the Grid
     */
    public void open(int i, int j){
        checkRange(i); //index check
        checkRange(j); //index check

        int  i1 = convertGridIndex(i);
        int  j1 = convertGridIndex(j);

        int  GridUFPos = convertGridUFIndex(i,j);

        Grid[i1][j1] = true; // both indexes starts from zero, one means site is open

        if ( i > 1 && Grid[i1 - 1][j1] ){
            GridUF.union(GridUFPos, convertGridUFIndex(i - 1,j)); //connect the site above
            GridUFNC.union(GridUFPos, convertGridUFIndex(i - 1,j));
        }

        if ( i < size && Grid[i1 +1][j1] ){
            GridUF.union(GridUFPos, convertGridUFIndex(i + 1, j)); //connect the site bellow
            GridUFNC.union(GridUFPos, convertGridUFIndex(i + 1, j));

        }

        if ( j < size && Grid[i1][j1 + 1] ){
            GridUF.union(GridUFPos, convertGridUFIndex(i, j + 1)); //connect the site to the right
            GridUFNC.union(GridUFPos, convertGridUFIndex(i, j + 1));
        }

        if ( j > 1 && Grid[i1][j1 - 1] ){
            GridUF.union(GridUFPos, convertGridUFIndex(i, j - 1)); //connect the site to the left
            GridUFNC.union(GridUFPos, convertGridUFIndex(i, j - 1));
        } //connect the site to the left

        if (i == 1){
            GridUF.union(GridUFPos, 0);
            GridUFNC.union(GridUFPos, 0);}

        if (i == size){GridUF.union(GridUFPos, size*size + 1);} //connect bottom row to last, non-grid node

    }

    /**
     * Checks if the site is open
     * @param i - column
     * @param j - row
     * @return
     */
    public boolean isOpen(int i, int j){
        checkRange(i);
        checkRange(j);

        return Grid[convertGridIndex(i)][convertGridIndex(j)];
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

        return GridUFNC.connected(0, convertGridUFIndex(i, j)); // check if the node at (i, j) is connected to top
    }

    /**
     * Checks if the system percolates
     * @return
     */
    public boolean percolates () {

        return GridUF.connected(0, size*size + 1); // check if top and bottom are connected
    }

}
