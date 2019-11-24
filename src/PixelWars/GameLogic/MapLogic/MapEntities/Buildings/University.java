package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.Producer;
import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

public class University extends Building {
    private static final BuildRequirements br;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("TownHall",1);
        br.addRequiredResource("FoodResourceBank",100);
        br.addRequiredResource("GoldResourceBank",100);
        br.addRequiredResource("WoodResourceBank",400);
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public University(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "University";
    }

}
