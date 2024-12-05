package model;

import java.util.HashMap;

public class Tile {

    private static final HashMap<Integer, String> COLOR_MAP = new HashMap<>();

    static {
    	//colors from original 2048 game
        COLOR_MAP.put(2, "#EEE4DA");
        COLOR_MAP.put(4, "#EDE0C8");
        COLOR_MAP.put(8, "#F2B179");
        COLOR_MAP.put(16, "#F59563");
        COLOR_MAP.put(32, "#F67C5F");
        COLOR_MAP.put(64, "#F65E3B");
        COLOR_MAP.put(128, "#EDCF72");
        COLOR_MAP.put(256, "#EDCC61");
        COLOR_MAP.put(512, "#BD9B39");
        COLOR_MAP.put(1024, "#BE982B");
        COLOR_MAP.put(2048, "#FCC42C");
    }

    private int tileValue;

    public Tile() {
        tileValue = 0; 
    }

    public boolean isEmpty() {
        return tileValue == 0;
    }

    public void setEmpty() {
        tileValue = 0;
    }

    public void setTileValue(int tileValue) {
        this.tileValue = tileValue;
    }

    public int getTileValue() {
        return tileValue;
    }

    public String getColor() {
        return COLOR_MAP.getOrDefault(tileValue, "#CDC1B4");
    }


}
