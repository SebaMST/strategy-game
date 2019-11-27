package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.LumberCamp;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;

public interface LumberCampPH extends ProductionHandler {
    void requestProduction(LumberCamp who);
    static int getProductionCooldown()
    {
        return 5000;
    }
}
