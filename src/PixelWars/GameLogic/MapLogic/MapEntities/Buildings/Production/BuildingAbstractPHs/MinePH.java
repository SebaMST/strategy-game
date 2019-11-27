package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Mine;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;

public interface MinePH extends ProductionHandler {
	void requestProduction(Mine who);
	static int getProductionCooldown()
	{
		return 6000;
	}
}
