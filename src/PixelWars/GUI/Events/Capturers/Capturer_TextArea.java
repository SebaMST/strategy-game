package PixelWars.GUI.Events.Capturers;

import PixelWars.GUI.ColorUtils;
import PixelWars.GUI.Events.EventCapturer;
import PixelWars.GameLogic.Game;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Building;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;
import PixelWars.GameLogic.Messaging.GlobalSpeaker;
import PixelWars.GameLogic.Messaging.Message;
import PixelWars.GameLogic.Messaging.MessageLog;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Capturer_TextArea extends TextArea implements EventCapturer {
    private static class UpdateHandler {
        static void handle(MessageLog cause, Capturer_TextArea capturer)
        {
            Platform.runLater(() -> {
                    Message m = cause.lastMessage();
                    GlobalSpeaker sender = m.getSender();

                    if(sender instanceof Player)
                    {
                        String name = ((Player) sender).getName();
                        String color = ((Player) sender).getColor();
                        capturer.appendText(name+" ("+color+"): "+m.getContent()+"\n");
                    }
                    else if(sender instanceof Building)
                    {
                        String name= ((Building) sender).getConcreteName();
                        String owner=((Building)sender).getOwner().getName();
                        capturer.appendText(owner+"'s "+name+": "+m.getContent()+"\n");
                    }
                    else if(sender instanceof Game)
                    {
                        String name = "SYSTEM";
                        capturer.appendText(name+": "+m.getContent()+"\n");
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