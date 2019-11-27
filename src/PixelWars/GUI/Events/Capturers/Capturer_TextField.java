package PixelWars.GUI.Events.Capturers;

import PixelWars.GUI.Events.EventCapturer;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;
import PixelWars.GameLogic.MapLogic.Point;
import javafx.application.Platform;
import javafx.scene.control.TextField;

public class Capturer_TextField extends TextField implements EventCapturer {
    private static class UpdateHandler {
        static void handle(Player.ResourceDetailWrapper cause, Capturer_TextField capturer)
        {
            Platform.runLater(() -> capturer.setText("" + cause.getValue()));

        }
        static void handle(Player.BuildingDetailWrapper cause, Capturer_TextField capturer)
        {
            Platform.runLater(() -> capturer.setText(""+cause.getBuildingList().size()));
        }
        static void handle(Player cause, Capturer_TextField capturer)
        {
            Platform.runLater(() -> {
                Point coords = cause.getCoords();
                capturer.setText("X:"+coords.getX()+",Y:"+coords.getY());
            });
        }
    }
    @Override
    public void update(Object cause) {
        if(cause instanceof Player.ResourceDetailWrapper)
        {
            UpdateHandler.handle((Player.ResourceDetailWrapper) cause,this);
        }
        else if(cause instanceof Player.BuildingDetailWrapper)
        {
            UpdateHandler.handle((Player.BuildingDetailWrapper) cause, this);
        }
        else if (cause instanceof Player)
        {
            UpdateHandler.handle((Player) cause,this);
        }
    }
}
