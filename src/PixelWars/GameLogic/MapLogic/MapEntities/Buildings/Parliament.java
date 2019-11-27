package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs.ParliamentPH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ConcretePHs.*;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

import java.util.Arrays;
import java.util.List;

public class Parliament extends Building{
    private static final BuildRequirements br;
    private static final List<ProductionHandler> productionHandlers;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("Hospital",3);
        br.addRequiredBuilding("TradeCenter",3);
        br.addRequiredBuilding("University",3);
        br.addRequiredResource("FoodResourceBank",400);
        br.addRequiredResource("GoldResourceBank",300);
        productionHandlers= Arrays.asList(new GoldPH(), new StonePH());
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public static List<ProductionHandler> getProductionHandlers() { return productionHandlers; }

    public Parliament(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "Parliament";
    }

    @Override
    public void run() {
        if(!productionHandlers.isEmpty())
            while(getIsProductionThreadStarted())
            {
                try {
                    Thread.sleep(ParliamentPH.getProductionCooldown());
                } catch (InterruptedException ignored) {
                }
                for(ProductionHandler ph: productionHandlers)
                {
                    ((ParliamentPH)ph).requestProduction(this);
                }
                speak("Produced resources.");
            }
    }

}
