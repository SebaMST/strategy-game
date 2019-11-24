package PixelWars.GameLogic.MapLogic.MapEntities.Resources;

import PixelWars.GUI.ImageLoader;
import javafx.scene.image.Image;

public class WoodResourceBank extends ResourceBank {

    public WoodResourceBank() {
        super(270,40);
    }

    @Override
    public String getConcreteName() {
        return "WoodResourceBank";
    }

}
