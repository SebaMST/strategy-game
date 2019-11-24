package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.Producer;
import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

public class Mosque extends Building {
    private static final BuildRequirements br;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("Parliament",1);
        br.addRequiredBuilding("Temple",5);
        br.addRequiredResource("FoodResourceBank",1200);
        br.addRequiredResource("GoldResourceBank",600);
        br.addRequiredResource("StoneResourceBank",650);
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public Mosque(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "Mosque";
    }

}
