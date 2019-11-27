package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Observatory;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;

public interface ObservatoryPH extends ProductionHandler {
	void requestProduction(Observatory who);
	static int getProductionCooldown()
	{
		return 0;
	}
}
