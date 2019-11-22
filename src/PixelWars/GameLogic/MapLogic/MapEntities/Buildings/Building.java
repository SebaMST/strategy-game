package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.MapEntity;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

public abstract class Building extends MapEntity {
    private Player owner;
    public Building(Player owner) {
        this.owner=owner;
    }
    protected Player getOwner()
    {
        return owner;
    }
}