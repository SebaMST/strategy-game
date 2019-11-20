package PixelWars.GUI.Events.Capturers;

import PixelWars.GUI.Events.EventCapturer;
import PixelWars.GameLogic.MapLogic.MapTile;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class Capturer_ImageView extends ImageView implements EventCapturer {
    private static class UpdateHandler {
        static void handle(MapTile cause, Capturer_ImageView capturer)
        {
            if(cause.getMapEntity()!=null) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        capturer.setImage(cause.getMapEntity().getIcon());
                    }
                });

            }
        }
    }
    @Override
    public synchronized void update(Object cause) {
        if(cause instanceof MapTile)
            UpdateHandler.handle((MapTile)cause,this);
    }
}
