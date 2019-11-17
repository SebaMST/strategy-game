package PixelWars.GameLogic.MapLogic;

import PixelWars.GUI.Events.EventBroadcaster;
import PixelWars.GameLogic.MapLogic.MapEntities.MapEntity;

public class MapTile {
    private MapEntity mapEntity;

    public boolean hasMapEntity() {
        return mapEntity != null;
    }

    public void setMapEntity(MapEntity mapEntity) {
        this.mapEntity = mapEntity;
        eb.notifyEventCapturers();
    }

    public MapEntity getMapEntity() {
        return mapEntity;
    }

    //Events
    private EventBroadcaster eb = new EventBroadcaster(this);

    public EventBroadcaster getEventBroadcaster()
    {
        return eb;
    }
}