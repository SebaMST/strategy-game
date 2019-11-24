package PixelWars.GUI;

import javafx.scene.image.Image;

import java.io.File;
import java.util.HashMap;

public class ImageLoader {
    private static final String PATH_ICON_FOLDER = "res/img/icon/";
    private static final HashMap<String, Image> playerIconsMap;
    private static final HashMap<String, Image> resourceIconsMap;
    private static final HashMap<String, HashMap<String, Image>> buildingIconsMap;


    static {
        playerIconsMap=createIconsMap(PATH_ICON_FOLDER+"player/");
        resourceIconsMap=createIconsMap(PATH_ICON_FOLDER+"resource/");

        buildingIconsMap=new HashMap<>();
        File buildingIconsFolder = new File(PATH_ICON_FOLDER+"building/");
        File[] buildingIconsSubfolders = buildingIconsFolder.listFiles();
        if (buildingIconsSubfolders != null)
        {
            for(File subfolder: buildingIconsSubfolders)
            {
                String subfolderName = subfolder.getName(); //to be added in buildingIconsMap
                HashMap<String,Image> subfolderIconsMap=createIconsMap(PATH_ICON_FOLDER+"building/"+subfolderName+"/");
                buildingIconsMap.put(subfolderName,subfolderIconsMap);
            }
        }
    }

    private static HashMap<String,Image> createIconsMap(String path)
    {
        HashMap<String,Image> iconsMap = new HashMap<>();
        File iconsFolder = new File(path);
        File[] icons = iconsFolder.listFiles();
        if (icons != null) {
            for(File icon: icons)
            {
                String fullIconName = icon.getName();
                String iconName = fullIconName.substring(0,fullIconName.length()-4); //without .png extension
                Image img = new Image(path+"/"+fullIconName);
                iconsMap.put(iconName,img);
            }
        }
        return iconsMap;
    }

    public static Image getIcon(String category, String iconName)
    {
        if(category.equals("player"))
            return playerIconsMap.get(iconName);
        else if(category.equals("resource"))
            return resourceIconsMap.get(iconName);
        else return buildingIconsMap.get(category).get(iconName);
        //if null is thrown then the icon was not found
    }

}