package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Castle;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;

public interface CastlePH extends ProductionHandler {
    void requestProduction(Castle who);
    static int getProductionCooldown()
    {
        return 12000;
    }
}
