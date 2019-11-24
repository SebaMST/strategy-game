package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Observatory;

public interface ObservatoryPH extends ProductionHandler {
	void requestProduction(Observatory who);
}
