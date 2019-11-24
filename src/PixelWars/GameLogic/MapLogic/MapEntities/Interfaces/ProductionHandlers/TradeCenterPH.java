package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.TradeCenter;

public interface TradeCenterPH extends ProductionHandler {
	void requestProduction(TradeCenter who);
}
