package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Sphinx;

public interface SphinxPH extends ProductionHandler {
	void requestProduction(Sphinx who);
}
