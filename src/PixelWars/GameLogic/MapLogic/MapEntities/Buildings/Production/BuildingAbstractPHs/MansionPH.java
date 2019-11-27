package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Mansion;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;

public interface MansionPH extends ProductionHandler {
	void requestProduction(Mansion who);
	static int getProductionCooldown()
	{
		return 5000;
	}
}
