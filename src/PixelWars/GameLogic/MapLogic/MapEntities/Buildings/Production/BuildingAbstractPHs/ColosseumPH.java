package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Colosseum;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;

public interface ColosseumPH extends ProductionHandler {
	void requestProduction(Colosseum who);
	static int getProductionCooldown()
	{
		return 0;
	}
}
