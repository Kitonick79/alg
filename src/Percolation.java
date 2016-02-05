/**
 * Created by Dmitrii_Miagkov on 1/30/2016.
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    boolean [][] Grid;
    boolean [][] GridF;
    WeightedQuickUnionUF GridUF;
    private int size;
    private boolean percolates;


    public Percolation(int N) {
        size = N;
        Grid = new boolean[size][size];
        GridUF = new WeightedQuickUnionUF (size*size + 1); // 2 extra nodes above and bellow the matrix
        GridF = new boolean[size][size];
        for (int i=0; i < N; i++){
            for (int j=0; j < N; j++){
                Grid[i][j] = false; // zero means that site is blocked
                GridF[i][j] = false; // zero means that site is blocked
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
        int  i1 = convertGridIndex(i);
        int  j1 = convertGridIndex(j);
        int  GridUFPos = convertGridUFIndex(i,j);

        Grid[i1][j1] = true; // both indexes starts from zero, one means site is open

        if ( i > 1 && Grid[i1 - 1][j1] ){GridUF.union(GridUFPos, convertGridUFIndex(i - 1,j));} //connect the site above

        if ( i < size && Grid[i1 +1][j1] ){GridUF.union(GridUFPos, convertGridUFIndex(i + 1, j));} //connect the site bellow

        if ( j < size && Grid[i1][j1 + 1] ){GridUF.union(GridUFPos, convertGridUFIndex(i, j + 1));} //connect the site to the right

        if ( j > 1 && Grid[i1][j1 - 1] ){GridUF.union(GridUFPos, convertGridUFIndex(i, j - 1));} //connect the site to the left

        if (i == 1){GridUF.union(GridUFPos, 0);}



    }

    public boolean isOpen(int i, int j){

        return Grid[convertGridIndex(i)][convertGridIndex(j)];
    }

    public boolean isFull(int i, int j) {

//               int  i1 = convertGridIndex(i);
//        int  j1 = convertGridIndex(j);
//
//        if (percolates()) {
//            if (GridF[i1][j1]) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//            else {
//                if (GridUF.connected(0, convertGridUFIndex(i, j))) {
//                    GridF[i1][j1] = true;
//                    return true;
//                }
//            }
//
        if (i == size && GridUF.connected(0, convertGridUFIndex(i, j))){
            percolates = true;
        }

        return GridUF.connected(0, convertGridUFIndex(i, j));
    }

    public boolean percolates () {


        return percolates;
    }

    public static void main(String[] args) {

        Percolation Test = new Percolation(3);
        Test.open(1,1);
        Test.open(2,1);
        Test.open(3,1);
        System.out.println(Test.percolates());
    }
}
