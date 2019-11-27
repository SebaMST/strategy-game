package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ConcretePHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.*;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs.*;

public class StonePH implements CastlePH, MillPH, MinePH, ParliamentPH, TemplePH, TownHallPH, WatchTowerPH {
    private void completeProduction(Building building, int amount) {
        building.getOwner().updateResourceBar("StoneResourceBank",amount);
    }

    @Override
    public void requestProduction(Castle who) {
        completeProduction(who,75);
    }

    @Override
    public void requestProduction(Mill who) {
        completeProduction(who,20);
    }

    @Override
    public void requestProduction(Mine who) {
        completeProduction(who,30);
    }

    @Override
    public void requestProduction(Parliament who) {
        completeProduction(who, 100);
    }

    @Override
    public void requestProduction(Temple who) {
        completeProduction(who,30);
    }

    @Override
    public void requestProduction(TownHall who) {
        completeProduction(who,50);
    }

    @Override
    public void requestProduction(WatchTower who) {
        completeProduction(who,15);
    }
}
