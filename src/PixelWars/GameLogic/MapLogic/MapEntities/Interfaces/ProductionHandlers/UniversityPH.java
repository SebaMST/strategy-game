package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.University;

public interface UniversityPH extends ProductionHandler {
	void requestProduction(University who);
}
