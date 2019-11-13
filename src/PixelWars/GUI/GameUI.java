package PixelWars.GUI;

import PixelWars.GameLogic.Game;
import PixelWars.GameLogic.Messaging.MessagingSystem;
import PixelWars.GameLogic.Messaging.MessageLog;
import PixelWars.GameLogic.MapLogic.Map;
import PixelWars.GameLogic.Player;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

import java.awt.*;
import java.io.File;
import java.util.*;

public class GameUI {
    private static final double SCREEN_WIDTH, SCREEN_HEIGHT;

    static {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        SCREEN_WIDTH = screenSize.getWidth();
        SCREEN_HEIGHT = screenSize.getHeight();
        System.out.println(SCREEN_WIDTH + " " + SCREEN_HEIGHT);
        ImageLoader.loadIcons();
    }

    private static class ImageLoader {
        private static final String PATH_ICON_FOLDER = "res/img/icon/";
        private static HashMap<String, HashMap<String,Image>> iconsMap;

        private static void loadIcons() {
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
            System.out.println(iconsMap);
        }

        public static Image getIcon(String category, String name)
        {
            System.out.println(category+" "+name);
            return iconsMap.get(category).get(name);
        }

    }

    public static class IntroUI {
        public static Parent createIntroUI() {
            HBox HBox_intro_root = new HBox();
            HBox_intro_root.getStyleClass().addAll("Alignment-center", "Background-gameTheme"); HBox_intro_root.setId("HBox-intro-root");
            ObservableList<Node> HBox_intro_root_Children = HBox_intro_root.getChildren();
                    VBox VBox_intro_mainPanel = new VBox();
                    VBox_intro_mainPanel.getStyleClass().add("Alignment-center"); VBox_intro_mainPanel.setId("VBox-intro-mainPanel");
                    ObservableList<Node> VBox_intro_mainPanel_Children = VBox_intro_mainPanel.getChildren();
                            VBox VBox_intro_welcome = new VBox();
                            VBox_intro_welcome.getStyleClass().add("Alignment-center"); VBox_intro_welcome.setId("VBox-intro-welcome");
                            ObservableList<Node> VBox_welcome_Children = VBox_intro_welcome.getChildren();
                                    Text Text_title = new Text("PIXEL Wars");
                                    Text_title.getStyleClass().addAll("Font-size-3XL", "Font-family-title", "Effect-dropshadow");
                                    Text Text_motto = new Text("The PIXEL world awaits you: Grind, Build, Conquer!");
                                    Text_motto.getStyleClass().addAll("Font-size-M", "Effect-dropshadow");
                                    VBox_welcome_Children.addAll(Text_title, Text_motto);
                            VBox VBox_config1 = new VBox();
                            VBox_config1.getStyleClass().add("Alignment-bottomCenter"); VBox_config1.setId("VBox-intro-config1");
                            ObservableList<Node> VBox_config1_Children = VBox_config1.getChildren();
                                    Text Text_generalSettings = new Text("General Settings");
                                    Text_generalSettings.getStyleClass().addAll("Font-size-S", "Font-family-header", "Effect-dropshadow");
                                    HBox HBox_config1Body = new HBox();
                                    HBox_config1Body.getStyleClass().add("Alignment-center"); HBox_config1Body.setId("HBox-intro-config1Body");
                                    ObservableList<Node> HBox_config1Body_Children = HBox_config1Body.getChildren();
                                            VBox VBox_config1Labels = new VBox();
                                            VBox_config1Labels.getStyleClass().add("Alignment-leftCenter"); VBox_config1Labels.setId("VBox-intro-config1Labels");
                                            ObservableList<Node> VBox_config1Labels_Children = VBox_config1Labels.getChildren();
                                                    Label Label_playersNr = new Label("Nr. of players:");
                                                    Label_playersNr.getStyleClass().addAll("Font-size-S", "Effect-dropshadow");
                                                    Label Label_mapSize = new Label("Map size:");
                                                    Label_mapSize.getStyleClass().addAll("Font-size-S", "Effect-dropshadow");
                                                    Label Label_resDensity = new Label("Resources density:");
                                                    Label_resDensity.getStyleClass().addAll("Font-size-S", "Effect-dropshadow");
                                                    VBox_config1Labels_Children.addAll(Label_playersNr, Label_mapSize, Label_resDensity);
                                            VBox VBox_config1Choices = new VBox();
                                            VBox_config1Choices.getStyleClass().add("Alignment-rightCenter"); VBox_config1Choices.setId("VBox-intro-config1Choices");
                                            ObservableList<Node> VBox_config1choices_Children = VBox_config1Choices.getChildren();
                                                    Accordion Accordion_playersSettings = new Accordion();
                                                    ChoiceBox<Integer> ChoiceBox_playersNr = IntroUI.createPlayersNrChoiceBox(Accordion_playersSettings);
                                                    ChoiceBox_playersNr.getStyleClass().add("ChoiceBox-gameTheme"); ChoiceBox_playersNr.setId("ChoiceBox-intro-playersNr");
                                                    ChoiceBox<String> ChoiceBox_mapSize = IntroUI.createMapSizeChoiceBox();
                                                    ChoiceBox_mapSize.getStyleClass().add("ChoiceBox-gameTheme"); ChoiceBox_mapSize.setId("ChoiceBox-intro-mapSize");
                                                    ChoiceBox<String> ChoiceBox_resDensity = IntroUI.createResDensityChoiceBox();
                                                    ChoiceBox_resDensity.getStyleClass().add("ChoiceBox-gameTheme"); ChoiceBox_resDensity.setId("ChoiceBox-intro-resDensity");
                                                    VBox_config1choices_Children.addAll(ChoiceBox_playersNr, ChoiceBox_mapSize, ChoiceBox_resDensity);
                                            HBox_config1Body_Children.addAll(VBox_config1Labels, VBox_config1Choices);
                                    VBox_config1_Children.addAll(Text_generalSettings, HBox_config1Body);
                            VBox VBox_config2 = new VBox();
                            VBox_config2.getStyleClass().add("Alignment-topCenter"); VBox_config2.setId("VBox-intro-config2");
                            ObservableList<Node> VBox_config2_Children = VBox_config2.getChildren();
                                    Text Text_playerSettings = new Text("Players Settings");
                                    Text_playerSettings.getStyleClass().addAll("Font-size-S", "Font-family-header", "Effect-dropshadow");
                                    VBox VBox_config2Body = new VBox();
                                    VBox_config2Body.getStyleClass().add("Alignment-center"); VBox_config2Body.setId("VBox-intro-config2Body");
                                    ObservableList<Node> VBox_config2Body_Children = VBox_config2Body.getChildren();
                                            Accordion_playersSettings.getStyleClass().add("Accordion-gameTheme"); Accordion_playersSettings.setId("Accordion-intro-playersSettings");
                                            VBox_config2Body_Children.add(Accordion_playersSettings);
                                    VBox_config2_Children.addAll(Text_playerSettings, VBox_config2Body);
                            HBox HBox_buttons = new HBox();
                            HBox_buttons.getStyleClass().add("Alignment-center"); HBox_buttons.setId("HBox-intro-buttons");
                            ObservableList<Node> VBox_buttons_Children = HBox_buttons.getChildren();
                                    Button Button_play = new Button("PLAY");
                                    Button_play.getStyleClass().addAll("Button-gameTheme", "Font-size-S"); Button_play.setId("Button-intro-play");
                                    Button Button_quit = new Button("QUIT");
                                    Button_quit.getStyleClass().addAll("Button-gameTheme", "Font-size-S"); Button_quit.setId("Button-intro-quit");
                                    VBox_buttons_Children.addAll(Button_play, Button_quit);
                            VBox_intro_mainPanel_Children.addAll(VBox_intro_welcome, VBox_config1, VBox_config2, HBox_buttons);
                    HBox_intro_root_Children.add(VBox_intro_mainPanel);
            return HBox_intro_root;
        }

