package PixelWars;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HelloWorld{

    public static void print(String name) throws IOException {

        StringBuilder p=new StringBuilder("");
        p.append("package PixelWars.GameLogic.MapLogic.MapEntities.Interfaces.ProductionHandlers;\n\n");
        p.append("public interface ").append(name).append("PH extends ProductionHandler {\n");
        p.append("\tvoid requestProduction(").append(name).append("PH who);\n}\n");

        BufferedWriter writer = new BufferedWriter(new FileWriter(name+"PH.java"));
        writer.write(p.toString());

        writer.close();
    }

    public static void main(String []args) throws IOException {
        String[] strs={"Colosseum","Granary","Hospital","House","Mansion","Mill","Mine","Mosque","Observatory","Parliament","Smithery","Sphinx","Stable","Temple","TownHall","TradeCenter","University","WatchTower"};
        for(String s:strs)
        {
            print(s);
        }
    }

}