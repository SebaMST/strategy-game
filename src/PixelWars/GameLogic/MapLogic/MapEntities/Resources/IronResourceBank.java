package PixelWars.GameLogic.MapLogic.MapEntities.Resources;

import PixelWars.GUI.ImageLoader;
import javafx.scene.image.Image;

public class IronResourceBank extends ResourceBank {

    public IronResourceBank() {
        super(200,30);
    }

    @Override
    public String getConcreteName() {
        return "IronResourceBank";
    }

}
