package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs.HospitalPH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ConcretePHs.FoodPH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ConcretePHs.GoldPH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

import java.util.Arrays;
import java.util.List;

public class Hospital extends Building {
    private static final BuildRequirements br;
    private static final List<ProductionHandler> productionHandlers;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("TownHall",1);
        br.addRequiredResource("FoodResourceBank",400);
        br.addRequiredResource("GoldResourceBank",100);
        br.addRequiredResource("IronResourceBank",100);
        br.addRequiredResource("StoneResourceBank",150);
        productionHandlers= Arrays.asList(new FoodPH(), new GoldPH());
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public static List<ProductionHandler> getProductionHandlers() { return productionHandlers; }

    public Hospital(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "Hospital";
    }

    @Override
    public void run() {
        if(!productionHandlers.isEmpty())
            while(getIsProductionThreadStarted())
            {
                try {
                    Thread.sleep(HospitalPH.getProductionCooldown());
                } catch (InterruptedException ignored) {
                }
                for(ProductionHandler ph: productionHandlers)
                {
                    ((HospitalPH)ph).requestProduction(this);
                }
                speak("Produced resources.");
            }
    }
}
