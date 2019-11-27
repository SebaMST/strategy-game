package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Mill;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;

public interface MillPH extends ProductionHandler {
	void requestProduction(Mill who);
	static int getProductionCooldown()
	{
		return 5000;
	}
}
