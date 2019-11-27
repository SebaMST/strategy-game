package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.TownHall;

public interface TownHallPH extends ProductionHandler {
	void requestProduction(TownHall who);
	static int getProductionCooldown()
	{
		return 10000;
	}
}
