package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ResourceBankAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Resources.FoodResourceBank;

public interface FoodResourceBankPH extends ProductionHandler {
    void requestProduction(FoodResourceBank who);
}
