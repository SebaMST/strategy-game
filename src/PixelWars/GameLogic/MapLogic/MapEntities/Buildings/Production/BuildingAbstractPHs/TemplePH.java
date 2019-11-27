package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Temple;

public interface TemplePH extends ProductionHandler {
	void requestProduction(Temple who);
	static int getProductionCooldown()
	{
		return 9000;
	}
}
