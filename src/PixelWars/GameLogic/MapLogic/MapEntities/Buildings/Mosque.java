package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs.MosquePH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

import java.util.Arrays;
import java.util.List;

public class Mosque extends Building {
    private static final BuildRequirements br;
    private static final List<ProductionHandler> productionHandlers;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("Parliament",1);
        br.addRequiredBuilding("Temple",5);
        br.addRequiredResource("FoodResourceBank",1200);
        br.addRequiredResource("GoldResourceBank",600);
        br.addRequiredResource("StoneResourceBank",650);
        productionHandlers= Arrays.asList();
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public static List<ProductionHandler> getProductionHandlers() { return productionHandlers; }

    public Mosque(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "Mosque";
    }

    @Override
    public void run() {
        if(!productionHandlers.isEmpty())
            while(getIsProductionThreadStarted())
            {
                try {
                    Thread.sleep(MosquePH.getProductionCooldown());
                } catch (InterruptedException ignored) {
                }
                for(ProductionHandler ph: productionHandlers)
                {
                    ((MosquePH)ph).requestProduction(this);
                }
                speak("Produced resources.");
            }
    }
}
