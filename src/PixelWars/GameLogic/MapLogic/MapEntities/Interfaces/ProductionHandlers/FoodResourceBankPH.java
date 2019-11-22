package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;

import PixelWars.GameLogic.MapLogic.MapEntities.Resources.FoodResourceBank;

public interface FoodResourceBankPH extends ProductionHandler {
    void requestProduction(FoodResourceBank who);
}
