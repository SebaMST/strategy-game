package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Mine;

public interface MinePH extends ProductionHandler {
	void requestProduction(Mine who);
}
