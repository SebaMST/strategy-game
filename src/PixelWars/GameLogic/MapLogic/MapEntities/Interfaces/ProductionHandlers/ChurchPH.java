package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Church;

public interface ChurchPH extends ProductionHandler {
    void requestProduction(Church who);
}
