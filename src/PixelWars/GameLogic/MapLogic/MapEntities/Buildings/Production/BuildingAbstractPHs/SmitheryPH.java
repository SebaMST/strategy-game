package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Smithery;

public interface SmitheryPH extends ProductionHandler {
	void requestProduction(Smithery who);
	static int getProductionCooldown()
	{
		return 10000;
	}
}
