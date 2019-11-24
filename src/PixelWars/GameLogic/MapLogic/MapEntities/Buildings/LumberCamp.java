package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.Producer;
import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

public class LumberCamp extends Building implements Producer {
    private static final BuildRequirements br;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("House",4);
        br.addRequiredResource("StoneResourceBank",40);
        br.addRequiredResource("WoodResourceBank",40);
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public LumberCamp(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "LumberCamp";
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
