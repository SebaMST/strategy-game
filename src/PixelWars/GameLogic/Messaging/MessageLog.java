package PixelWars.GameLogic.Messaging;

import PixelWars.GUI.Events.EventBroadcaster;

import java.util.LinkedList;

public class MessageLog{

    private LinkedList<String> messages;

    public MessageLog() {
        messages = new LinkedList<>();
    }

    public synchronized void addMessage(String Who, String What) {
        String message = Who + ": " + What;
        messages.add(message);
        eb.notifyEventCapturers();
    }

    public void clearMessages()
    {
        messages.clear();
    }

    public String lastMessage()
    {
        return messages.isEmpty()?null:messages.getLast();
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        for (String m : messages) {
            res.append(m).append("\n");
        }
        return res.toString();
    }

    //Events
    private EventBroadcaster eb = new EventBroadcaster(this);

    public EventBroadcaster getEventBroadcaster()
    {
        return eb;
    }
}