package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Granary;

public interface GranaryPH extends ProductionHandler {
	void requestProduction(Granary who);
}
