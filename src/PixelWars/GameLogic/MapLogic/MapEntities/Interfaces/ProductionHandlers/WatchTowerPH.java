package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.WatchTower;

public interface WatchTowerPH extends ProductionHandler {
	void requestProduction(WatchTower who);
}
