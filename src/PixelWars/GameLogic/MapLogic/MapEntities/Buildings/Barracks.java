package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.BuildingAbstractPHs.BarracksPH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ConcretePHs.FoodPH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ConcretePHs.IronPH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ConcretePHs.WoodPH;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Production.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

import java.util.Arrays;
import java.util.List;

public class Barracks extends Building {
    private static final BuildRequirements buildRequirements;
    private static final List<ProductionHandler> productionHandlers;

    static {
        buildRequirements =new BuildRequirements();
        buildRequirements.addRequiredBuilding("Smithery",2);
        buildRequirements.addRequiredBuilding("TownHall",1);
        buildRequirements.addRequiredBuilding("WatchTower",4);
        buildRequirements.addRequiredResource("FoodResourceBank",150);
        buildRequirements.addRequiredResource("GoldResourceBank",80);
        buildRequirements.addRequiredResource("IronResourceBank",150);
        buildRequirements.addRequiredResource("WoodResourceBank",150);
        productionHandlers = Arrays.asList(new FoodPH(), new IronPH(), new WoodPH());
    }

    public static BuildRequirements getBuildRequirements()
    {
        return buildRequirements;
    }

    public static List<ProductionHandler> getProductionHandlers() { return productionHandlers; }

    public Barracks(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "Barracks";
    }

    @Override
    public void run() {
        if(!productionHandlers.isEmpty())
            while(getIsProductionThreadStarted())
            {
                try {
                    Thread.sleep(BarracksPH.getProductionCooldown());
                } catch (InterruptedException ignored) {
                }
                for(ProductionHandler ph: productionHandlers)
                {
                        ((BarracksPH)ph).requestProduction(this);
                }
                speak("Produced resources.");
            }
    }
}
