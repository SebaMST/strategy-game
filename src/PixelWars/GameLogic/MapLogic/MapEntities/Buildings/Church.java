package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs.ChurchPH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ConcretePHs.GoldPH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

import java.util.Arrays;
import java.util.List;

public class Church extends Building {
    private static final BuildRequirements br;
    private static final List<ProductionHandler> productionHandlers;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("House",4);
        br.addRequiredBuilding("Mansion",2);
        br.addRequiredResource("GoldResourceBank",40);
        br.addRequiredResource("StoneResourceBank",80);
        br.addRequiredResource("WoodResourceBank",40);
        productionHandlers= Arrays.asList(new GoldPH());
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public static List<ProductionHandler> getProductionHandlers() { return productionHandlers; }

    public Church(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "Church";
    }

    @Override
    public void run() {
        if(!productionHandlers.isEmpty())
            while(getIsProductionThreadStarted())
            {
                try {
                    Thread.sleep(ChurchPH.getProductionCooldown());
                } catch (InterruptedException ignored) {
                }
                for(ProductionHandler ph: productionHandlers)
                {
                    ((ChurchPH)ph).requestProduction(this);
                }
                speak("Produced resources.");
            }
    }
}
