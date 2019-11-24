package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.Producer;
import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

public class Barracks extends Building implements Producer {
    private static final BuildRequirements br;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("Smithery",2);
        br.addRequiredBuilding("TownHall",1);
        br.addRequiredBuilding("WatchTower",4);
        br.addRequiredResource("FoodResourceBank",150);
        br.addRequiredResource("GoldResourceBank",80);
        br.addRequiredResource("IronResourceBank",150);
        br.addRequiredResource("WoodResourceBank",150);
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public Barracks(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "Barracks";
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
