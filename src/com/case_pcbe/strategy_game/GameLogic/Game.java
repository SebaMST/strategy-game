package com.case_pcbe.strategy_game.GameLogic;

import com.case_pcbe.strategy_game.Console.MessagingSystem;
import com.case_pcbe.strategy_game.GameLogic.MapLogic.Map;

import java.util.ArrayList;

public final class Game {
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
}
