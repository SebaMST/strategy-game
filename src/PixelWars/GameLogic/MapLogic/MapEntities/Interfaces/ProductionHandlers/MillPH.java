package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Mill;

public interface MillPH extends ProductionHandler {
	void requestProduction(Mill who);
}
