package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ConcretePHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.*;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs.*;

public class GoldPH implements ChurchPH, HospitalPH, MinePH, ParliamentPH, TemplePH, TownHallPH, TradeCenterPH, UniversityPH {
    private void completeProduction(Building building, int amount) {
        building.getOwner().updateResourceBar("GoldResourceBank",amount);
    }

    @Override
    public void requestProduction(Church who) {
        completeProduction(who,20);
    }

    @Override
    public void requestProduction(Hospital who) {
        completeProduction(who,75);
    }

    @Override
    public void requestProduction(Mine who) {
        completeProduction(who,10);
    }

    @Override
    public void requestProduction(Parliament who) {
        completeProduction(who,90);
    }

    @Override
    public void requestProduction(Temple who) {
        completeProduction(who,35);
    }

    @Override
    public void requestProduction(TownHall who) {
        completeProduction(who,50);
    }

    @Override
    public void requestProduction(TradeCenter who) {
        completeProduction(who,65);
    }

    @Override
    public void requestProduction(University who) {
        completeProduction(who,50);
    }
}
