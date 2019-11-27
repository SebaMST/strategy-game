package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs.HousePH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ConcretePHs.FoodPH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

import java.util.Arrays;
import java.util.List;

public class House extends Building {
    private static final BuildRequirements br;
    private static final List<ProductionHandler> productionHandlers;

    static {
        br=new BuildRequirements();
        br.addRequiredResource("FoodResourceBank",30);
        br.addRequiredResource("WoodResourceBank",30);
        productionHandlers= Arrays.asList(new FoodPH());
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public static List<ProductionHandler> getProductionHandlers() { return productionHandlers; }

    public House(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "House";
    }

    @Override
    public void run() {
        if(!productionHandlers.isEmpty())
            while(getIsProductionThreadStarted())
            {
                try {
                    Thread.sleep(HousePH.getProductionCooldown());
                } catch (InterruptedException ignored) {
                }
                for(ProductionHandler ph: productionHandlers)
                {
                    ((HousePH)ph).requestProduction(this);
                }
                speak("Produced resources.");
            }
    }
}
