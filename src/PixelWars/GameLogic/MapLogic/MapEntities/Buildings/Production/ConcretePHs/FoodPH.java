package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ConcretePHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.*;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs.*;

public class FoodPH implements BarracksPH, CastlePH, GranaryPH, HospitalPH, HousePH, MansionPH, StablePH, TownHallPH, TradeCenterPH {
    private void completeProduction(Building building, int amount) {
        building.getOwner().updateResourceBar("FoodResourceBank",amount);
    }

    @Override
    public void requestProduction(Barracks who) {
        completeProduction(who,35);
    }

    @Override
    public void requestProduction(Castle who) {
        completeProduction(who,70);
    }

    @Override
    public void requestProduction(Granary who) {
        completeProduction(who,15);
    }

    @Override
    public void requestProduction(Hospital who) {
        completeProduction(who,70);
    }

    @Override
    public void requestProduction(House who) {
        completeProduction(who,5);
    }

    @Override
    public void requestProduction(Mansion who) {
        completeProduction(who,15);
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
    public void requestProduction(TradeCenter who) {
        completeProduction(who,40);
    }
}
