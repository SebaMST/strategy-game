package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.Producer;
import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

public class TownHall extends Building implements Producer {
    private static final BuildRequirements br;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("Church",1);
        br.addRequiredBuilding("Granary",2);
        br.addRequiredBuilding("LumberCamp",2);
        br.addRequiredBuilding("Mansion",3);
        br.addRequiredBuilding("Mill",2);
        br.addRequiredBuilding("Mine",2);
        br.addRequiredResource("FoodResourceBank",220);
        br.addRequiredResource("GoldResourceBank",100);
        br.addRequiredResource("StoneResourceBank",150);
        br.addRequiredResource("WoodResourceBank",120);
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public TownHall(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "TownHall";
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
