package PixelWars.GameLogic.MapLogic.MapEntities.Resources;

import PixelWars.GameLogic.Exceptions.AttemptExploitZeroException;
import PixelWars.GameLogic.MapLogic.MapEntities.MapEntity;

public abstract class ResourceBank extends MapEntity {
    public static final String[] RESOURCEBANK_TYPES = {"FoodResourceBank", "WoodResourceBank", "StoneResourceBank", "IronResourceBank", "GoldResourceBank"};
    private int durability;
    private final int dropAmount;

    protected ResourceBank(int initDurability,int dropAmount)
    {
        this.durability =initDurability;
        this.dropAmount=dropAmount;
    }

    public synchronized int exploit() {
        if (durability > dropAmount) {
            durability -= dropAmount;
            return dropAmount;
        } else if(durability > 0){
            int leftAmount = durability;
            durability = 0;
            getMap().setMapEntityAtCoords(getCoords(),null);
            return leftAmount;
        }
        throw new IllegalStateException("EXPLOIT");
    }

    public synchronized int getDurability() {
        return durability;
    }

    public int getDropAmount() {
        return dropAmount;
    }

    public abstract String getConcreteName();
}



