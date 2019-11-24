package PixelWars.GameLogic.MapLogic;

import PixelWars.GameLogic.Factory.ResourceFactory;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;
import PixelWars.GameLogic.MapLogic.MapEntities.Resources.ResourceBank;
import PixelWars.GameLogic.MapLogic.TerrainCreation.TerrainBuilder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class MapBuilder {

    private static final int MAP_WIDTH_RATIO = 3, MAP_HEIGHT_RATIO = 2;
    private static final HashMap<String, Integer> MAPSIZE_MULTIPLIERS = new LinkedHashMap<>();
    static {
        MAPSIZE_MULTIPLIERS.put("Tiny", 60);
        MAPSIZE_MULTIPLIERS.put("Small", 70);
        MAPSIZE_MULTIPLIERS.put("Medium", 80);
        MAPSIZE_MULTIPLIERS.put("Large", 90);
        MAPSIZE_MULTIPLIERS.put("Giant", 100);
    }
    private static final HashMap<String,Double> RESOURCEDENSITY_PERCENTS = new LinkedHashMap<>();
    static {
        RESOURCEDENSITY_PERCENTS.put("Starvation", 0.02D);
        RESOURCEDENSITY_PERCENTS.put("Moderate", 0.03D);
        RESOURCEDENSITY_PERCENTS.put("Richness", 0.04D);
    }

    public static String[] getMapSizes() {
        return MAPSIZE_MULTIPLIERS.keySet().toArray(new String[0]);
    }
    public static String[] getResourceDensities() { return RESOURCEDENSITY_PERCENTS.keySet().toArray(new String[0]); }

    private static class Spawner {
        private final Map where;

        private Spawner(Map where)
        {
            this.where=where;
        }

        private void spawnPlayers(List<Player> players) {
            for(Player p: players) {
                p.setMap(where);
            }
        }

        private void spawnResources(String resDensity) {
            int mapWidth = where.getWidth();
            int mapHeight = where.getHeight();
            int mapArea = mapWidth*mapHeight;
            double percent = (200*300.)/(mapArea)*RESOURCEDENSITY_PERCENTS.get(resDensity);
            int resourceNr = (int) (percent*mapArea);
            int resourceNrOfAType = resourceNr/ ResourceFactory.getResourceTypes().length;

            ResourceFactory rf = new ResourceFactory();
            for (String resource : ResourceFactory.getResourceTypes()) {
                for(int i=0;i<resourceNrOfAType;i++) {
                    ResourceBank rb = rf.create(resource);
                    rb.setMap(where);
                }
            }
        }
    }

    public static Map createMap(String mapSize, String resDensity, List<Player> players/*,String terrainType*/)
    {
        //Building the map object
        int multiplier= MAPSIZE_MULTIPLIERS.get(mapSize);
        int width = MAP_WIDTH_RATIO*multiplier;
        int height = MAP_HEIGHT_RATIO*multiplier;
        MapTile[][] mapTilesMatrix = TerrainBuilder.createMapTilesMatrix(width,height);
        Map map =  new Map(mapTilesMatrix);

        //Populating the map object with its initial entities
        Spawner spawner = new Spawner(map);
        spawner.spawnResources(resDensity);
        spawner.spawnPlayers(players);

        return map;
    }
}