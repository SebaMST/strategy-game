package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Granary;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;

public interface GranaryPH extends ProductionHandler {
	void requestProduction(Granary who);
	static int getProductionCooldown()
	{
		return 5000;
	}
}
