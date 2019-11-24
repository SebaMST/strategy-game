package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Colosseum;

public interface ColosseumPH extends ProductionHandler {
	void requestProduction(Colosseum who);
}
