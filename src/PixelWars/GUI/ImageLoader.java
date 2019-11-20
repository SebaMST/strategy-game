package PixelWars.GUI;

import javafx.scene.image.Image;

import java.io.File;
import java.util.HashMap;

public class ImageLoader {
    private static final String PATH_ICON_FOLDER = "res/img/icon/";
    private static final HashMap<String, HashMap<String, Image>> iconsMap;

    static {
        //Loading icons algorithm
        iconsMap=new HashMap<>();
        File iconFolder = new File(PATH_ICON_FOLDER);
        File[] iconSubfolders = iconFolder.listFiles();
        if (iconSubfolders != null)
        {
            for(File subfolder: iconSubfolders)
            {
                String subfolderName = subfolder.getName(); //to be added in iconsMap
                HashMap<String,Image> subfolderIconsMap=new HashMap<>();
                File[] icons = subfolder.listFiles();
                if (icons != null) {
                    for(File icon: icons)
                    {
                        String fullIconName = icon.getName();
                        String iconName = fullIconName.substring(0,fullIconName.length()-4); //without .png extension
                        Image img = new Image(PATH_ICON_FOLDER+subfolderName+"/"+fullIconName);
                        subfolderIconsMap.put(iconName,img);
                    }
                }
                iconsMap.put(subfolderName,subfolderIconsMap);
            }
        }
    }

    public static Image getIcon(String category, String name)
    {
        return iconsMap.get(category).get(name);
    }

}