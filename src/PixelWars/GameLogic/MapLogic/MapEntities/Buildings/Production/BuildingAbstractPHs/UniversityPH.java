package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.University;

public interface UniversityPH extends ProductionHandler {
	void requestProduction(University who);
	static int getProductionCooldown()
	{
		return 10000;
	}
}
