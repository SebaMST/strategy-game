package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ConcretePHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.*;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs.*;

public class WoodPH implements BarracksPH, LumberCampPH, StablePH, TownHallPH, UniversityPH, WatchTowerPH {
    private void completeProduction(Building building, int amount) {
        building.getOwner().updateResourceBar("WoodResourceBank",amount);
    }

    @Override
    public void requestProduction(Barracks who) {
        completeProduction(who,35);
    }

    @Override
    public void requestProduction(LumberCamp who) {
        completeProduction(who,30);
    }

    @Override
    public void requestProduction(Stable who) {
        completeProduction(who,35);
    }

    @Override
    public void requestProduction(TownHall who) {
        completeProduction(who,50);
    }

    @Override
    public void requestProduction(University who) {
        completeProduction(who,75);
    }

    @Override
    public void requestProduction(WatchTower who) {
        completeProduction(who,15);
    }
}
