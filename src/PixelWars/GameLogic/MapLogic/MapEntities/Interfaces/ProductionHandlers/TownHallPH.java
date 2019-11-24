package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.TownHall;

public interface TownHallPH extends ProductionHandler {
	void requestProduction(TownHall who);
}
