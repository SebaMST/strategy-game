package PixelWars.GameLogic.Factory;

import PixelWars.GameLogic.MapLogic.MapEntities.Resources.ResourceBank;

import java.lang.reflect.Constructor;

public class ResourceFactory implements AbstractFactory<ResourceBank> {
    private static final String[] RESOURCE_TYPES = {"FoodResourceBank", "GoldResourceBank", "IronResourceBank", "StoneResourceBank", "WoodResourceBank"};
    public static String[] getResourceTypes() {
        return RESOURCE_TYPES.clone();
    }

    @Override
    public ResourceBank create(String resourceType) {
        try {
            Class c = Class.forName("PixelWars.GameLogic.MapLogic.MapEntities.Resources." + resourceType);
            return (ResourceBank) c.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}