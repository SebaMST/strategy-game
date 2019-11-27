package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import java.util.HashMap;

public class BuildRequirements {
    private HashMap<String,Integer> REQUIRED_RESOURCES=new HashMap<>();
    private HashMap<String,Integer> REQUIRED_BUILDINGS=new HashMap<>();

    public void addRequiredResource(String resourceName, int resourceCount)
    {
        REQUIRED_RESOURCES.put(resourceName,resourceCount);
    }

    public void addRequiredBuilding(String buildingName, int buildingCount)
    {
        REQUIRED_BUILDINGS.put(buildingName,buildingCount);
    }

    public HashMap<String,Integer> getRequiredResources()
    {
        return REQUIRED_RESOURCES;
    }

    public HashMap<String,Integer> getRequiredBuildings()
    {
        return REQUIRED_BUILDINGS;
    }
}