package PixelWars.GameLogic.MapLogic.MapEntities.Resources;

import PixelWars.GameLogic.MapLogic.MapEntities.MapEntity;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;

public abstract class ResourceBank extends MapEntity {
    public static final String[] RESOURCEBANK_TYPES = {"FoodResourceBank", "WoodResourceBank", "StoneResourceBank", "IronResourceBank", "GoldResourceBank"};
    private int production;
    private boolean producedAlready = false;

    protected void setInitProduction(int production) {
        if (!producedAlready) {
            producedAlready = true;
            this.production = production;
        } else throw new RuntimeException("This ResourceBank has already produced its initial resource amount");
    }

    public synchronized int exploit() {
        int amountMined;
        if (production > 10) {
            amountMined = 10;
            production -= amountMined;
            return amountMined;
        } else if(production > 0){
            amountMined = production;
            production -= amountMined;
            getMap().setMapEntityAtCoords(getCoords(), null);
            return amountMined;
        }
        else return 0;
    }

    public int cooldown() {
        return 100;
    }

    public abstract String getConcreteName();
}



