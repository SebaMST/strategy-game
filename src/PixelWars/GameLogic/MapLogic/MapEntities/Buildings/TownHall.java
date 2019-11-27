package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs.TownHallPH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ConcretePHs.*;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

import java.util.Arrays;
import java.util.List;

public class TownHall extends Building{
    private static final BuildRequirements br;
    private static final List<ProductionHandler> productionHandlers;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("Church",1);
        br.addRequiredBuilding("Granary",2);
        br.addRequiredBuilding("LumberCamp",2);
        br.addRequiredBuilding("Mansion",3);
        br.addRequiredBuilding("Mill",2);
        br.addRequiredBuilding("Mine",2);
        br.addRequiredResource("FoodResourceBank",220);
        br.addRequiredResource("GoldResourceBank",100);
        br.addRequiredResource("StoneResourceBank",150);
        br.addRequiredResource("WoodResourceBank",120);
        productionHandlers= Arrays.asList(new FoodPH(), new GoldPH(), new IronPH(), new StonePH(), new WoodPH());
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public static List<ProductionHandler> getProductionHandlers() { return productionHandlers; }

    public TownHall(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "TownHall";
    }

    @Override
    public void run() {
        if(!productionHandlers.isEmpty())
            while(getIsProductionThreadStarted())
            {
                try {
                    Thread.sleep(TownHallPH.getProductionCooldown());
                } catch (InterruptedException ignored) {
                }
                for(ProductionHandler ph: productionHandlers)
                {
                    ((TownHallPH)ph).requestProduction(this);
                }
                speak("Produced resources.");
            }
    }
}
