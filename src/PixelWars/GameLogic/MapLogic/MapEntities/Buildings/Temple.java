package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs.TemplePH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ConcretePHs.GoldPH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ConcretePHs.StonePH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

import java.util.Arrays;
import java.util.List;

public class Temple extends Building {
    private static final BuildRequirements br;
    private static final List<ProductionHandler> productionHandlers;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("Church",3);
        br.addRequiredResource("FoodResourceBank",50);
        br.addRequiredResource("GoldResourceBank",100);
        br.addRequiredResource("StoneResourceBank",100);
        br.addRequiredResource("WoodResourceBank",100);
        productionHandlers= Arrays.asList(new GoldPH(), new StonePH());
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public static List<ProductionHandler> getProductionHandlers() { return productionHandlers; }

    public Temple(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "Temple";
    }

    @Override
    public void run() {
        if(!productionHandlers.isEmpty())
            while(getIsProductionThreadStarted())
            {
                try {
                    Thread.sleep(TemplePH.getProductionCooldown());
                } catch (InterruptedException ignored) {
                }
                for(ProductionHandler ph: productionHandlers)
                {
                    ((TemplePH)ph).requestProduction(this);
                }
                speak("Produced resources.");
            }
    }
}
