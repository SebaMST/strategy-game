package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Player;

public class Colosseum extends Building {
    private static final BuildRequirements br;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("Castle",4);
        br.addRequiredResource("GoldResourceBank",300);
        br.addRequiredResource("IronResourceBank",800);
        br.addRequiredResource("StoneResourceBank",1200);
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public Colosseum(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "Colosseum";
    }

}
