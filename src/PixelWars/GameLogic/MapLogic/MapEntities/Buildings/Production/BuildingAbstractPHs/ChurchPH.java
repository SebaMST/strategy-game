package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Church;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;

public interface ChurchPH extends ProductionHandler {
    void requestProduction(Church who);
    static int getProductionCooldown()
    {
        return 5000;
    }
}
