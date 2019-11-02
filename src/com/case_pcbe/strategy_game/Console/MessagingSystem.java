package com.case_pcbe.strategy_game.Console;

public final class MessagingSystem {
    public static final MessageLog MESSAGE_LOG = new MessageLog();

    public static void chat(String name, String s) {
        MESSAGE_LOG.append(name, s);
    }
}
