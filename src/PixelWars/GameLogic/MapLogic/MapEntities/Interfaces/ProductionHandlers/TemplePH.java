package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Temple;

public interface TemplePH extends ProductionHandler {
	void requestProduction(Temple who);
}
