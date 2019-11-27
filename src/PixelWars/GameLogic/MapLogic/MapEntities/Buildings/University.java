package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs.UniversityPH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ConcretePHs.GoldPH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ConcretePHs.WoodPH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

import java.util.Arrays;
import java.util.List;

public class University extends Building {
    private static final BuildRequirements br;
    private static final List<ProductionHandler> productionHandlers;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("TownHall",1);
        br.addRequiredResource("FoodResourceBank",100);
        br.addRequiredResource("GoldResourceBank",100);
        br.addRequiredResource("WoodResourceBank",400);
        productionHandlers= Arrays.asList(new GoldPH(), new WoodPH());
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public static List<ProductionHandler> getProductionHandlers() { return productionHandlers; }

    public University(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "University";
    }

    @Override
    public void run() {
        if(!productionHandlers.isEmpty())
            while(getIsProductionThreadStarted())
            {
                try {
                    Thread.sleep(UniversityPH.getProductionCooldown());
                } catch (InterruptedException ignored) {
                }
                for(ProductionHandler ph: productionHandlers)
                {
                    ((UniversityPH)ph).requestProduction(this);
                }
                speak("Produced resources.");
            }
    }

}
