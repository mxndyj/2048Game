package model;


import java.util.ArrayList;
import java.util.List;


public class MoveTileUp implements MoveTileStrategy {

    @Override
    public int[] moveTile(Tile[][] gridBoard) {
        int totalScore = 0;
        int isValidMove = 0;

        for (int j = 0; j < gridBoard[0].length; j++) {
            List<Integer> items = new ArrayList<>();

            // Collect non-zero tiles in   the column
            for (int i = 0; i < gridBoard.length; i++) {
                int value = gridBoard[i][j].getTileValue();
                if (value != 0) {
                    items.add(value);
                }
            }

             // Merge adjacent tiles with the same  value
            for (int i = 0; i < items.size() - 1; i++) {
                if (items.get(i).equals(items.get(i + 1))) {
                    int mergedValue = 2 * items.get(i);
                    totalScore += mergedValue;
                    items.set(i, mergedValue);
                    items.remove(i + 1);
                }
            }

            // Place merged/moved  tiles back into the grid
            for (int i = 0; i < gridBoard.length; i++) {
                if (!items.isEmpty()) {
                    int changedValue = items.remove(0);
                    if (gridBoard[i][j].getTileValue() != changedValue) {
                        isValidMove = 1;
                    }
                    gridBoard[i][j].setTileValue(changedValue);
                } else {
                    if (!gridBoard[i][j].isEmpty()) {
                        isValidMove = 1;
                    }
                    gridBoard[i][j].setEmpty();
                }
            }
        }
        return new int[]{isValidMove, totalScore};
    }
}



