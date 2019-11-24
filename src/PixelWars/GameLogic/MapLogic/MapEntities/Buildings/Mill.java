package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.Producer;
import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

public class Mill extends Building implements Producer {
    private static final BuildRequirements br;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("House",2);
        br.addRequiredResource("StoneResourceBank",30);
        br.addRequiredResource("WoodResourceBank",30);
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public Mill(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "Mill";
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
