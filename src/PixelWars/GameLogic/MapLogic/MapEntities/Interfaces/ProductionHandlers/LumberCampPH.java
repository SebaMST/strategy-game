package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.LumberCamp;

public interface LumberCampPH extends ProductionHandler {
    void requestProduction(LumberCamp who);
}
