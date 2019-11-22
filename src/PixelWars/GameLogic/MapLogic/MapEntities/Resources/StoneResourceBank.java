package PixelWars.GameLogic.MapLogic.MapEntities.Resources;

import PixelWars.GUI.ImageLoader;
import javafx.scene.image.Image;

public class StoneResourceBank extends ResourceBank {

    public StoneResourceBank() {
        setInitProduction(80);
    }

    public Image getIcon() {
        return ImageLoader.getIcon("resource", "stoneresourcebank");
    }

    @Override
    public String getConcreteName() {
        return "StoneResourceBank";
    }

}
