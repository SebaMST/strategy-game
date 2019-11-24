package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GUI.ImageLoader;
import PixelWars.GameLogic.MapLogic.MapEntities.MapEntity;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;
import PixelWars.GameLogic.Messaging.GlobalSpeaker;
import javafx.scene.image.Image;

public abstract class Building extends MapEntity implements GlobalSpeaker {
    private Player owner;

    public Building(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    @Override
    public Image getIcon() {
        return ImageLoader.getIcon(getConcreteName().toLowerCase(), owner.getColor());
    }

    //region Messaging
    public void speak(String message) {
        GlobalSpeaker.super.speak(message);
    }
    //endregion
}