        public static ChoiceBox<Integer> createPlayersNrChoiceBox(Accordion Accordion_playersSettings) {
            ChoiceBox<Integer> ChoiceBox_playersNr = new ChoiceBox<>();
            ObservableList<Integer> ChoiceBox_playersNr_items = ChoiceBox_playersNr.getItems();
            int playersNrMin = Game.getPlayersNrMin();
            for (int i = playersNrMin; i <= Game.getPlayersNrMax(); i++) {
                ChoiceBox_playersNr_items.add(i);
            }
            ChoiceBox_playersNr.setValue(playersNrMin);
            IntroUI.updatePlayersSettingsAccordion(playersNrMin, Accordion_playersSettings);
            ChoiceBox_playersNr.valueProperty().addListener(new ChangeListener<Object>() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    IntroUI.updatePlayersSettingsAccordion((int) newValue, Accordion_playersSettings);
                }
            });
            return ChoiceBox_playersNr;
        }

        public static ChoiceBox<String> createMapSizeChoiceBox() {
            Set<String> mapSizes = Map.getMapSizes();
            ChoiceBox<String> ChoiceBox_mapSize = new ChoiceBox<>(FXCollections.observableArrayList(mapSizes));
            ChoiceBox_mapSize.setValue(mapSizes.iterator().next());
            return ChoiceBox_mapSize;
        }

        public static ChoiceBox<String> createResDensityChoiceBox() {
            Set<String> resDensities = Map.getResDensities();
            ChoiceBox<String> ChoiceBox_resDensity = new ChoiceBox<>(FXCollections.observableArrayList(resDensities));
            ChoiceBox_resDensity.setValue(resDensities.iterator().next());
            return ChoiceBox_resDensity;
        }

        public static void updatePlayersSettingsAccordion(int playersNr, Accordion playersSettingsAccordion) {
            ObservableList<TitledPane> playersTitledPanes = playersSettingsAccordion.getPanes();
            playersTitledPanes.clear();

            for (int i = 1; i <= playersNr; ++i) {
                HBox HBox_playerConfig = new HBox();
                HBox_playerConfig.getStyleClass().add("Alignment-center");
                ObservableList<Node> HBox_playerConfig_Children = HBox_playerConfig.getChildren();
                        VBox VBox_playerLabels = new VBox();
                        VBox_playerLabels.getStyleClass().addAll("Alignment-leftCenter", "VBox-intro-playerLabels");
                        ObservableList<Node> VBox_playerLabels_Children = VBox_playerLabels.getChildren();
                                Label Label_playerName = new Label("Name:");
                                Label_playerName.getStyleClass().addAll("Font-size-S", "Effect-dropshadow");
                                Label Label_playerColor = new Label("Color:");
                                Label_playerColor.getStyleClass().addAll("Font-size-S", "Effect-dropshadow");
                                VBox_playerLabels_Children.addAll(Label_playerName, Label_playerColor);
                        VBox VBox_playerInputs = new VBox();
                        VBox_playerInputs.getStyleClass().addAll("Alignment-rightCenter", "VBox-intro-playerInputs");
                        ObservableList<Node> VBox_playerInputs_Children = VBox_playerInputs.getChildren();
                                TextField TextField_playerName = new TextField("Player #" + i);
                                TextField_playerName.getStyleClass().add("TextField-gameTheme"); TextField_playerName.setId("TextField-intro-player" + i + "Name");
                                HBox HBox_playerColor = new HBox();
                                HBox_playerColor.getStyleClass().addAll("Alignment-center", "HBox-intro-playerColor");
                                ObservableList<Node> HBox_playerColor_Children = HBox_playerColor.getChildren();
                                        ImageView img = new ImageView(); img.setFitWidth(30); img.setFitHeight(30);
                                        ChoiceBox<String> ChoiceBox_playerColor = createPlayerColorChoiceBox(i, img);
                                        ChoiceBox_playerColor.getStyleClass().addAll("ChoiceBox-gameTheme"); ChoiceBox_playerColor.setId("ChoiceBox-intro-player" + i + "Color");
                                        HBox_playerColor_Children.addAll(ChoiceBox_playerColor, img);
                                VBox_playerInputs_Children.addAll(TextField_playerName, HBox_playerColor);
                        HBox_playerConfig_Children.addAll(VBox_playerLabels, VBox_playerInputs);
                playersTitledPanes.add(new TitledPane("Player #" + i, HBox_playerConfig));
            }
        }

        public static ChoiceBox<String> createPlayerColorChoiceBox(int playerNr, ImageView img) {
            String[] playerColors = Player.getPlayersColors();
            String color = playerColors[playerNr - 1];
            ChoiceBox<String> ChoiceBox_playerColors = new ChoiceBox<>(FXCollections.observableArrayList(playerColors));
            ChoiceBox_playerColors.setValue(color);
            String iconPath = "res/img/icon/player/" + color + ".png";
            img.setImage(new Image(iconPath));
            ChoiceBox_playerColors.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    String iconPath = "res/img/icon/player/" + newValue + ".png";
                    img.setImage(new Image(iconPath));
                }
            });
            return ChoiceBox_playerColors;
        }
    }

    public static class InGameUI {
        public static Parent createInGameUI(Game g) {
            HBox HBox_inGameRoot = new HBox();
            HBox_inGameRoot.getStyleClass().add("Background-gameTheme"); HBox_inGameRoot.setId("HBox-ingame-root");
            ObservableList<Node> HBox_inGameRoot_Children = HBox_inGameRoot.getChildren();
                    VBox VBox_inGameLeft = new VBox();
                    VBox_inGameLeft.getStyleClass().add("Alignment-center"); VBox_inGameLeft.setId("VBox-ingame-left");
                    VBox_inGameLeft.setPrefWidth(480);
                    ObservableList<Node> VBox_inGameLeft_Children = VBox_inGameLeft.getChildren();
                            Accordion Accordion_playersPanels = playersAsAccordion(g.getPlayers()); Accordion_playersPanels.setId("Accordion-ingame-playersPanels");
                            TextArea TextArea_globalLog = messageLogAsTextArea(MessagingSystem.MESSAGE_LOG);
                            TextArea_globalLog.setId("TextArea-ingame-globalLog");
                            VBox_inGameLeft_Children.addAll(Accordion_playersPanels, TextArea_globalLog);
                    VBox VBox_inGameRight = new VBox();
                    VBox_inGameRight.getStyleClass().add("Alignment-center");
                    VBox_inGameRight.setId("VBox-ingame-right");
                    VBox_inGameRight.setPrefWidth(1440);
                    ObservableList<Node> VBox_inGameRight_Children = VBox_inGameRight.getChildren();
                            HBox HBox_map = new HBox();
                            HBox_inGameRoot.setId("HBox-ingame-map");
                            HBox_map.setPrefHeight(840);
                            ObservableList<Node> HBox_map_Children = HBox_map.getChildren();
                                    GridPane mapPane = mapAsPane(g.getMap());
                                    mapPane.setId("GridPane-ingame-mapPane");
                                    HBox_map_Children.add(mapPane);
                            VBox_inGameRight_Children.add(HBox_map);
                    HBox_inGameRoot_Children.addAll(VBox_inGameLeft, VBox_inGameRight);
            return HBox_inGameRoot;
        }

        private static GridPane mapAsPane(Map map) {
            GridPane mapPane = new GridPane();
            double[][] noiseMatrix = map.getNoiseMatrix();
            int tileSize = map.getTileSize();

            Color[] terrainColors = Map.terrainColors;
            int terrainColorsLength=terrainColors.length;

            Background[] mapTileColors = new Background[terrainColorsLength];
            for(int i=0;i<terrainColorsLength;i++)
            {
                mapTileColors[i] = new Background(new BackgroundFill(terrainColors[i],CornerRadii.EMPTY,Insets.EMPTY));
            }

            Random r = new Random();

            for (int i = 0; i < map.getHeight(); i++) {
                for (int j = 0; j < map.getWidth(); j++) {
                    for (int h = 0; h < Map.terrainHeights.length; h++) {
                        if (noiseMatrix[i][j] <= Map.terrainHeights[h]) {
                            AnchorPane a = new AnchorPane();
                            a.setBackground(mapTileColors[h]);
                            ImageView iv = new ImageView();
                            iv.setFitWidth(tileSize); iv.setFitHeight(tileSize);
                            /*if(r.nextInt(7000)%611==0)
                            iv.setImage(ImageLoader.getIcon("ResourceBank","Tree"));
                            else if (r.nextInt(7000)%2111==0)
                                iv.setImage(ImageLoader.getIcon("ResourceBank","Iron"));
                            else if (r.nextInt(7000)%1333==0)
                                iv.setImage(ImageLoader.getIcon("ResourceBank","Gold"));
                            else if (r.nextInt(7000)%700==0)
                                iv.setImage(ImageLoader.getIcon("ResourceBank","Stone"));
                            else if (r.nextInt(7000)%1555==0)
                                iv.setImage(ImageLoader.getIcon("ResourceBank","Food"));*/
                            a.getChildren().add(iv);
                            mapPane.add(a,j,i);
                            break;
                        }
                    }
                }
            }
            return mapPane;
        }

        private static Accordion playersAsAccordion(ArrayList<Player> players) {
            Accordion playersPanel = new Accordion();
            playersPanel.getStyleClass().add("Accordion-gameTheme");
            ObservableList<TitledPane> playerPanes = playersPanel.getPanes();

            for (Player p : players) {
                playerPanes.add(new TitledPane(p.getName(), new AnchorPane()));
            }
            return playersPanel;
        }

        private static TextArea messageLogAsTextArea(MessageLog log) {
            TextArea gameLog = new TextArea(log.toString());
            gameLog.setEditable(false);
            return gameLog;
        }
    }

}