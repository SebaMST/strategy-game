package PixelWars.GameLogic.MapLogic;

import PixelWars.GUI.Events.EventBroadcaster;
import PixelWars.GameLogic.MapLogic.MapEntities.MapEntity;
import PixelWars.GameLogic.MapLogic.TerrainCreation.Terrains.Terrain;

public class MapTile {
    private MapEntity mapEntity;
    private final Terrain terrain;

    public MapTile(Terrain terrain)
    {
        this.terrain=terrain;
    }

    public synchronized boolean isFree() {
        return mapEntity == null;
    }

    public synchronized void setMapEntity(MapEntity mapEntity) {
        this.mapEntity = mapEntity;
        eb.notifyEventCapturers();
    }

    public synchronized MapEntity getMapEntity() {
        return mapEntity;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public boolean isOperational() {
        return terrain.isOperational();
    }
    //Events
    private EventBroadcaster eb = new EventBroadcaster(this);

    public EventBroadcaster getEventBroadcaster()
    {
        return eb;
    }
}