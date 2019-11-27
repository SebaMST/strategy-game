package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Stable;

public interface StablePH extends ProductionHandler {
	void requestProduction(Stable who);
	static int getProductionCooldown()
	{
		return 10000;
	}
}
