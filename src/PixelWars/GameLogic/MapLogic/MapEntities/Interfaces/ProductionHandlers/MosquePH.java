package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Mosque;

public interface MosquePH extends ProductionHandler {
	void requestProduction(Mosque who);
}
