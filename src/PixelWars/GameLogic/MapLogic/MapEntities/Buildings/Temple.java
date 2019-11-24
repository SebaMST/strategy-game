package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.Producer;
import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

public class Temple extends Building {
    private static final BuildRequirements br;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("Church",3);
        br.addRequiredResource("FoodResourceBank",50);
        br.addRequiredResource("GoldResourceBank",100);
        br.addRequiredResource("StoneResourceBank",100);
        br.addRequiredResource("WoodResourceBank",100);
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public Temple(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "Temple";
    }

}
