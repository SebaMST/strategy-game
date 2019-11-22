package PixelWars.GameLogic.MapLogic.MapEntities.Resources;

import PixelWars.GUI.ImageLoader;
import javafx.scene.image.Image;

public class WoodResourceBank extends ResourceBank {

    public WoodResourceBank() {
        setInitProduction(100);
    }

    public Image getIcon() {
        return ImageLoader.getIcon("resource", "woodresourcebank");
    }

    @Override
    public String getConcreteName() {
        return "WoodResourceBank";
    }

}
