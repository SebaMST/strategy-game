package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.Producer;
import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

public class Parliament extends Building{
    private static final BuildRequirements br;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("Hospital",3);
        br.addRequiredBuilding("TradeCenter",3);
        br.addRequiredBuilding("University",3);
        br.addRequiredResource("FoodResourceBank",400);
        br.addRequiredResource("GoldResourceBank",300);
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public Parliament(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "Parliament";
    }

}
