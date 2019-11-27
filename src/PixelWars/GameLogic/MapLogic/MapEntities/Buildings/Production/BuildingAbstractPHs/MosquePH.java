package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Mosque;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;

public interface MosquePH extends ProductionHandler {
	void requestProduction(Mosque who);
	static int getProductionCooldown()
	{
		return 0;
	}
}
