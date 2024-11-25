package model;

import java.util.ArrayList;
import java.util.List;
 

public class MoveTileRight implements MoveTileStrategy {

    @Override
    public int[] moveTile(Tile[][] gridBoard) {
        int totalScore = 0;
        int isValidMove = 0;

        for (int i = 0; i < gridBoard.length; i++) {
            List<Integer> items = new ArrayList<>();

            // Collect non-zero tiles in the row
            for (int j = gridBoard[0].length - 1; j >= 0; j--) {
                int value = gridBoard[i][j].getTileValue();
                if (value != 0) {
                    items.add(value);
                }
            }

            // mergre adjacent tiles with the same value
            for (int j = 0; j < items.size() - 1; j++) {
                if (items.get(j).equals(items.get(j + 1))) {
                    int mergedValue = 2 * items.get(j);
                    totalScore += mergedValue;
                    items.set(j, mergedValue);
                    items.remove(j + 1);
                }
            }

            // Place merged/moved tiles back into the grid
            for (int j = gridBoard[0].length - 1; j >= 0; j--) {
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
