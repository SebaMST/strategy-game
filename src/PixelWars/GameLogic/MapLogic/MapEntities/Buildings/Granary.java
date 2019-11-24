package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.Producer;
import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

public class Granary extends Building implements Producer {
    private static final BuildRequirements br;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("House",3);
        br.addRequiredResource("FoodResourceBank",40);
        br.addRequiredResource("WoodResourceBank",40);
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public Granary(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "Granary";
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
