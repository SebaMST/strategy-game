package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Stable;

public interface StablePH extends ProductionHandler {
	void requestProduction(Stable who);
}
