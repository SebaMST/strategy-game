package PixelWars.GUI.Events.Capturers;

import PixelWars.GUI.Events.EventCapturer;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;
import PixelWars.GameLogic.Messaging.MessageLog;
import javafx.application.Platform;
import javafx.scene.control.TextField;

public class Capturer_TextField extends TextField implements EventCapturer {
    private static class UpdateHandler {
        static void handle(Player.ResourceValueWrapper cause, Capturer_TextField capturer)
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    capturer.setText(""+cause.getValue());
                }
            });

        }
        static void handle(Player cause, Capturer_TextField capturer)
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    capturer.setText(cause.getPosX()+","+cause.getPosY());
                }
            });
        }
    }
    @Override
    public synchronized void update(Object cause) {
        if(cause instanceof Player.ResourceValueWrapper)
        {
            UpdateHandler.handle((Player.ResourceValueWrapper) cause,this);
        }
        else if (cause instanceof Player)
        {
            UpdateHandler.handle((Player) cause,this);
        }
    }
}
