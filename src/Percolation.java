/**
 * Created by Dmitrii_Miagkov on 1/30/2016.
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    int[][] Grid; // Matrix to store state
    WeightedQuickUnionUF GridUF; // Linear massive for Union-Find operations
    public int size; // Size of Grid


    public Percolation(int N) { // Constructor
        size = N;
        Grid = new int[size][size];
        GridUF = new WeightedQuickUnionUF (size*size + 2); // 2 extra nodes above and bellow the matrix
        for (int i=0; i < N; i++){
            for (int j=0; j < N; j++){
                Grid[i][j] = 0; // zero means that site is blocked
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
        checkRange(i);
        checkRange(j);
        int  i1 = convertGridIndex(i); // indices in Grid Matrix
        int  j1 = convertGridIndex(j); // indices in Grid Matrix
        int  GridUFPos = convertGridUFIndex(i,j); //Position in Matrix for Union-Find

        Grid[i1][j1] = 1; // both indexes starts from zero, one means site is open

        if ( i > 1 && (Grid[i1 - 1][j1] != 0) ){
            GridUF.union(GridUFPos, convertGridUFIndex(i - 1, j));
            if (GridUF.connected(0, convertGridUFIndex(i - 1, j))){Grid[i1-1][j1] = 2;}
        } //connect the site above

        if ( i < size && (Grid[i1 +1][j1] != 0) ){
            GridUF.union(GridUFPos, convertGridUFIndex(i + 1, j));
            if (GridUF.connected(0, convertGridUFIndex(i + 1,j))){Grid[i1 +1][j1] = 2;}
        } //connect the site bellow

        if ( j < size && (Grid[i1][j1 + 1] != 0) ){
            GridUF.union(GridUFPos, convertGridUFIndex(i, j + 1));
            if (GridUF.connected(0, convertGridUFIndex(i,j + 1))){Grid[i1][j1 + 1] = 2;}
        } //connect the site to the right

        if ( j > 1 && (Grid[i1][j1 - 1] != 0) ){
            GridUF.union(GridUFPos, convertGridUFIndex(i, j - 1));
            if (GridUF.connected(0, convertGridUFIndex(i,j - 1))){Grid[i1][j1 - 1] = 2;}
        } //connect the site to the left

        if (i == 1){GridUF.union(GridUFPos, 0);}

        if (i == size){GridUF.union(GridUFPos, size*size + 1);}

        if (GridUF.connected(0, convertGridUFIndex(i,j))){Grid[i1][j1] = 2;} // two means site is Full
    }

    public boolean isOpen(int i, int j){
        checkRange(i);
        checkRange(j);
        int  i1 = convertGridIndex(i);
        int  j1 = convertGridIndex(j);

        if(Grid[i1][j1] == 0){return false;}
        return true;
    }

    public boolean isFull(int i, int j){
        checkRange(i);
        checkRange(j);
        int  i1 = convertGridIndex(i);
        int  j1 = convertGridIndex(j);

        if (Grid[i1][j1] == 2){return true;}
        else {return false;}
    }

    public boolean percolates () {

        return GridUF.connected(0, (size*size +1) );
    }

    public static void main(String[] args) {

        Percolation Test = new Percolation(3);
        Test.open(1,1);
        Test.open(2,1);
        Test.open(3,1);
        System.out.println(Test.percolates());
    }
}
