package PixelWars.GameLogic.Factory;

import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.BuildRequirements;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Building;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class BuildingFactory implements AbstractFactory<Building> {
    private static final String[] BUILDING_TYPES = {"Barracks", "Castle", "Church", "Colosseum", "Granary", "Hospital", "House", "LumberCamp","Mansion", "Mill", "Mine", "Mosque", "Observatory", "Parliament", "Smithery", "Sphinx", "Stable", "Temple", "TownHall", "TradeCenter", "University", "WatchTower"};
    public static String[] getBuildingTypes() {
        return BUILDING_TYPES.clone();
    }

    private Player owner;
    public BuildingFactory(Player owner) {
        this.owner = owner;
    }

    @Override
    public Building create(String buildingType) {
        try {
            Constructor c = Class.forName("PixelWars.GameLogic.MapLogic.MapEntities.Buildings." + buildingType).getDeclaredConstructor(Player.class);
            return (Building) c.newInstance(owner);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BuildRequirements getRequirements(String buildingType) {
        try {
            Method m = Class.forName("PixelWars.GameLogic.MapLogic.MapEntities.Buildings."+buildingType).getMethod("getBuildRequirements");
            return (BuildRequirements)m.invoke(null);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
