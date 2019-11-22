package PixelWars.GUI.Events.Capturers;

import PixelWars.GUI.ColorUtils;
import PixelWars.GUI.Events.EventCapturer;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;
import PixelWars.GameLogic.Messaging.GlobalSpeaker;
import PixelWars.GameLogic.Messaging.MessageLog;
import PixelWars.GameLogic.Messaging.Message;
import PixelWars.GameLogic.Game;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.Arrays;

public class Capturer_TextFlow extends TextFlow implements EventCapturer {

    private static class UpdateHandler {
        static void handle(MessageLog cause, Capturer_TextFlow capturer)
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                        Message m = cause.lastMessage();
                        GlobalSpeaker sender = m.getSender();

                        Text message;
                        if(sender instanceof Player)
                        {
                            String name = ((Player) sender).getName();
                            String color = ((Player) sender).getColor();
                            message = new Text(name+" ("+color+"): "+m.getContent()+"\n");
                            message.setFill(ColorUtils.COLOR_MAP.get(color));
                            message.getStyleClass().add("Font-size-2XS");
                            capturer.getChildren().add(message);

                        }
                        else if(sender instanceof Game)
                        {
                            String name = "SYSTEM";
                            message = new Text(name+": "+m.getContent()+"\n");
                            message.setFill(Color.WHITE);
                            message.getStyleClass().add("Font-size-2XS");
                            capturer.getChildren().add(message);
                        }
                    }
            });

        }
    }

    @Override
    public synchronized void update(Object cause) {
        if(cause instanceof MessageLog)
        {
            Capturer_TextFlow.UpdateHandler.handle((MessageLog)cause,this);
        }
    }

}
