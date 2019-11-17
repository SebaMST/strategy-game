package PixelWars.GameLogic;

import PixelWars.GameLogic.MapLogic.MapEntities.Player;
import PixelWars.GameLogic.MapLogic.MapTile;
import PixelWars.GameLogic.Messaging.MessagingSystem;
import PixelWars.GameLogic.MapLogic.Map;
import java.util.List;
import java.util.Random;

public final class Game {
    private Map map;
    private List<Player> players;

    public Game(List<Player> players, Map map) {
        this.players = players;
        this.map = map;
    }

    private static class Spawner {
        private static void spawn(Map map, List<Player> players) {
            MapTile[][] mapTiles = map.getMapTilesMatrix();
            int mapWidth = map.getWidth();
            int mapHeight = map.getHeight();

            Random r = new Random(System.currentTimeMillis());
            int posX, posY;
            for(Player p: players) {
                do {
                    posX = r.nextInt(mapWidth);
                    posY = r.nextInt(mapHeight);
                } while (mapTiles[posY][posX] == null || mapTiles[posY][posX].getMapEntity() != null);
                p.setPos(posX,posY);
                mapTiles[posY][posX].setMapEntity(p);
            }
        }
    }

    public void begin() {
        speak("Welcome to the PIXEL Wars!");
        speak( "Click BEGIN when you are ready.");
        speak("A number of " + players.size() + " emperors have joined the battlefield!");
        Spawner.spawn(map,players);
        for(Player p:players)
        {
            p.start();
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
