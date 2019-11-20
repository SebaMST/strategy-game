package PixelWars.GameLogic.MapLogic.MapCreation;

import PixelWars.GameLogic.MapLogic.MapCreation.TerrainCreation.TerrainBuilder;
import PixelWars.GameLogic.MapLogic.MapTile;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class MapBuilder {

    private static final int MAP_WIDTH_RATIO = 3, MAP_HEIGHT_RATIO = 2;
    private static HashMap<String, Integer> mapSizeMultipliers;
    static {
        mapSizeMultipliers = new LinkedHashMap<>();
        mapSizeMultipliers.put("Tiny", 60);
        mapSizeMultipliers.put("Small", 70);
        mapSizeMultipliers.put("Medium", 80);
        mapSizeMultipliers.put("Large", 90);
        mapSizeMultipliers.put("Giant", 100);
    }

    public static String[] getMapSizes() {
        return mapSizeMultipliers.keySet().toArray(new String[0]);
    }

    public static Map createMap(String mapSize/*,String terrainType*/)
    {
        int multiplier=mapSizeMultipliers.get(mapSize);
        int width = MAP_WIDTH_RATIO*multiplier;
        int height = MAP_HEIGHT_RATIO*multiplier;
        MapTile[][] mapTilesMatrix = TerrainBuilder.createMapTilesMatrix(width,height);
        return new Map(mapTilesMatrix);
    }
}