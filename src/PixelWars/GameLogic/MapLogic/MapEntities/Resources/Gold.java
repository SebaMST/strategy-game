package PixelWars.GameLogic.MapLogic.MapEntities.Resources;

import PixelWars.GUI.ImageLoader;
import javafx.scene.image.Image;

class Gold extends ResourceBank {
    public Gold(int posX, int posY) {
        super(posX, posY);
    }

    public int initHP() {
        return 50;
    }

    @Override
    public Image getIcon() {
        return ImageLoader.getIcon("ResourceBank","Gold");
    }
}
