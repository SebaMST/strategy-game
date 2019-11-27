package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs.ColosseumPH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

import java.util.Arrays;
import java.util.List;

public class Colosseum extends Building {
    private static final BuildRequirements br;
    private static final List<ProductionHandler> productionHandlers;

    static {
        br=new BuildRequirements();
        br.addRequiredBuilding("Castle",4);
        br.addRequiredResource("GoldResourceBank",300);
        br.addRequiredResource("IronResourceBank",800);
        br.addRequiredResource("StoneResourceBank",1200);
        productionHandlers= Arrays.asList();
    }

    public static BuildRequirements getBuildRequirements()
    {
        return br;
    }

    public static List<ProductionHandler> getProductionHandlers() { return productionHandlers; }

    public Colosseum(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "Colosseum";
    }

    public void run() {
        if(!productionHandlers.isEmpty())
            while(getIsProductionThreadStarted())
            {
                try {
                    Thread.sleep(ColosseumPH.getProductionCooldown());
                } catch (InterruptedException ignored) {
                }
                for(ProductionHandler ph: productionHandlers)
                {
                    ((ColosseumPH)ph).requestProduction(this);
                }
                speak("Produced resources.");
            }
    }
}
