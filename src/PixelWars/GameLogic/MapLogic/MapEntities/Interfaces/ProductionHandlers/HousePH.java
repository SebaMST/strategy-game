package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.House;

public interface HousePH extends ProductionHandler {
	void requestProduction(House who);
}
