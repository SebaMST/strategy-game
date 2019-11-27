package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs.CastlePH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ConcretePHs.FoodPH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ConcretePHs.IronPH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ConcretePHs.StonePH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

import java.util.Arrays;
import java.util.List;

public class Castle extends Building {
    private static final BuildRequirements buildRequirements;
    private static final List<ProductionHandler> productionHandlers;

    static {
        buildRequirements =new BuildRequirements();
        buildRequirements.addRequiredBuilding("Barracks",4);
        buildRequirements.addRequiredBuilding("Stable",4);
        buildRequirements.addRequiredResource("FoodResourceBank",150);
        buildRequirements.addRequiredResource("GoldResourceBank",150);
        buildRequirements.addRequiredResource("IronResourceBank",200);
        buildRequirements.addRequiredResource("StoneResourceBank",200);
        buildRequirements.addRequiredResource("WoodResourceBank",50);
        productionHandlers= Arrays.asList(new FoodPH(),new IronPH(), new StonePH());
    }

    public static BuildRequirements getBuildRequirements()
    {
        return buildRequirements;
    }

    public static List<ProductionHandler> getProductionHandlers() { return productionHandlers; }

    public Castle(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "Castle";
    }

    @Override
    public void run() {
        if(!productionHandlers.isEmpty())
            while(getIsProductionThreadStarted())
            {
                try {
                    Thread.sleep(CastlePH.getProductionCooldown());
                } catch (InterruptedException ignored) {
                }
                for(ProductionHandler ph: productionHandlers)
                {
                        ((CastlePH)ph).requestProduction(this);
                }
                speak("Produced resources.");
            }
    }
}
