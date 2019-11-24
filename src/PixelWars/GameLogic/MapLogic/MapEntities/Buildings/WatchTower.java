package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.Producer;
import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

public class WatchTower extends Building {
    private static final BuildRequirements br;

    static {
        br=new BuildRequirements();
        br.addRequiredResource("StoneResourceBank",40);
        br.addRequiredResource("WoodResourceBank",60);
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public WatchTower(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "WatchTower";
    }

}
