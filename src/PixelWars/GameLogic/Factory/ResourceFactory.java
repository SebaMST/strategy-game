package PixelWars.GameLogic.Factory;

import PixelWars.GameLogic.MapLogic.MapEntities.Resources.*;

public class ResourceFactory implements AbstractFactory<ResourceBank> {
    public static final String[] RESOURCE_TYPES = {"food","wood","stone","iron","gold"};

    @Override
    public ResourceBank create(String resourceType){
        if ("food".equals(resourceType)) {
            return new FoodResourceBank();
        } else if ("wood".equals(resourceType)) {
            return new WoodResourceBank();
        } else if ("stone".equals(resourceType)) {
            return new StoneResourceBank();
        } else if ("iron".equals(resourceType)) {
            return new IronResourceBank();
        } else if ("gold".equals(resourceType)) {
            return new GoldResourceBank();
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}