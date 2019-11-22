package PixelWars.GUI;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Collections;
import java.util.HashMap;

public class ColorUtils {
    public static final HashMap<String, Color> COLOR_MAP;

    static {
        COLOR_MAP=new HashMap<>();
        COLOR_MAP.put("red",Color.rgb(255,0,0));
        COLOR_MAP.put("orange",Color.rgb(255,131,0));
        COLOR_MAP.put("yellow",Color.rgb(255,216,0));
        COLOR_MAP.put("brown",Color.rgb(141,83,27));
        COLOR_MAP.put("green",Color.rgb(76,255,0));
        COLOR_MAP.put("cyan",Color.rgb(0,255,212));
        COLOR_MAP.put("blue",Color.rgb(0,144,255));
        COLOR_MAP.put("purple",Color.rgb(200,22,255));
    }
}
