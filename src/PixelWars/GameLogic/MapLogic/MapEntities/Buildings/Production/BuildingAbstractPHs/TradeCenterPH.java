package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.TradeCenter;

public interface TradeCenterPH extends ProductionHandler {
	void requestProduction(TradeCenter who);
	static int getProductionCooldown()
	{
		return 10000;
	}
}
