package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;

import PixelWars.GameLogic.MapLogic.MapEntities.Resources.IronResourceBank;

public interface IronResourceBankPH extends ProductionHandler {
    void requestProduction(IronResourceBank who);
}
