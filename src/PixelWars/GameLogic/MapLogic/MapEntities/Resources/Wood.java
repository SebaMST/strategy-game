package PixelWars.GameLogic.MapLogic.MapEntities.Resources;

import PixelWars.GUI.ImageLoader;
import javafx.scene.image.Image;

public class Wood extends Resource {
    public Wood(int posX, int posY) {
        super(posX, posY);
    }

    public int initHP() {
        return 90;
    }

    @Override
    public Image getIcon() {
        return ImageLoader.getIcon("Resource","Wood");
    }
}
