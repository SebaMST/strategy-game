package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs.WatchTowerPH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ConcretePHs.StonePH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ConcretePHs.WoodPH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

import java.util.Arrays;
import java.util.List;

public class WatchTower extends Building {
    private static final BuildRequirements br;
    private static final List<ProductionHandler> productionHandlers;

    static {
        br=new BuildRequirements();
        br.addRequiredResource("StoneResourceBank",40);
        br.addRequiredResource("WoodResourceBank",60);
        productionHandlers= Arrays.asList(new StonePH(), new WoodPH());
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public static List<ProductionHandler> getProductionHandlers() { return productionHandlers; }

    public WatchTower(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "WatchTower";
    }

    @Override
    public void run() {
        if(!productionHandlers.isEmpty())
            while(getIsProductionThreadStarted())
            {
                try {
                    Thread.sleep(WatchTowerPH.getProductionCooldown());
                } catch (InterruptedException ignored) {
                }
                for(ProductionHandler ph: productionHandlers)
                {
                    ((WatchTowerPH)ph).requestProduction(this);
                }
                speak("Produced resources.");
            }
    }
}
