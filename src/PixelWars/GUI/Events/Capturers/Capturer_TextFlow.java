package PixelWars.GUI.Events.Capturers;

import PixelWars.GUI.ColorUtils;
import PixelWars.GUI.Events.EventCapturer;
import PixelWars.GameLogic.Game;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;
import PixelWars.GameLogic.Messaging.GlobalSpeaker;
import PixelWars.GameLogic.Messaging.Message;
import PixelWars.GameLogic.Messaging.MessageLog;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Capturer_TextFlow extends TextFlow implements EventCapturer {
    private static class UpdateHandler {
        static void handle(MessageLog cause, Capturer_TextFlow capturer)
        {
            Platform.runLater(() -> {
                Message m = cause.lastMessage();
                GlobalSpeaker sender = m.getSender();

                String name;
                String content = m.getContent();

                if(sender instanceof Player)
                {
                    name = ((Player) sender).getName();
                    String color = ((Player) sender).getColor();
                    Text text=new Text(name+" ("+color+"): "+content+"\n");
                    text.setFill(ColorUtils.COLOR_MAP.get(color));
                    text.setFont(new Font(11));
                    capturer.getChildren().add(text);
                    if(capturer.getChildren().size()>30)
                    {
                        capturer.getChildren().remove(0);
                    }
                }
                else if(sender instanceof Game)
                {
                    name = "SYSTEM";
                    Text text=new Text(name+": "+content+"\n");
                    text.setFill(Color.WHITE);
                    text.setFont(new Font(11));
                    capturer.getChildren().add(text);
                    if(capturer.getChildren().size()>30)
                    {
                        capturer.getChildren().remove(0);
                    }
                }

            });

        }
    }
    @Override
    public void update(Object cause) {
        if(cause instanceof MessageLog)
        {
            UpdateHandler.handle((MessageLog)cause,this);
        }
    }
}