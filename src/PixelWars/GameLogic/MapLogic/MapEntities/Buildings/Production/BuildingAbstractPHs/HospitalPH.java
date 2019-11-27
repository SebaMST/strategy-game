package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Hospital;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;

public interface HospitalPH extends ProductionHandler {
	void requestProduction(Hospital who);
	static int getProductionCooldown()
	{
		return 10000;
	}
}
