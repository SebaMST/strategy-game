package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Mansion;

public interface MansionPH extends ProductionHandler {
	void requestProduction(Mansion who);
}
