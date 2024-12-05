package model;


/**
 * An interface that defines the strategy for moving tiles on the game board.
 * 
 */
public interface MoveTileStrategy {
    int[] moveTile(Tile[][] gridBoard);
}
