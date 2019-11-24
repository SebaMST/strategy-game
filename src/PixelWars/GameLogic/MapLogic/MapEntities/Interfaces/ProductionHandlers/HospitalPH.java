package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Hospital;

public interface HospitalPH extends ProductionHandler {
	void requestProduction(Hospital who);
}
