package PixelWars.GameLogic.MapLogic;

public abstract class MapTile {
    public abstract boolean isOperational();
}

class NonoperationalMapTile extends MapTile {

    public boolean isOperational() {
        return false;
    }
}

class OperationalMapTile extends MapTile {
    private MapEntity entity;

    public boolean isOperational() {
        return true;
    }

    public boolean hasEntity() {
        return this.entity != null;
    }

    public void setEntity(MapEntity entity) {
        this.entity = entity;
    }

    public MapEntity getEntity() {
        return this.entity;
    }
}