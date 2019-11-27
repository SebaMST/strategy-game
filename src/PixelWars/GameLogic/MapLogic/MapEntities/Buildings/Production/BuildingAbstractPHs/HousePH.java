package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.House;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;

public interface HousePH extends ProductionHandler {
	void requestProduction(House who);
	static int getProductionCooldown()
	{
		return 4000;
	}
}
