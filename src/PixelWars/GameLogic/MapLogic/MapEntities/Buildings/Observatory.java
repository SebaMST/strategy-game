package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.Producer;
import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

public class Observatory extends Building implements Producer {
    private static final BuildRequirements br;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("University",3);
        br.addRequiredResource("GoldResourceBank",200);
        br.addRequiredResource("IronResourceBank",400);
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public Observatory(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "Observatory";
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
