package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.Producer;
import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

public class Church extends Building implements Producer {
    private static final BuildRequirements br;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("House",4);
        br.addRequiredBuilding("Mansion",2);
        br.addRequiredResource("GoldResourceBank",40);
        br.addRequiredResource("StoneResourceBank",80);
        br.addRequiredResource("WoodResourceBank",40);
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public Church(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "Church";
    }

    @Override
    public void produce(ProductionHandler productionHandler) {
    }

    @Override
    public ProductionHandler[] getProductionHandlers() {
        return new ProductionHandler[0];
    }

    @Override
    public void run() {

    }
}
