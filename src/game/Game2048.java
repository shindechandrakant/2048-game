package game;

import java.util.ArrayList;
import java.util.Random;

public class Game2048 {

    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int UP = 3;
    private static final int DOWN = 4;
    private static final int LIMIT = 2048;

    private final int[][] grid;
    private final Random random;
    private final int gridSize;

    public Game2048(int gridSize) {

        this.grid = new int[gridSize][gridSize];
        this.random = new Random();
        this.gridSize = gridSize;
        putNumberIntoGrid();
        putNumberIntoGrid();
        printGrid();
    }

    private class Pair {

        int row, col;
        public Pair(int row, int col) {

            this.row = row;
            this.col = col;
        }
        public int getRow() {
            return row;
        }
        public int getCol() {
            return col;
        }
    }

    private void printGrid() {

        for(int i = 0; i < grid.length; i++) {

            for(int j = 0; j < grid[i].length; j++) {

                System.out.printf("%4d", grid[i][j]);
            }
            System.out.println();
        }
    }

    public boolean nextMove(int userInput) {

        switch (userInput) {
            case LEFT:
                leftMove();
                break;
            case RIGHT:
                rightMove();
                break;
            case UP:
                upMove();
                break;
            case DOWN:
                downMove();
        }

        boolean isGameOver = putNumberIntoGrid();
        printGrid();
        return isGameOver;
    }

    private boolean putNumberIntoGrid() {

        ArrayList<Pair> emptySpots = new ArrayList<>();
        for(int row = 0; row < grid.length; row++) {

            for(int col = 0; col < grid[row].length; col++) {

                // if the Cell reaches at 2048 then it will end game
                if(grid[row][col] == LIMIT) {

                    return true;
                }

                // if grid is empty then add location for random number input
                if(grid[row][col] == 0) {

                    emptySpots.add(new Pair(row, col));
                }
            }
        }

        if(emptySpots.size() == 0) {
            return isGameOver();
        }

        int indx = random.nextInt(emptySpots.size());
        int row = emptySpots.get(indx).getRow();
        int col = emptySpots.get(indx).getCol();
        grid[row][col] = ((row+col) % 2 == 0 ? 2 : 4);
        return false;
    }

    private boolean isGameOver() {

        for(int row = 0; row < gridSize; row++) {

            for(int col = 0; col < gridSize; col++) {

                if(row-1 >= 0 && grid[row][col] == grid[row-1][col]) {
                    return false;
                }
                if(col + 1 < gridSize && grid[row][col] == grid[row][col+1]) {
                    return false;
                }
                if(row+1 < gridSize && grid[row][col] == grid[row+1][col]) {
                    return false;
                }
                if(col-1 >= 0 && grid[row][col] == grid[row][col-1]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void shiftRowZeros(int[] arr) {

        int n = arr.length;
        for(int i = 0; i < n; i++) {

            int end = i;
            int start = i;
            while(end < n && arr[end] == 0) {
                end++;
            }
            while(end < n) {
                arr[start] = arr[end];
                start++;
                end++;
            }
            while(start < n) {
                arr[start] = 0;
                start++;
            }
        }
    }

    private void addRowElements(int[] arr) {

        int n = arr.length;
        shiftRowZeros(arr);
        for(int i = 0; i < n-1; i++) {
            if(arr[i] == arr[i+1]) {
                arr[i] += arr[i+1];
                arr[i+1] = 0;
            }
        }
        shiftRowZeros(arr);
    }

    private void leftMove() {

        for(int row = 0; row < gridSize; row++) {
            int[] arr = new int[gridSize];
            for(int col = 0; col < gridSize; col++) {

                arr[col] = grid[row][col];
            }
            addRowElements(arr);
            for(int col = 0; col < gridSize; col++) {

                grid[row][col] = arr[col];
            }
        }
    }

    private void rightMove() {

        for(int row = 0; row < gridSize; row++) {

            int[] arr = new int[gridSize];
            int counter = 0;
            for(int col = gridSize-1; col >= 0; col--) {

                arr[counter] = grid[row][col];
                counter++;
            }
            addRowElements(arr);
            counter = 0;
            for(int col = gridSize-1; col >= 0; col--) {

                grid[row][col] = arr[counter];
                counter++;
            }
        }
    }

    private void upMove() {

        for(int col = 0; col < gridSize; col++) {

            int[] arr = new int[gridSize];
            int counter = 0;
            for(int row = 0; row < gridSize; row++) {

                arr[counter] = grid[row][col];
                counter++;
            }

            addRowElements(arr);
            counter = 0;
            for(int row = 0; row < gridSize; row++) {

                grid[row][col] = arr[counter];
                counter++;
            }
        }
    }

    private void downMove() {

        for(int col = 0; col < gridSize; col++) {

            int[] arr = new int[gridSize];
            int counter = gridSize-1;
            for(int row = 0; row < gridSize; row++) {

                arr[counter] = grid[row][col];
                counter--;
            }

            addRowElements(arr);
            counter = gridSize-1;
            for(int row = 0; row < gridSize; row++) {

                grid[row][col] = arr[counter];
                counter--;
            }
        }
    }

}
