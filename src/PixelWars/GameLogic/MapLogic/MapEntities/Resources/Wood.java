package PixelWars.GameLogic.MapLogic.MapEntities.Resources;

import PixelWars.GUI.ImageLoader;
import javafx.scene.image.Image;

class Wood extends ResourceBank {
    public Wood(int posX, int posY) {
        super(posX, posY);
    }

    public int initHP() {
        return 90;
    }

    @Override
    public Image getIcon() {
        return ImageLoader.getIcon("ResourceBank","Wood");
    }
}
