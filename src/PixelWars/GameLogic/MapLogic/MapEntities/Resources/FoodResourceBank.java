package PixelWars.GameLogic.MapLogic.MapEntities.Resources;

import PixelWars.GUI.ImageLoader;
import javafx.scene.image.Image;

public class FoodResourceBank extends ResourceBank {

    public FoodResourceBank() {
        super(270,50);
    }

    @Override
    public String getConcreteName() {
        return "FoodResourceBank";
    }

}