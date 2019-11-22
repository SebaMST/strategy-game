package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GUI.ImageLoader;
import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.Producer;
import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;
import javafx.scene.image.Image;

public class Colosseum extends Building implements Producer {

    public Colosseum(Player owner) {
        super(owner);
    }

    @Override
    public String getConcreteName() {
        return "Colosseum";
    }

    public Image getIcon() {
        return ImageLoader.getIcon("building","colosseum");
    }

    @Override
    public void produce(ProductionHandler productionHandler) {

    }
}