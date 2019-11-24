package PixelWars;

import PixelWars.GUI.ImageLoader;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Building;
import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.Producer;
import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers.ProductionHandler;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;
import PixelWars.GameLogic.MapLogic.Point;
import javafx.scene.image.Image;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HelloWorld{ //Dummy class used for Building concrete classes code generation

    private static String contentString(String name)
    {
        String s="package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;\n";
        s+="import PixelWars.GUI.ImageLoader;\nimport PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.Producer;\n";
        s+="import PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers.ProductionHandler;\n";
        s+="import PixelWars.GameLogic.MapLogic.MapEntities.Player;\nimport javafx.scene.image.Image;\n";
        s+="public class "+name+" extends Building implements Producer {\n";
        s+="public "+name+"(Player owner) { super(owner); }\n";
        s+="@Override\npublic String getConcreteName() { return \""+name+"\"; }\n";
        s+="public Image getIcon() { return ImageLoader.getIcon(\"building\",\""+name.toLowerCase()+"\");}\n";
        s+="@Override\npublic void produce(ProductionHandler productionHandler) { }\n }\n";
        return s;
    }

    private static void toFile(String name) throws IOException {
        String str = contentString(name);
        BufferedWriter writer = new BufferedWriter(new FileWriter(name+".java"));
        writer.write(str);
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        String[] classes=new String[]{"Barracks","Castle","Church","Colosseum","Granary","Hospital","House","Mansion","Mill","Mine","Mosque","Observatory","Parliament","Smithery","Sphinx","Stable","Temple","TownHall","TradeCenter","University","WatchTower","LumberCamp"};
        for(String s :classes)
        {
            toFile(s);
        }
    }

}