package PixelWars.GameLogic.Messaging;

import PixelWars.GUI.Events.EventBroadcaster;
import jdk.nashorn.internal.objects.Global;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageLog{

    private final Queue<Message> messages;

    MessageLog() {
        messages = new ConcurrentLinkedQueue<>();
    }

    void addMessage(Message message) {
            messages.add(message);
            eb.notifyEventCapturers();
    }

    void clearMessages()
    {
        messages.clear();
    }

    public Message lastMessage()
    {
            return messages.remove();
    }

    //Events
    private EventBroadcaster eb = new EventBroadcaster(this);

    public EventBroadcaster getEventBroadcaster()
    {
        return eb;
    }
}