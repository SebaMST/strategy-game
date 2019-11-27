package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Sphinx;

public interface SphinxPH extends ProductionHandler {
	void requestProduction(Sphinx who);
	static int getProductionCooldown()
	{
		return 0;
	}
}
