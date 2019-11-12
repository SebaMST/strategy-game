package PixelWars.GameLogic;

import PixelWars.GameLogic.Messaging.MessagingSystem;
import PixelWars.GameLogic.MapLogic.Map;
import java.util.ArrayList;

public final class Game {
    private static int PLAYERS_NR_MIN = 2, PLAYERS_NR_MAX = 8;
    private Map map;
    private ArrayList<Player> players;

    public Game(ArrayList<Player> players, Map map) {
        this.players = players;
        this.map = map;
        speak("Welcome to the PIXEL Wars!");
        for (Player p : players) {
            p.speak("I have joined the Game!");
        }
    }

    public static void speak(String s) {
        MessagingSystem.chat("SYSTEM", s);
    }

    public Map getMap() {
        return map;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public static int getPlayersNrMin() {
        return PLAYERS_NR_MIN;
    }

    public static int getPlayersNrMax() {
        return PLAYERS_NR_MAX;
    }
}
