package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.Producer;
import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

public class Mine extends Building implements Producer {
    private static final BuildRequirements br;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("House",4);
        br.addRequiredResource("GoldResourceBank",30);
        br.addRequiredResource("IronResourceBank",50);
        br.addRequiredResource("StoneResourceBank",50);
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public Mine(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "Mine";
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
