package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs.StablePH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ConcretePHs.*;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

import java.util.Arrays;
import java.util.List;

public class Stable extends Building {
    private static final BuildRequirements br;
    private static final List<ProductionHandler> productionHandlers;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("Smithery",2);
        br.addRequiredBuilding("TownHall",1);
        br.addRequiredBuilding("WatchTower",4);
        br.addRequiredResource("FoodResourceBank",150);
        br.addRequiredResource("GoldResourceBank",80);
        br.addRequiredResource("IronResourceBank",150);
        br.addRequiredResource("WoodResourceBank",150);
        productionHandlers= Arrays.asList(new FoodPH(), new IronPH(), new WoodPH());
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public static List<ProductionHandler> getProductionHandlers() { return productionHandlers; }

    public Stable(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "Stable";
    }

    @Override
    public void run() {
        if(!productionHandlers.isEmpty())
            while(getIsProductionThreadStarted())
            {
                try {
                    Thread.sleep(StablePH.getProductionCooldown());
                } catch (InterruptedException ignored) {
                }
                for(ProductionHandler ph: productionHandlers)
                {
                    ((StablePH)ph).requestProduction(this);
                }
                speak("Produced resources.");
            }
    }
}
