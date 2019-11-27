package PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ResourceBankAbstractPHs;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Resources.StoneResourceBank;

public interface StoneResourceBankPH extends ProductionHandler {
    void requestProduction(StoneResourceBank who);
}
