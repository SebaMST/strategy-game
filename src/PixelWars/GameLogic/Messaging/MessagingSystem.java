package PixelWars.GameLogic.Messaging;

public final class MessagingSystem {
    private static final MessageLog GLOBAL_LOG = new MessageLog();

    public static void chat(String name, String s) {
        GLOBAL_LOG.addMessage(name, s);
    }

    public static void reset()
    {
        GLOBAL_LOG.clearMessages();
    }

    public static MessageLog getGlobalLog() {
        return GLOBAL_LOG;
    }
}
