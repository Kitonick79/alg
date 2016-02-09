/**
 * Created by Dmitrii_Miagkov on 1/30/2016.
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    boolean [][] Grid;              // holds status open/bloched of a site. Models percolation Grid
    WeightedQuickUnionUF GridUFNC;  // To hold the object without top and bottom sites
    WeightedQuickUnionUF GridUF;    // With bottom and opened sites
    private int size;               // size of a Grid along axis

    public Percolation(int N) {
        size = N;
        Grid = new boolean[size][size];
        GridUF = new WeightedQuickUnionUF (size*size + 2); // 2 extra nodes above and bellow the matrix
        GridUFNC = new WeightedQuickUnionUF (size*size + 1); // 2 extra nodes above and bellow the matrix

        for (int i=0; i < N; i++){
            for (int j=0; j < N; j++){
                Grid[i][j] = false; // zero means that site is blocked
            }
        }
    }

    private void checkRange(int i){
        if (i <= 0 || i > size) throw new IndexOutOfBoundsException("row index i out of bounds");
    }

    private int convertGridIndex(int i){
        return i-1;
    }

    private int convertGridUFIndex(int i, int j) {
        return (i-1)*size + j;
    }

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

    public boolean isOpen(int i, int j){
        checkRange(i);
        checkRange(j);

        return Grid[convertGridIndex(i)][convertGridIndex(j)];
    }

    public boolean isFull(int i, int j) {
        checkRange(i);
        checkRange(j);

        return GridUFNC.connected(0, convertGridUFIndex(i, j)); // check if the node at (i, j) is connected to top
    }

    public boolean percolates () {

        return GridUF.connected(0, size*size + 1); // check if top and bottom are connected
    }

    public static void main(String[] args) {

        Percolation Test = new Percolation(3);
        Test.open(1,1);
        Test.open(2,1);
        Test.open(3,1);
        System.out.println(Test.percolates());
    }
}
