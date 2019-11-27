package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ResourceBankAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Resources.GoldResourceBank;

public interface GoldResourceBankPH extends ProductionHandler {
    void requestProduction(GoldResourceBank who);
}
