package com.case_pcbe.strategy_game.GameLogic;

import com.case_pcbe.strategy_game.GameLogic.Messaging.MessageLog;
import com.case_pcbe.strategy_game.GameLogic.Messaging.MessagingSystem;

public class Player {
    private static final String[] playersColors = new String[]{"Red", "Orange", "Yellow", "Brown", "Green", "Cyan", "Blue", "Purple"};
    private String name;
    private String color;
    private MessageLog playerLog;

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
        this.playerLog = MessagingSystem.MESSAGE_LOG;
    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    public void speak(String s) {
        MessagingSystem.chat(this.name + " (" + color + ")", s);
    }

    public static String[] getPlayersColors() {
        return playersColors;
    }
}