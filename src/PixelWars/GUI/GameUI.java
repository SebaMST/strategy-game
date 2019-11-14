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
    }

    public static void init()
    {
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
        }

        public static Image getIcon(String category, String name)
        {
            return iconsMap.get(category).get(name);
        }

    }

    public static class IntroUI {
        public static Parent createIntroUI() { //creates structure and behaviour of the intro UI
            HBox HBox_intro_root = new HBox();
            HBox_intro_root.getStyleClass().addAll("Alignment-center", "Background-gameTheme"); HBox_intro_root.setId("HBox-intro-root");
            ObservableList<Node> HBox_intro_root_Children = HBox_intro_root.getChildren();
                    VBox VBox_intro_mainPanel = new VBox();
                    VBox_intro_mainPanel.getStyleClass().add("Alignment-center"); VBox_intro_mainPanel.setId("VBox-intro-mainPanel");
                    ObservableList<Node> VBox_intro_mainPanel_Children = VBox_intro_mainPanel.getChildren();
                            VBox VBox_intro_welcome = new VBox();
                            VBox_intro_welcome.getStyleClass().add("Alignment-center"); VBox_intro_welcome.setId("VBox-intro-welcome");
                            ObservableList<Node> VBox_intro_welcome_Children = VBox_intro_welcome.getChildren();
                                    Text Text_intro_title = new Text("PIXEL Wars");
                                    Text_intro_title.getStyleClass().addAll("Font-size-3XL", "Font-family-title", "Effect-dropshadow");
                                    Text Text_intro_motto = new Text("The PIXEL world awaits you: Grind, Build, Conquer!");
                                    Text_intro_motto.getStyleClass().addAll("Font-size-M", "Effect-dropshadow");
                                    VBox_intro_welcome_Children.addAll(Text_intro_title, Text_intro_motto);
                            VBox VBox_intro_config1 = new VBox();
                            VBox_intro_config1.getStyleClass().add("Alignment-bottomCenter"); VBox_intro_config1.setId("VBox-intro-config1");
                            ObservableList<Node> VBox_intro_config1_Children = VBox_intro_config1.getChildren();
                                    Text Text_intro_generalSettings = new Text("General Settings");
                                    Text_intro_generalSettings.getStyleClass().addAll("Font-size-S", "Font-family-header", "Effect-dropshadow");
                                    HBox HBox_intro_config1Body = new HBox();
                                    HBox_intro_config1Body.getStyleClass().add("Alignment-center"); HBox_intro_config1Body.setId("HBox-intro-config1Body");
                                    ObservableList<Node> HBox_intro_config1Body_Children = HBox_intro_config1Body.getChildren();
                                            VBox VBox_intro_config1Labels = new VBox();
                                            VBox_intro_config1Labels.getStyleClass().add("Alignment-leftCenter"); VBox_intro_config1Labels.setId("VBox-intro-config1Labels");
                                            ObservableList<Node> VBox_intro_config1Labels_Children = VBox_intro_config1Labels.getChildren();
                                                    Label Label_intro_playersNr = new Label("Nr. of players:");
                                                    Label_intro_playersNr.getStyleClass().addAll("Font-size-S", "Effect-dropshadow");
                                                    Label Label_intro_mapSize = new Label("Map size:");
                                                    Label_intro_mapSize.getStyleClass().addAll("Font-size-S", "Effect-dropshadow");
                                                    Label Label_intro_resDensity = new Label("Resources density:");
                                                    Label_intro_resDensity.getStyleClass().addAll("Font-size-S", "Effect-dropshadow");
                                                    VBox_intro_config1Labels_Children.addAll(Label_intro_playersNr, Label_intro_mapSize, Label_intro_resDensity);
                                            VBox VBox_intro_config1Choices = new VBox();
                                            VBox_intro_config1Choices.getStyleClass().add("Alignment-rightCenter"); VBox_intro_config1Choices.setId("VBox-intro-config1Choices");
                                            ObservableList<Node> VBox_intro_config1choices_Children = VBox_intro_config1Choices.getChildren();
                                                    Accordion Accordion_intro_playersSettings = new Accordion();
                                                    ChoiceBox<Integer> ChoiceBox_intro_playersNr = IntroUI.createPlayersNrChoiceBox(Accordion_intro_playersSettings);
                                                    ChoiceBox_intro_playersNr.getStyleClass().add("ChoiceBox-gameTheme"); ChoiceBox_intro_playersNr.setId("ChoiceBox-intro-playersNr");
                                                    ChoiceBox<String> ChoiceBox_intro_mapSize = IntroUI.createMapSizeChoiceBox();
                                                    ChoiceBox_intro_mapSize.getStyleClass().add("ChoiceBox-gameTheme"); ChoiceBox_intro_mapSize.setId("ChoiceBox-intro-mapSize");
                                                    ChoiceBox<String> ChoiceBox_intro_resDensity = IntroUI.createResDensityChoiceBox();
                                                    ChoiceBox_intro_resDensity.getStyleClass().add("ChoiceBox-gameTheme"); ChoiceBox_intro_resDensity.setId("ChoiceBox-intro-resDensity");
                                                    VBox_intro_config1choices_Children.addAll(ChoiceBox_intro_playersNr, ChoiceBox_intro_mapSize, ChoiceBox_intro_resDensity);
                                            HBox_intro_config1Body_Children.addAll(VBox_intro_config1Labels, VBox_intro_config1Choices);
                                    VBox_intro_config1_Children.addAll(Text_intro_generalSettings, HBox_intro_config1Body);
                            VBox VBox_intro_config2 = new VBox();
                            VBox_intro_config2.getStyleClass().add("Alignment-topCenter"); VBox_intro_config2.setId("VBox-intro-config2");
                            ObservableList<Node> VBox_intro_config2_Children = VBox_intro_config2.getChildren();
                                    Text Text_intro_playerSettings = new Text("Players Settings");
                                    Text_intro_playerSettings.getStyleClass().addAll("Font-size-S", "Font-family-header", "Effect-dropshadow");
                                    VBox VBox_intro_config2Body = new VBox();
                                    VBox_intro_config2Body.getStyleClass().add("Alignment-center"); VBox_intro_config2Body.setId("VBox-intro-config2Body");
                                    ObservableList<Node> VBox_intro_config2Body_Children = VBox_intro_config2Body.getChildren();
                                            Accordion_intro_playersSettings.getStyleClass().add("Accordion-gameTheme"); Accordion_intro_playersSettings.setId("Accordion-intro-playersSettings");
                                            VBox_intro_config2Body_Children.add(Accordion_intro_playersSettings);
                                    VBox_intro_config2_Children.addAll(Text_intro_playerSettings, VBox_intro_config2Body);
                            HBox HBox_intro_buttons = new HBox();
                            HBox_intro_buttons.getStyleClass().add("Alignment-center"); HBox_intro_buttons.setId("HBox-intro-buttons");
                            ObservableList<Node> VBox_intro_buttons_Children = HBox_intro_buttons.getChildren();
                                    Button Button_intro_play = new Button("PLAY");
                                    Button_intro_play.getStyleClass().addAll("Button-gameTheme", "Font-size-S"); Button_intro_play.setId("Button-intro-play");
                                    Button Button_intro_quit = new Button("QUIT");
                                    Button_intro_quit.getStyleClass().addAll("Button-gameTheme", "Font-size-S"); Button_intro_quit.setId("Button-intro-quit");
                                    VBox_intro_buttons_Children.addAll(Button_intro_play, Button_intro_quit);
                            VBox_intro_mainPanel_Children.addAll(VBox_intro_welcome, VBox_intro_config1, VBox_intro_config2, HBox_intro_buttons);
                    HBox_intro_root_Children.add(VBox_intro_mainPanel);
            return HBox_intro_root;
        }

        private static ChoiceBox<Integer> createPlayersNrChoiceBox(Accordion Accordion_intro_playersSettings) {
            ChoiceBox<Integer> ChoiceBox_intro_playersNr = new ChoiceBox<>();
            ObservableList<Integer> ChoiceBox_intro_playersNr_items = ChoiceBox_intro_playersNr.getItems();
            int playersNrMin = Game.getPlayersNrMin();
            for (int i = playersNrMin; i <= Game.getPlayersNrMax(); i++) {
                ChoiceBox_intro_playersNr_items.add(i);
            }
            ChoiceBox_intro_playersNr.setValue(playersNrMin);
            IntroUI.updatePlayersSettingsAccordion(playersNrMin, Accordion_intro_playersSettings);
            ChoiceBox_intro_playersNr.valueProperty().addListener(new ChangeListener<Object>() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    IntroUI.updatePlayersSettingsAccordion((int) newValue, Accordion_intro_playersSettings);
                }
            });
            return ChoiceBox_intro_playersNr;
        }

        private static ChoiceBox<String> createMapSizeChoiceBox() {
            Set<String> mapSizes = Map.getMapSizes();
            ChoiceBox<String> ChoiceBox_intro_mapSize = new ChoiceBox<>(FXCollections.observableArrayList(mapSizes));
            ChoiceBox_intro_mapSize.setValue(mapSizes.iterator().next());
            return ChoiceBox_intro_mapSize;
        }

        private static ChoiceBox<String> createResDensityChoiceBox() {
            Set<String> resDensities = Map.getResDensities();
            ChoiceBox<String> ChoiceBox_intro_resDensity = new ChoiceBox<>(FXCollections.observableArrayList(resDensities));
            ChoiceBox_intro_resDensity.setValue(resDensities.iterator().next());
            return ChoiceBox_intro_resDensity;
        }

        private static void updatePlayersSettingsAccordion(int playersNr, Accordion Accordion_intro_playersSettings) {
            ObservableList<TitledPane> playersTitledPanes = Accordion_intro_playersSettings.getPanes();
            playersTitledPanes.clear();

            for (int i = 1; i <= playersNr; ++i) {
                HBox HBox_intro_playerConfig = new HBox();
                HBox_intro_playerConfig.getStyleClass().add("Alignment-center");
                ObservableList<Node> HBox_intro_playerConfig_Children = HBox_intro_playerConfig.getChildren();
                        VBox VBox_intro_playerLabels = new VBox();
                        VBox_intro_playerLabels.getStyleClass().addAll("Alignment-leftCenter", "VBox-intro-playerLabels");
                        ObservableList<Node> VBox_intro_playerLabels_Children = VBox_intro_playerLabels.getChildren();
                                Label Label_intro_playerName = new Label("Name:");
                                Label_intro_playerName.getStyleClass().addAll("Font-size-S", "Effect-dropshadow");
                                Label Label_playerColor = new Label("Color:");
                                Label_playerColor.getStyleClass().addAll("Font-size-S", "Effect-dropshadow");
                                VBox_intro_playerLabels_Children.addAll(Label_intro_playerName, Label_playerColor);
                        VBox VBox_intro_playerInputs = new VBox();
                        VBox_intro_playerInputs.getStyleClass().addAll("Alignment-rightCenter", "VBox-intro-playerInputs");
                        ObservableList<Node> VBox_intro_playerInputs_Children = VBox_intro_playerInputs.getChildren();
                                TextField TextField_intro_playerName = new TextField("Player #" + i);
                                TextField_intro_playerName.getStyleClass().add("TextField-gameTheme"); TextField_intro_playerName.setId("TextField-intro-player" + i + "Name");
                                HBox HBox_intro_playerColor = new HBox();
                                HBox_intro_playerColor.getStyleClass().addAll("Alignment-center", "HBox-intro-playerColor");
                                ObservableList<Node> HBox_intro_playerColor_Children = HBox_intro_playerColor.getChildren();
                                        ImageView img = new ImageView(); img.setFitWidth(30); img.setFitHeight(30);
                                        ChoiceBox<String> ChoiceBox_intro_playerColor = createPlayerColorChoiceBox(i, img);
                                        ChoiceBox_intro_playerColor.getStyleClass().addAll("ChoiceBox-gameTheme"); ChoiceBox_intro_playerColor.setId("ChoiceBox-intro-player" + i + "Color");
                                        HBox_intro_playerColor_Children.addAll(ChoiceBox_intro_playerColor, img);
                                VBox_intro_playerInputs_Children.addAll(TextField_intro_playerName, HBox_intro_playerColor);
                        HBox_intro_playerConfig_Children.addAll(VBox_intro_playerLabels, VBox_intro_playerInputs);
                playersTitledPanes.add(new TitledPane("Player #" + i, HBox_intro_playerConfig));
            }
        }

        private static ChoiceBox<String> createPlayerColorChoiceBox(int playerNr, ImageView img) {
            String[] playerColors = Player.getPlayersColors();
            String color = playerColors[playerNr - 1];
            ChoiceBox<String> ChoiceBox_playerColors = new ChoiceBox<>(FXCollections.observableArrayList(playerColors));
            ChoiceBox_playerColors.setValue(color);
            img.setImage(ImageLoader.getIcon("Player",color));
            ChoiceBox_playerColors.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    img.setImage(ImageLoader.getIcon("Player",newValue));
                }
            });
            return ChoiceBox_playerColors;
        }
    }

    public static class InGameUI {
        public static Parent createInGameUI(Game g) {
            HBox HBox_ingame_root = new HBox();
            HBox_ingame_root.getStyleClass().add("Background-gameTheme"); HBox_ingame_root.setId("HBox-ingame-root");
            ObservableList<Node> HBox_ingame_root_Children = HBox_ingame_root.getChildren();
                    VBox VBox_ingame_left = new VBox();
                    VBox_ingame_left.getStyleClass().add("Alignment-center"); VBox_ingame_left.setId("VBox-ingame-left");
                    ObservableList<Node> VBox_ingame_left_Children = VBox_ingame_left.getChildren();
                            VBox VBox_ingame_leftUpper = new VBox();
                            VBox_ingame_leftUpper.getStyleClass().add("Alignment-topCenter"); VBox_ingame_leftUpper.setId("VBox-ingame-leftUpper");
                            ObservableList<Node> VBox_ingame_leftUpper_Children = VBox_ingame_leftUpper.getChildren();
                                    Text Text_ingame_playersPanels = new Text("Players Panels");
                                    Text_ingame_playersPanels.getStyleClass().addAll("Font-size-S", "Font-family-header", "Effect-dropshadow");
                                    Accordion Accordion_ingame_playersPanels = playersAsAccordion(g.getPlayers());
                                    Accordion_ingame_playersPanels.getStyleClass().add("Accordion-gameTheme"); Accordion_ingame_playersPanels.setId("Accordion-ingame-playersPanels");
                                    VBox_ingame_leftUpper_Children.addAll(Text_ingame_playersPanels, Accordion_ingame_playersPanels);
                            VBox VBox_ingame_leftLower = new VBox();
                            VBox_ingame_leftLower.getStyleClass().add("Alignment-topCenter"); VBox_ingame_leftLower.setId("VBox-ingame-leftLower");
                            ObservableList<Node> VBox_ingame_leftLower_Children = VBox_ingame_leftLower.getChildren();
                                Text Text_ingame_globalLog = new Text("Global Event Log");
                                Text_ingame_globalLog.getStyleClass().addAll("Font-size-S", "Font-family-header", "Effect-dropshadow");
                                TextArea TextArea_ingame_globalLog = messageLogAsTextArea(MessagingSystem.MESSAGE_LOG);
                                TextArea_ingame_globalLog.getStyleClass().add("TextArea-gameTheme"); TextArea_ingame_globalLog.setId("TextArea-ingame-globalLog");
                                VBox_ingame_leftLower_Children.addAll(Text_ingame_globalLog, TextArea_ingame_globalLog);
                            VBox_ingame_left_Children.addAll(VBox_ingame_leftUpper, VBox_ingame_leftLower);
                    VBox VBox_ingame_right = new VBox();
                    VBox_ingame_right.getStyleClass().add("Alignment-topCenter");
                    VBox_ingame_right.setId("VBox-ingame-right");
                    ObservableList<Node> VBox_ingame_right_Children = VBox_ingame_right.getChildren();
                            Text Text_ingame_pixelMap = new Text("PIXEL Map");
                            Text_ingame_pixelMap.getStyleClass().addAll("Font-size-2XL","Font-family-title","Effect-dropshadow");
                            GridPane GridPane_ingame_mapPane = mapAsPane(g.getMap());
                            GridPane_ingame_mapPane.setId("GridPane-ingame-mapPane");
                            Button Button_ingame_reset = new Button("RESET");
                            Button_ingame_reset.getStyleClass().addAll("Button-gameTheme", "Font-size-S"); Button_ingame_reset.setId("Button-ingame-reset");
                            VBox_ingame_right_Children.addAll(Text_ingame_pixelMap,GridPane_ingame_mapPane,Button_ingame_reset);
                    HBox_ingame_root_Children.addAll(VBox_ingame_left, VBox_ingame_right);
            return HBox_ingame_root;
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