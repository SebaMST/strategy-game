package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.Producer;
import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

public class Sphinx extends Building{
    private static final BuildRequirements br;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("Castle",2);
        br.addRequiredBuilding("Observatory",2);
        br.addRequiredResource("GoldResourceBank",450);
        br.addRequiredResource("StoneResourceBank",1600);
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public Sphinx(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "Sphinx";
    }

}
