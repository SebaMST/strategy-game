package com.case_pcbe.strategy_game.GameLogic;

import com.case_pcbe.strategy_game.Console.MessagingSystem;
import com.case_pcbe.strategy_game.GameLogic.MapLogic.Map;

import java.util.ArrayList;

public final class Game {
    public static Map MAP = new Map();
    public static ArrayList<Player> players = new ArrayList();

    static {
        speak("Welcome to the PIXEL Wars!");
    }

    public static void speak(String s) {
        MessagingSystem.chat("SYSTEM", s);
    }
}
