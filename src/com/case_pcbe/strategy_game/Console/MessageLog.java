package com.case_pcbe.strategy_game.Console;

import java.util.LinkedList;

public class MessageLog {
    private LinkedList<String> messages;

    public MessageLog() {
        messages = new LinkedList();
    }

    public String append(String Who, String What) {
        String message = Who + ": " + What;
        return this.messages.add(message) ? message : null;
    }

    public String toString() {
        String res = "";
        for (String m : messages) {
            res += m + "\n";
        }
        return res;
    }
}