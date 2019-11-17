package PixelWars.GUI.Events;

import java.util.ArrayList;
import java.util.List;

public class EventBroadcaster {
    private Object cause;
    private List<EventCapturer> eventCapturerList;

    public EventBroadcaster(Object cause) {
        this.cause=cause;
        eventCapturerList = new ArrayList<>();
    }

    public boolean addEventCapturer(EventCapturer ec)
    {
        return eventCapturerList.add(ec);
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