package PixelWars.GameLogic.Messaging;

public class Message {
    private final GlobalSpeaker sender;
    private final String content;
    Message(GlobalSpeaker sender, String content)
    {
        this.sender=sender;
        this.content=content;
    }
    public GlobalSpeaker getSender()
    {
        return sender;
    }
    public String getContent()
    {
        return content;
    }
}
