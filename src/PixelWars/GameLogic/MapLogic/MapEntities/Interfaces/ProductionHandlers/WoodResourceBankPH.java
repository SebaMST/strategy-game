package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;

import PixelWars.GameLogic.MapLogic.MapEntities.Resources.WoodResourceBank;

public interface WoodResourceBankPH extends ProductionHandler {
    void requestProduction(WoodResourceBank who);
}
