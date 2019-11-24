package PixelWars.GameLogic.MapLogic.MapEntities.Resources;

import PixelWars.GUI.ImageLoader;
import javafx.scene.image.Image;

public class StoneResourceBank extends ResourceBank {

    public StoneResourceBank() {
        super(250,40);
    }

    @Override
    public String getConcreteName() {
        return "StoneResourceBank";
    }

}
