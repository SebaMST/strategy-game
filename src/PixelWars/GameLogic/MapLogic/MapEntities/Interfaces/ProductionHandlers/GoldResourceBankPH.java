package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;


import PixelWars.GameLogic.MapLogic.MapEntities.Resources.GoldResourceBank;

public interface GoldResourceBankPH extends ProductionHandler {
    void requestProduction(GoldResourceBank who);
}
