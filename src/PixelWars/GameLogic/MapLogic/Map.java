package PixelWars.GameLogic.MapLogic;

import PixelWars.GameLogic.MapLogic.Generation.NoiseGenerator;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Set;

public class Map {
    public static final int MAP_WIDTH_PX = 1440, MAP_HEIGHT_PX = 840;
    public static final double[] terrainHeights = new double[]{0.2D, 0.345D, 0.38D, 0.58D, 0.83D, 0.91D, 1.0D};
    public static final Color[] terrainColors = new Color[]{Color.rgb(0, 93, 244), Color.DODGERBLUE, Color.BEIGE, Color.GREEN, Color.DARKGREEN, Color.DARKGREY, Color.WHITESMOKE};
    private static final Class[] terrainClasses = new Class[]{NonoperationalMapTile.class, NonoperationalMapTile.class, OperationalMapTile.class, OperationalMapTile.class, OperationalMapTile.class, OperationalMapTile.class, NonoperationalMapTile.class};
    private int tileSize;
    private static HashMap<String, Integer> mapSizes, resDensities;
    private int width;
    private int height;
    private double[][] noiseMatrix;
    private MapTile[][] mapTilesMatrix;

    static {
        mapSizes = new HashMap<>();
        mapSizes.put("Small", 12);
        mapSizes.put("Medium", 10);
        mapSizes.put("Large", 8);
        mapSizes.put("Giant", 6);

        resDensities = new HashMap<>();
        resDensities.put("Starvation", 3);
        resDensities.put("Moderate", 5);
        resDensities.put("Richness", 7);
    }

    public Map(String mapType) {
        tileSize = mapSizes.get(mapType);
        width = MAP_WIDTH_PX / tileSize;
        height = MAP_HEIGHT_PX / tileSize;
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

    public static Set<String> getMapSizes() {
        return mapSizes.keySet();
    }

    public static Set<String> getResDensities() {
        return resDensities.keySet();
    }

}