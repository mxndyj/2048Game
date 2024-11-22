package model;


import java.util.Random;
/**
 * The GameBoard class represents the game grid for the 2048 game.
 * It initializes the board, spawns random tiles, and provides methods
 * for displaying the board and accessing its state.
 */

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
        if (randomGenerator.nextInt(10) >= 8) { // 2 out of 10 values 20% 
            value = 4;
        } else { // Remaining 8 out of 10 values 80 %
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

        System.out.println("[" + value + "] Spawn at row : " + (rowIndex+1) + " col : " + (columnIndex+1));
    }
    
    //deep copy of grid
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
    
    // if board has another valid move (end condition) 
    private boolean hasMove() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (gridBoard[i][j].isEmpty()) {
                    return true;
                }

                if (i < SIZE - 1 && j < SIZE - 1) {
                    if (gridBoard[i][j].getTileValue() == gridBoard[i + 1][j].getTileValue() ||
                            gridBoard[i][j].getTileValue() == gridBoard[i][j + 1].getTileValue()) {
                        return true;
                    }
                }

                if (i == SIZE - 1 && j < SIZE - 1) {
                    if (gridBoard[i][j].getTileValue() == gridBoard[i][j + 1].getTileValue()) {
                        return true;
                    }
                }

                if (j == SIZE - 1 && i < SIZE - 1) {
                    if (gridBoard[i][j].getTileValue() == gridBoard[i + 1][j].getTileValue()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    
    
    public boolean isGameOver() {
        return !hasMove();
    }


    // after each move, spawn another random tile
    public void placeRandomTile() {
        if (hasEmptyTile()) {
            assignRandomValue(); 
        } else {
            System.out.println("No empty tiles available to place a new tile.");
        }
    }
    
    
    //helper for placeRandomtile, checks if there is empty tile
    private boolean hasEmptyTile() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (gridBoard[i][j].isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    
    
    public int[] move(MoveTileStrategy strategy) {
        return strategy.moveTile(gridBoard);
    }
    
    
    // MoveTileStrategy.java , MoveTileDown, MoveTileUp, MoveTileLeft, MoveTileRight

    
    
    
    



}
