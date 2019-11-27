package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs.MinePH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ConcretePHs.*;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

import java.util.Arrays;
import java.util.List;

public class Mine extends Building {
    private static final BuildRequirements br;
    private static final List<ProductionHandler> productionHandlers;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("House",4);
        br.addRequiredResource("GoldResourceBank",30);
        br.addRequiredResource("IronResourceBank",50);
        br.addRequiredResource("StoneResourceBank",50);
        productionHandlers= Arrays.asList(new GoldPH(), new IronPH(), new StonePH());
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public static List<ProductionHandler> getProductionHandlers() { return productionHandlers; }

    public Mine(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "Mine";
    }

    @Override
    public void run() {
        if(!productionHandlers.isEmpty())
            while(getIsProductionThreadStarted())
            {
                try {
                    Thread.sleep(MinePH.getProductionCooldown());
                } catch (InterruptedException ignored) {
                }
                for(ProductionHandler ph: productionHandlers)
                {
                    ((MinePH)ph).requestProduction(this);
                }
                speak("Produced resources.");
            }
    }
}
