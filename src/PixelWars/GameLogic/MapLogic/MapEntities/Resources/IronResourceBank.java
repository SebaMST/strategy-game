package PixelWars.GameLogic.MapLogic.MapEntities.Resources;

import PixelWars.GUI.ImageLoader;
import javafx.scene.image.Image;

public class IronResourceBank extends ResourceBank {

    public IronResourceBank() {
        setInitProduction(60);
    }

    public Image getIcon() {
        return ImageLoader.getIcon("resource", "ironresourcebank");
    }

    @Override
    public String getConcreteName() {
        return "IronResourceBank";
    }

}
