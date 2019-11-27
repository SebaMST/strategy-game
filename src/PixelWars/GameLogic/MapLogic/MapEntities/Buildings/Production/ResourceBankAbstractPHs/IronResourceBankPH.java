package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ResourceBankAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Resources.IronResourceBank;

public interface IronResourceBankPH extends ProductionHandler {
    void requestProduction(IronResourceBank who);
}
