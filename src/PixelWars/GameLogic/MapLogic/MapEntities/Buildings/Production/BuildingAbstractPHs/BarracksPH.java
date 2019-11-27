package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Barracks;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;

public interface BarracksPH extends ProductionHandler {
    void requestProduction(Barracks who);
    static int getProductionCooldown()
    {
        return 10000;
    }
}
