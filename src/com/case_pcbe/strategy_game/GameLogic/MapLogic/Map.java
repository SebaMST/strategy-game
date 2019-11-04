package com.case_pcbe.strategy_game.GameLogic.MapLogic;

import com.case_pcbe.strategy_game.Generation.NoiseGenerator;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class Map {
    private static final int MAP_WIDTH_PX = 1280, MAP_HEIGHT_PX = 900;
    public static final double[] terrainHeights = new double[]{0.2D, 0.345D, 0.37D, 0.58D, 0.75D, 0.85D, 1.0D};
    public static final Color[] terrainColors = new Color[]{Color.BLUE, Color.DODGERBLUE, Color.BEIGE, Color.GREEN, Color.DARKGREEN, Color.DARKGREY, Color.WHITESMOKE};
    public static final Class[] terrainClasses = new Class[]{NonoperationalMapTile.class, NonoperationalMapTile.class, OperationalMapTile.class, OperationalMapTile.class, OperationalMapTile.class, OperationalMapTile.class, NonoperationalMapTile.class};
    private int tileSize;
    private static HashMap<String, Integer> mapSizes;
    private int width;
    private int height;
    private double[][] noiseMatrix;
    private MapTile[][] mapTilesMatrix;

    static {
        mapSizes = new HashMap<>();
        mapSizes.put("Tiny", 7);
        mapSizes.put("Small", 6);
        mapSizes.put("Medium", 5);
        mapSizes.put("Large", 4);
        mapSizes.put("Giant", 3);
    }

    public Map(String mapType) {
        tileSize = mapSizes.get(mapType);
        width = MAP_WIDTH_PX / tileSize;
        height = MAP_HEIGHT_PX / tileSize;
        noiseMatrix = NoiseGenerator.generateNoiseMatrix(width, height, (int) System.currentTimeMillis(), 120, 4, 0.5D, 2.0D);
        mapTilesMatrix = fromNoiseToTiles();
    }

    private MapTile[][] fromNoiseToTiles() {
        MapTile[][] mapTilesMatrix = new MapTile[noiseMatrix.length][noiseMatrix[0].length];
        for (int i = 0; i < noiseMatrix.length; ++i) {
            for (int j = 0; j < noiseMatrix[0].length; ++j) {
                for (int h = 0; h < terrainHeights.length; ++h) {
                    if (noiseMatrix[i][j] <= terrainHeights[h]) {
                        try {
                            mapTilesMatrix[i][j] = (MapTile) terrainClasses[h].newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }
        return mapTilesMatrix;
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double[][] getNoiseMatrix() {
        return noiseMatrix;
    }

    public MapTile[][] getMapTilesMatrix() {
        return mapTilesMatrix;
    }

}