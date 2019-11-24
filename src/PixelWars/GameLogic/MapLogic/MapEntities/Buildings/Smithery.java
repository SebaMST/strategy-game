package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.Producer;
import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

public class Smithery extends Building implements Producer {
    private static final BuildRequirements br;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("Mine",2);
        br.addRequiredResource("GoldResourceBank",50);
        br.addRequiredResource("IronResourceBank",110);
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public Smithery(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "Smithery";
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
