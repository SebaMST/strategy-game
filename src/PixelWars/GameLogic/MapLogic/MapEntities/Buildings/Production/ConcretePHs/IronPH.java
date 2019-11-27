package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ConcretePHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.*;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs.*;

public class IronPH implements BarracksPH, CastlePH, MinePH, SmitheryPH, StablePH, TownHallPH {
    private void completeProduction(Building building, int amount) {
        building.getOwner().updateResourceBar("IronResourceBank",amount);
    }

    @Override
    public void requestProduction(Barracks who) {
        completeProduction(who,50);
    }

    @Override
    public void requestProduction(Castle who) {
        completeProduction(who,75);
    }

    @Override
    public void requestProduction(Mine who) {
        completeProduction(who,25);
    }

    @Override
    public void requestProduction(Smithery who) {
        completeProduction(who,50);
    }

    @Override
    public void requestProduction(Stable who) {
        completeProduction(who,50);
    }

    @Override
    public void requestProduction(TownHall who) {
        completeProduction(who,50);
    }
}
