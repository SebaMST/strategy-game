package PixelWars.GameLogic.MapLogic.MapEntities.Resources;

import PixelWars.GUI.ImageLoader;
import javafx.scene.image.Image;

public class GoldResourceBank extends ResourceBank {

    public GoldResourceBank() {
        super(40,5);
    }

    public Image getIcon() {
        return ImageLoader.getIcon("resource", "goldresourcebank");
    }

    @Override
    public String getConcreteName() {
        return "GoldResourceBank";
    }

}
