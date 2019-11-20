package PixelWars.GameLogic.MapLogic.MapEntities.Resources;

import PixelWars.GUI.ImageLoader;
import javafx.scene.image.Image;

public class Food extends Resource {
    public Food(int posX, int posY) {
        super(posX, posY);
    }

    public int initHP() {
        return 100;
    }

    @Override
    public Image getIcon() {
        return ImageLoader.getIcon("Resource","Food");
    }
}