package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs.SphinxPH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

import java.util.Arrays;
import java.util.List;

public class Sphinx extends Building{
    private static final BuildRequirements br;
    private static final List<ProductionHandler> productionHandlers;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("Castle",2);
        br.addRequiredBuilding("Observatory",2);
        br.addRequiredResource("GoldResourceBank",450);
        br.addRequiredResource("StoneResourceBank",1600);
        productionHandlers= Arrays.asList();
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public static List<ProductionHandler> getProductionHandlers() { return productionHandlers; }

    public Sphinx(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "Sphinx";
    }

    @Override
    public void run() {
        if(!productionHandlers.isEmpty())
            while(getIsProductionThreadStarted())
            {
                try {
                    Thread.sleep(SphinxPH.getProductionCooldown());
                } catch (InterruptedException ignored) {
                }
                for(ProductionHandler ph: productionHandlers)
                {
                    ((SphinxPH)ph).requestProduction(this);
                }
                speak("Produced resources.");
            }
    }
}
