package PixelWars.GameLogic.MapLogic.MapCreation;

import PixelWars.GameLogic.MapLogic.MapTile;

public class Map {

    private MapTile[][] mapTilesMatrix;
    private int width,height;

    Map(MapTile[][] mapTilesMatrix) {
        this.mapTilesMatrix=mapTilesMatrix;
        this.width=mapTilesMatrix[0].length;
        this.height=mapTilesMatrix.length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public MapTile[][] getMapTilesMatrix() {
        return mapTilesMatrix;
    }

}