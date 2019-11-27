package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.WatchTower;

public interface WatchTowerPH extends ProductionHandler {
	void requestProduction(WatchTower who);
	static int getProductionCooldown()
	{
		return 5000;
	}
}
