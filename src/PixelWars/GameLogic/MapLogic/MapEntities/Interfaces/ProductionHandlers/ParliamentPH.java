package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Parliament;

public interface ParliamentPH extends ProductionHandler {
	void requestProduction(Parliament who);
}
