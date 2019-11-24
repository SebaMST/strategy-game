package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Barracks;

public interface BarracksPH extends ProductionHandler {
    void requestProduction(Barracks who);
}
