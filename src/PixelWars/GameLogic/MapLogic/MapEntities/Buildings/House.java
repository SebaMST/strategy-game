package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.Producer;
import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

public class House extends Building {
    private static final BuildRequirements br;

    static {
        br=new BuildRequirements();
        br.addRequiredResource("FoodResourceBank",30);
        br.addRequiredResource("WoodResourceBank",30);
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public House(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "House";
    }

}
