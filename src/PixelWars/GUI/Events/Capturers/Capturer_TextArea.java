package PixelWars.GUI.Events.Capturers;

import PixelWars.GUI.Events.EventCapturer;
import PixelWars.GameLogic.Messaging.MessageLog;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class Capturer_TextArea extends TextArea implements EventCapturer {
    private static class UpdateHandler {
        static void handle(MessageLog cause, Capturer_TextArea capturer)
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    capturer.setText(cause.toString());
                }
            });

        }
    }
    @Override
    public synchronized void update(Object cause) {
        if(cause instanceof MessageLog)
        {
            UpdateHandler.handle((MessageLog)cause,this);
        }
    }
}
