package com.case_pcbe.strategy_game.GameLogic;

import com.case_pcbe.strategy_game.GameLogic.Messaging.MessageLog;
import com.case_pcbe.strategy_game.GameLogic.Messaging.MessagingSystem;
import javafx.scene.paint.Color;

public class Player {
    private String name;
    private Color color;
    private MessageLog playerLog;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.playerLog = MessagingSystem.MESSAGE_LOG;
    }

    public String getName() {
        return this.name;
    }

    public Color getColor() {
        return this.color;
    }

    public void speak(String s) {
        MessagingSystem.chat(this.name, s);
    }
}