package PixelWars.GameLogic.MapLogic;

import PixelWars.GameLogic.MapLogic.Generation.NoiseGenerator;
import javafx.scene.paint.Color;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

public class Map {
    private static final int MAP_WIDTH_RATIO = 3, MAP_HEIGHT_RATIO = 2;
    public static final double[] terrainHeights = new double[]{0.2D, 0.345D, 0.38D, 0.58D, 0.83D, 0.91D, 1.0D};
    public static final Color[] terrainColors = new Color[]{Color.rgb(0, 93, 244), Color.DODGERBLUE, Color.BEIGE, Color.GREEN, Color.DARKGREEN, Color.DARKGREY, Color.WHITESMOKE};
    private static final Class[] terrainClasses = new Class[]{NonoperationalMapTile.class, NonoperationalMapTile.class, OperationalMapTile.class, OperationalMapTile.class, OperationalMapTile.class, OperationalMapTile.class, NonoperationalMapTile.class};
    private static final int DEFAULT_TILE_SIZE=12;
    private static HashMap<String, Integer> mapSizeMultipliers, resDensities;
    private int width;
    private int height;
    private double[][] noiseMatrix;
    private MapTile[][] mapTilesMatrix;

    static {
        mapSizeMultipliers = new LinkedHashMap<>();
        mapSizeMultipliers.put("Tiny", 60);
        mapSizeMultipliers.put("Small", 70);
        mapSizeMultipliers.put("Medium", 80);
        mapSizeMultipliers.put("Large", 90);
        mapSizeMultipliers.put("Giant", 100);

        resDensities = new LinkedHashMap<>();
        resDensities.put("Starvation", 3);
        resDensities.put("Moderate", 5);
        resDensities.put("Richness", 7);
    }

    public Map(String mapType) {
        int multiplier=mapSizeMultipliers.get(mapType);
        width = MAP_WIDTH_RATIO*multiplier;
        height = MAP_HEIGHT_RATIO*multiplier;
        noiseMatrix = NoiseGenerator.generateNoiseMatrix(width, height, (int) System.currentTimeMillis(), 70, 5, 0.5D, 1.7D);
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

    public static int getDefaultTileSize() {
        return DEFAULT_TILE_SIZE;
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

    public static Set<String> getMapSizes() {
        return mapSizeMultipliers.keySet();
    }

    public static int getMapSizeMultiplier(String mapType) {
        return mapSizeMultipliers.get(mapType);
    }

    public static Set<String> getResDensities() {
        return resDensities.keySet();
    }

}