package PixelWars.GameLogic.MapLogic.MapEntities.Resources;

import PixelWars.GUI.ImageLoader;
import javafx.scene.image.Image;

public class FoodResourceBank extends ResourceBank {

    public FoodResourceBank() {
        super(120,30);
    }

    public Image getIcon() {
        return ImageLoader.getIcon("resource", "foodresourcebank");
    }

    @Override
    public String getConcreteName() {
        return "FoodResourceBank";
    }

}