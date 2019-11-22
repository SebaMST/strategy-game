package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;

import PixelWars.GameLogic.MapLogic.MapEntities.Resources.StoneResourceBank;

public interface StoneResourceBankPH extends ProductionHandler{
    void requestProduction(StoneResourceBank who);
}
