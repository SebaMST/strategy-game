package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces;

import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers.ProductionHandler;

public interface Producer extends Runnable{
    void produce(ProductionHandler productionHandler);
    ProductionHandler[] getProductionHandlers();
}
