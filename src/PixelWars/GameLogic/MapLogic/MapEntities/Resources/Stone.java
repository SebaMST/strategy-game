package PixelWars.GameLogic.MapLogic.MapEntities.Resources;

import PixelWars.GUI.ImageLoader;
import javafx.scene.image.Image;

class Stone extends ResourceBank {
    public Stone(int posX, int posY) {
        super(posX, posY);
    }

    public int initHP() {
        return 75;
    }

    @Override
    public Image getIcon() {
        return ImageLoader.getIcon("ResourceBank","Stone");
    }
}
