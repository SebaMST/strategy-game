package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.MapEntity;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

public abstract class Building extends MapEntity {
    public static final String[] BUILDING_TYPES_WONDER = {"FoodResourceBank", "WoodResourceBank", "StoneResourceBank", "IronResourceBank", "GoldResourceBank"};
    private Player owner;
    public Building(Player owner) {
        this.owner=owner;
    }
    protected Player getOwner()
    {
        return owner;
    }
}