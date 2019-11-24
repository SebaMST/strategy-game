package PixelWars.GameLogic.MapLogic.MapEntities.Resources;

import PixelWars.GUI.ImageLoader;
import PixelWars.GameLogic.Exceptions.AttemptExploitZeroException;
import PixelWars.GameLogic.MapLogic.MapEntities.MapEntity;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;
import javafx.scene.image.Image;

public abstract class ResourceBank extends MapEntity {
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
        return 0;
    }

    public synchronized int getDurability() {
        return durability;
    }

    public int getDropAmount() {
        return dropAmount;
    }

    public Image getIcon()
    {
        return ImageLoader.getIcon("resource",getConcreteName().toLowerCase());
    }
}



