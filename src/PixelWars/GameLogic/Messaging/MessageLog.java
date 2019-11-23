package PixelWars.GameLogic.Messaging;

import PixelWars.GUI.Events.EventBroadcaster;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageLog{

    private final Queue<Message> messages;

    MessageLog() {
        messages = new ConcurrentLinkedQueue<>();
    }

    synchronized void addMessage(Message message) {
        synchronized (messages) {
            if(message.getSender() instanceof Player)
            System.out.println(((Player) message.getSender()).getColor()+" "+message.getContent());
            messages.add(message);
            eb.notifyEventCapturers();
        }
    }

    synchronized public Message lastMessage()
    {
        synchronized (messages) {
            return messages.remove();
        }
    }

    //Events
    private EventBroadcaster eb = new EventBroadcaster(this);

    public EventBroadcaster getEventBroadcaster()
    {
        return eb;
    }
}