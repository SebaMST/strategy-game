package PixelWars.GameLogic.MapLogic;

import PixelWars.GUI.Events.EventBroadcaster;
import PixelWars.GameLogic.MapLogic.MapCreation.TerrainCreation.Terrains.Terrain;
import PixelWars.GameLogic.MapLogic.MapEntities.MapEntity;

public class MapTile {
    private MapEntity mapEntity;
    private final Terrain terrain;

    public MapTile(Terrain terrain)
    {
        this.terrain=terrain;
    }

    public boolean hasMapEntity() {
        return mapEntity != null;
    }

    public synchronized void setMapEntity(MapEntity mapEntity) {
        this.mapEntity = mapEntity;
        eb.notifyEventCapturers();
    }

    public MapEntity getMapEntity() {
        return mapEntity;
    }
    public Terrain getTerrain() {
        return terrain;
    }

    //Events
    private EventBroadcaster eb = new EventBroadcaster(this);

    public EventBroadcaster getEventBroadcaster()
    {
        return eb;
    }
}