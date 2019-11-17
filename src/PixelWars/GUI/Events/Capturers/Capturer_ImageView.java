package PixelWars.GUI.Events.Capturers;

import PixelWars.GUI.Events.EventCapturer;
import PixelWars.GameLogic.MapLogic.MapTile;
import javafx.scene.image.ImageView;

public class Capturer_ImageView extends ImageView implements EventCapturer {
    private static class UpdateHandler {
        static void handle(MapTile cause, Capturer_ImageView capturer)
        {
                capturer.setImage(cause.getMapEntity().getIcon());
        }
    }
    @Override
    public synchronized void update(Object cause) {
        if(cause instanceof MapTile)
            UpdateHandler.handle((MapTile)cause,this);
    }
}
