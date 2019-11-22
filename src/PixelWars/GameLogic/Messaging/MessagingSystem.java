package PixelWars.GameLogic.Messaging;

public final class MessagingSystem {
    private static final MessageLog GLOBAL_LOG = new MessageLog();

    static synchronized void chat(GlobalSpeaker sender, String content) {
        GLOBAL_LOG.addMessage(new Message(sender,content));
    }

    public static void reset()
    {
        GLOBAL_LOG.clearMessages();
    }

    public static MessageLog getGlobalLog() {
        return GLOBAL_LOG;
    }

    public static final String[] BEGIN_THREAT_MESSAGES = new String[]{"I'm going to spank you until time and space have no meaning!", "All resources on this battlefield will be mine!", "Challenge me and i will send you to the nothingness!", "The god of death demands its pay!", "You will be obliterated them from history!", "Please don't hurt me, i'm neutral... for now", "This is where we fight! This is where they die!", "I will defend my fortress at any cost!"};
}
