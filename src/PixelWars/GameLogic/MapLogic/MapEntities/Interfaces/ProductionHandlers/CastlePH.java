package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Castle;

public interface CastlePH extends ProductionHandler {
    void requestProduction(Castle who);
}
