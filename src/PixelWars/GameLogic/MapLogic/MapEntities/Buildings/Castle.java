package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Player;

public class Castle extends Building {
    private static final BuildRequirements br;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("Barracks",4);
        br.addRequiredBuilding("Stable",4);
        br.addRequiredResource("FoodResourceBank",150);
        br.addRequiredResource("GoldResourceBank",150);
        br.addRequiredResource("IronResourceBank",200);
        br.addRequiredResource("StoneResourceBank",200);
        br.addRequiredResource("WoodResourceBank",50);
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public Castle(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "Castle";
    }

}
