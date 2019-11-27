package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs.LumberCampPH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ConcretePHs.WoodPH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

import java.util.Arrays;
import java.util.List;

public class LumberCamp extends Building {
    private static final BuildRequirements br;
    private static final List<ProductionHandler> productionHandlers;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("House",4);
        br.addRequiredResource("StoneResourceBank",40);
        br.addRequiredResource("WoodResourceBank",40);
        productionHandlers= Arrays.asList(new WoodPH());
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public static List<ProductionHandler> getProductionHandlers() { return productionHandlers; }

    public LumberCamp(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "LumberCamp";
    }

    @Override
    public void run() {
        if(!productionHandlers.isEmpty())
            while(getIsProductionThreadStarted())
            {
                try {
                    Thread.sleep(LumberCampPH.getProductionCooldown());
                } catch (InterruptedException ignored) {
                }
                for(ProductionHandler ph: productionHandlers)
                {
                    ((LumberCampPH)ph).requestProduction(this);
                }
                speak("Produced resources.");
            }
    }
}
