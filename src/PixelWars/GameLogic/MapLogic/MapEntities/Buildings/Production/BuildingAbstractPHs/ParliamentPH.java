package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Parliament;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;

public interface ParliamentPH extends ProductionHandler {
	void requestProduction(Parliament who);
	static int getProductionCooldown()
	{
		return 15000;
	}
}
