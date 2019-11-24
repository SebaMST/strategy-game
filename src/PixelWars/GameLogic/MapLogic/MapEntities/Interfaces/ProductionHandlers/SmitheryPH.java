package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Smithery;

public interface SmitheryPH extends ProductionHandler {
	void requestProduction(Smithery who);
}
