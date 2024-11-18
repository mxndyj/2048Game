package model;

import java.util.Random;

public class GameBoard {
    private static final int SIZE = 4;

    private Tile[][] gridBoard; 
    private final Random randomGenerator;

    public GameBoard() {
        randomGenerator = new Random();
        generateGrid();
        //start with two tiles
        assignRandomValue();
        assignRandomValue();
    }

    private void generateGrid() {
        gridBoard = new Tile[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                gridBoard[i][j] = new Tile(); 
            }
        }
    }

    private void assignRandomValue() {
    	int value;
        //  lower probability for appearing 4
        if (randomGenerator.nextInt(10) > 7) { // 2 out of 10 values 20% 
            value = 4;
        } else { // Remaining 7 out of 10 values 70 %
            value = 2;
        }
        //  starting with a random index
        int rowIndex = randomGenerator.nextInt(SIZE);
        int columnIndex = randomGenerator.nextInt(SIZE);

        // find an empty position
        while (!gridBoard[rowIndex][columnIndex].isEmpty()) {
            rowIndex = randomGenerator.nextInt(SIZE);
            columnIndex = randomGenerator.nextInt(SIZE);
        }
        //set tile value at an empty position
        gridBoard[rowIndex][columnIndex].setTileValue(value);

        System.out.println("[" + value + "] Spawn at row : " + rowIndex + " col : " + columnIndex);
    }
    
    //deep copy
    public Tile[][] getModelGridBoard() {
        Tile[][] copy = new Tile[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                copy[i][j] = new Tile();
                copy[i][j].setTileValue(gridBoard[i][j].getTileValue());
            }
        }
        return copy;
    } 

    //print to console current gameboard grid
    public void showTiles() {
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("%4d ", gridBoard[i][j].getTileValue());
            }
            System.out.println();
        }
        System.out.println();
    }




}
