package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ResourceBankAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Resources.WoodResourceBank;

public interface WoodResourceBankPH extends ProductionHandler {
    void requestProduction(WoodResourceBank who);
}
