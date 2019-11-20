package PixelWars.GUI.Events;

import PixelWars.GUI.Events.Capturers.Capturer_TextField;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

import java.util.ArrayList;
import java.util.List;

public class EventBroadcaster {
    private Object cause;
    private List<EventCapturer> eventCapturerList;

    public EventBroadcaster(Object cause) {
        this.cause=cause;
        eventCapturerList = new ArrayList<>();
    }

    public void addEventCapturer(EventCapturer ec)
    {
        eventCapturerList.add(ec);
        if(!cause.toString().equals(""))
        notifyEventCapturers();
    }

    public void removeEventCapturer(EventCapturer ec)
    {
        eventCapturerList.remove(ec);
    }

    public void clearEventCapturerList()
    {
        eventCapturerList.clear();
    }

    public void notifyEventCapturers()
    {
        for(EventCapturer ec: eventCapturerList)
        {
            ec.update(cause);
        }
    }
}