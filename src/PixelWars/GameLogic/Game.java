package PixelWars.GameLogic;

import PixelWars.GameLogic.MapLogic.MapCreation.MapBuilder;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;
import PixelWars.GameLogic.MapLogic.MapEntities.Resources.Resource;
import PixelWars.GameLogic.MapLogic.MapTile;
import PixelWars.GameLogic.Messaging.MessagingSystem;
import PixelWars.GameLogic.MapLogic.MapCreation.Map;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

public final class Game {
    private Map map;
    private List<Player> players;

    public Game(List<Player> players,String mapSize, String resDensity) {
        this.players = players;
        this.map = MapBuilder.createMap(mapSize);
        speak("Welcome to the PIXEL Wars!");
        speak( "Click BEGIN when you are ready.");
        speak("A number of " + players.size() + " emperors have joined the battlefield!");
        Spawner.spawnPlayers(this.map,this.players);
        Spawner.spawnResources(this.map,resDensity);
    }

    public static class Spawner {
        private static void spawnPlayers(Map where, List<Player> players) {
            MapTile[][] mapTiles = where.getMapTilesMatrix();
            int mapWidth = where.getWidth();
            int mapHeight = where.getHeight();

            Random r = new Random(System.currentTimeMillis());
            int posX, posY;
            for(Player p: players) {
                do {
                    posX = r.nextInt(mapWidth);
                    posY = r.nextInt(mapHeight);
                } while (!mapTiles[posY][posX].getTerrain().isOperational()|| mapTiles[posY][posX].getMapEntity() != null);
                p.setMap(where);
                p.setPos(posX,posY);
            }
        }

        public static final HashMap<String,Double> RESOURCENR_PERCENTS = new LinkedHashMap<>();
        static {
            RESOURCENR_PERCENTS.put("Starvation", 0.01D);
            RESOURCENR_PERCENTS.put("Moderate", 0.02D);
            RESOURCENR_PERCENTS.put("Richness", 0.03D);
        }

        private static void spawnResources(Map where,String resDensity) {
            int mapWidth = where.getWidth();
            int mapHeight = where.getHeight();
            int mapArea = mapWidth*mapHeight;
            double percent = (200*300.)/(mapArea)*RESOURCENR_PERCENTS.get(resDensity);
            MapTile[][] mapTilesMatrix = where.getMapTilesMatrix();
            int resourceNr = (int) (percent*mapArea);
            int resourceNrOfAType = resourceNr/Resource.RESOURCE_TYPES.length;
            Random r = new Random();
            int posX, posY;
            try {
                for (String resource : Resource.RESOURCE_TYPES) {
                    Constructor c = Class.forName("PixelWars.GameLogic.MapLogic.MapEntities.Resources."+resource).getDeclaredConstructor(int.class,int.class);
                    for (int i = 0; i < resourceNrOfAType; i++) {
                        do {
                            posX = r.nextInt(mapWidth);
                            posY = r.nextInt(mapHeight);
                        } while (mapTilesMatrix[posY][posX] == null || mapTilesMatrix[posY][posX].getMapEntity() != null);
                        Resource res = (Resource) c.newInstance(posX,posY);
                        mapTilesMatrix[posY][posX].setMapEntity(res);
                    }
                }
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void begin() {
        for (Player player : players) {
            player.start();
        }
    }

    public void stop() {
        for(Player p:players)
        {
            p.stop();
        }
    }

    private static void speak(String s) {
        MessagingSystem.chat("SYSTEM", s);
    }

    public Map getMap() {
        return map;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public static int getPlayersNrMin() {
        return 2;
    }

    public static int getPlayersNrMax() {
        return 8;
    }
}
