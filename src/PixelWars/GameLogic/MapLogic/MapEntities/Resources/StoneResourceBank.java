package PixelWars.GameLogic.MapLogic.MapEntities.Resources;

import PixelWars.GUI.ImageLoader;
import javafx.scene.image.Image;

public class StoneResourceBank extends ResourceBank {

    public StoneResourceBank() {
        super(80,15);
    }

    public Image getIcon() {
        return ImageLoader.getIcon("resource", "stoneresourcebank");
    }

    @Override
    public String getConcreteName() {
        return "StoneResourceBank";
    }

}
