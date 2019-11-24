package PixelWars.GameLogic.MapLogic.MapEntities.Resources;

import PixelWars.GUI.ImageLoader;
import javafx.scene.image.Image;

public class GoldResourceBank extends ResourceBank {

    public GoldResourceBank() {
        super(200,15);
    }

    @Override
    public String getConcreteName() {
        return "GoldResourceBank";
    }

}
