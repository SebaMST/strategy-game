package PixelWars.GUI;

import PixelWars.GUI.CustomFX.ZoomableScrollPane;
import PixelWars.GUI.Events.Capturers.Capturer_ImageView;
import PixelWars.GUI.Events.Capturers.Capturer_TextField;
import PixelWars.GUI.Events.Capturers.Capturer_TextFlow;
import PixelWars.GameLogic.Factory.BuildingFactory;
import PixelWars.GameLogic.Factory.ResourceFactory;
import PixelWars.GameLogic.Game;
import PixelWars.GameLogic.MapLogic.Map;
import PixelWars.GameLogic.MapLogic.MapBuilder;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Building;
import PixelWars.GameLogic.MapLogic.MapEntities.MapEntity;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;
import PixelWars.GameLogic.MapLogic.MapEntities.Resources.ResourceBank;
import PixelWars.GameLogic.MapLogic.Point;
import PixelWars.GameLogic.Messaging.MessageLog;
import PixelWars.GameLogic.Messaging.MessagingSystem;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class GameUI {
    private static final double SCREEN_WIDTH, SCREEN_HEIGHT;

    static {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        SCREEN_WIDTH = screenSize.getWidth();
        SCREEN_HEIGHT = screenSize.getHeight();
    }

    public static void init()
    {
        new ImageLoader();
        new ColorUtils();
    }

    public static class IntroUI {

        public static Parent createIntroUI() { //creates structure and behaviour of the intro UI
            //region HBox_intro_root
            HBox HBox_intro_root = new HBox();
            HBox_intro_root.getStyleClass().addAll("Alignment-center", "Background-gameTheme"); HBox_intro_root.setId("HBox-intro-root");
                    //region VBox_intro_mainPanel
                    VBox VBox_intro_mainPanel = new VBox();
                    VBox_intro_mainPanel.getStyleClass().add("Alignment-center"); VBox_intro_mainPanel.setId("VBox-intro-mainPanel");
                            //region VBox_intro_welcome
                            VBox VBox_intro_welcome = new VBox();
                            VBox_intro_welcome.getStyleClass().add("Alignment-center"); VBox_intro_welcome.setId("VBox-intro-welcome");
                                    //region Text_intro_title
                                    Text Text_intro_title = new Text("PIXEL Wars");
                                    Text_intro_title.getStyleClass().addAll("Font-size-3XL", "Font-family-title", "Effect-dropshadow");
                                    //endregion
                                    //region Text_intro_motto
                                    Text Text_intro_motto = new Text("The PIXEL world awaits you: Grind, Build, Conquer!");
                                    Text_intro_motto.getStyleClass().addAll("Font-size-M", "Effect-dropshadow");
                                    //endregion
                            VBox_intro_welcome.getChildren().addAll(Text_intro_title, Text_intro_motto);
                            //endregion
                            //region VBox_intro_config1
                            VBox VBox_intro_config1 = new VBox();
                            VBox_intro_config1.getStyleClass().add("Alignment-bottomCenter"); VBox_intro_config1.setId("VBox-intro-config1");
                                    //region Text_intro_generalSettings
                                    Text Text_intro_generalSettings = new Text("General Settings");
                                    Text_intro_generalSettings.getStyleClass().addAll("Font-size-S", "Font-family-header", "Effect-dropshadow");
                                    //endregion
                                    //region HBox_intro_config1Body
                                    HBox HBox_intro_config1Body = new HBox();
                                    HBox_intro_config1Body.getStyleClass().add("Alignment-center"); HBox_intro_config1Body.setId("HBox-intro-config1Body");
                                            //region VBox_intro_config1Labels
                                            VBox VBox_intro_config1Labels = new VBox();
                                            VBox_intro_config1Labels.getStyleClass().add("Alignment-leftCenter"); VBox_intro_config1Labels.setId("VBox-intro-config1Labels");
                                                    //region Label_intro_playersNr
                                                    Label Label_intro_playersNr = new Label("Nr. of players:");
                                                    Label_intro_playersNr.getStyleClass().addAll("Font-size-S", "Effect-dropshadow");
                                                    //endregion
                                                    //region Label_intro_mapSize
                                                    Label Label_intro_mapSize = new Label("Map size:");
                                                    Label_intro_mapSize.getStyleClass().addAll("Font-size-S", "Effect-dropshadow");
                                                    //endregion
                                                    //region Label_intro_resDesnity
                                                    Label Label_intro_resDensity = new Label("Resources density:");
                                                    Label_intro_resDensity.getStyleClass().addAll("Font-size-S", "Effect-dropshadow");
                                                    //endregion
                                            VBox_intro_config1Labels.getChildren().addAll(Label_intro_playersNr, Label_intro_mapSize, Label_intro_resDensity);
                                            //endregion
                                            //region VBox_intro_config1Choices
                                            VBox VBox_intro_config1Choices = new VBox();
                                            VBox_intro_config1Choices.getStyleClass().add("Alignment-rightCenter"); VBox_intro_config1Choices.setId("VBox-intro-config1Choices");
                                                    //region ChoiceBox_intro_playersNr
                                                    Accordion Accordion_intro_playersSettings = new Accordion();
                                                    ChoiceBox<Integer> ChoiceBox_intro_playersNr = IntroUI.createChoiceBoxPlayersNr(Accordion_intro_playersSettings);
                                                    ChoiceBox_intro_playersNr.getStyleClass().add("ChoiceBox-gameTheme"); ChoiceBox_intro_playersNr.setId("ChoiceBox-intro-playersNr");
                                                    //endregion
                                                    //region ChoiceBox_intro_mapSize
                                                    ChoiceBox<String> ChoiceBox_intro_mapSize = IntroUI.createChoiceBoxMapSize();
                                                    ChoiceBox_intro_mapSize.getStyleClass().add("ChoiceBox-gameTheme"); ChoiceBox_intro_mapSize.setId("ChoiceBox-intro-mapSize");
                                                    //endregion
                                                    //region ChoiceBox_intro_resDensity
                                                    ChoiceBox<String> ChoiceBox_intro_resDensity = IntroUI.createChoiceBoxResDensity();
                                                    ChoiceBox_intro_resDensity.getStyleClass().add("ChoiceBox-gameTheme"); ChoiceBox_intro_resDensity.setId("ChoiceBox-intro-resDensity");
                                                    //endregion
                                            VBox_intro_config1Choices.getChildren().addAll(ChoiceBox_intro_playersNr, ChoiceBox_intro_mapSize, ChoiceBox_intro_resDensity);
                                            //endregion
                                    HBox_intro_config1Body.getChildren().addAll(VBox_intro_config1Labels, VBox_intro_config1Choices);
                                    //endregion
                            VBox_intro_config1.getChildren().addAll(Text_intro_generalSettings, HBox_intro_config1Body);
                            //endregion
                            //region VBox_intro_config2
                            VBox VBox_intro_config2 = new VBox();
                            VBox_intro_config2.getStyleClass().add("Alignment-topCenter"); VBox_intro_config2.setId("VBox-intro-config2");
                                    //region Text_intro_playerSettings
                                    Text Text_intro_playerSettings = new Text("Players Settings");
                                    Text_intro_playerSettings.getStyleClass().addAll("Font-size-S", "Font-family-header", "Effect-dropshadow");
                                    //endregion
                                    //region VBox_intro_config2Body
                                    VBox VBox_intro_config2Body = new VBox();
                                    VBox_intro_config2Body.getStyleClass().add("Alignment-center"); VBox_intro_config2Body.setId("VBox-intro-config2Body");
                                            Accordion_intro_playersSettings.getStyleClass().add("Accordion-gameTheme"); Accordion_intro_playersSettings.setId("Accordion-intro-playersSettings");
                                    VBox_intro_config2Body.getChildren().add(Accordion_intro_playersSettings);
                                    //endregion
                            VBox_intro_config2.getChildren().addAll(Text_intro_playerSettings, VBox_intro_config2Body);
                            //endregion
                            //region HBox_intro_buttons
                            HBox HBox_intro_buttons = new HBox();
                            HBox_intro_buttons.getStyleClass().add("Alignment-center"); HBox_intro_buttons.setId("HBox-intro-buttons");
                                    //region Button_intro_play
                                    Button Button_intro_play = new Button("PLAY");
                                    Button_intro_play.getStyleClass().addAll("Button-gameTheme", "Font-size-S"); Button_intro_play.setId("Button-intro-play");
                                    //endregion
                                    //region Button_intro_quit
                                    Button Button_intro_quit = new Button("QUIT");
                                    Button_intro_quit.getStyleClass().addAll("Button-gameTheme", "Font-size-S"); Button_intro_quit.setId("Button-intro-quit");
                                    //endregion
                            HBox_intro_buttons.getChildren().addAll(Button_intro_play, Button_intro_quit);
                            //endregion
                    VBox_intro_mainPanel.getChildren().addAll(VBox_intro_welcome, VBox_intro_config1, VBox_intro_config2, HBox_intro_buttons);
                    //endregion
            HBox_intro_root.getChildren().add(VBox_intro_mainPanel);
            //endregion
            return HBox_intro_root;
        }

        private static ChoiceBox<Integer> createChoiceBoxPlayersNr(Accordion Accordion_intro_playersSettings) {
            ChoiceBox<Integer> ChoiceBox_intro_playersNr = new ChoiceBox<>();
            ObservableList<Integer> ChoiceBox_intro_playersNr_items = ChoiceBox_intro_playersNr.getItems();
            int playersNrMin = Game.getPlayersNrMin();
            for (int i = playersNrMin; i <= Game.getPlayersNrMax(); i++) {
                ChoiceBox_intro_playersNr_items.add(i);
            }
            ChoiceBox_intro_playersNr.setValue(playersNrMin);
            IntroUI.updateAccordionPlayersSettings(playersNrMin, Accordion_intro_playersSettings);
            ChoiceBox_intro_playersNr.valueProperty().addListener((ChangeListener<Object>) (observable, oldValue, newValue) -> IntroUI.updateAccordionPlayersSettings((int) newValue, Accordion_intro_playersSettings));
            return ChoiceBox_intro_playersNr;
        }

        private static ChoiceBox<String> createChoiceBoxMapSize() {
            String[] mapSizes = MapBuilder.getMapSizes();
            ChoiceBox<String> ChoiceBox_intro_mapSize = new ChoiceBox<>(FXCollections.observableArrayList(mapSizes));
            ChoiceBox_intro_mapSize.setValue(mapSizes[0]);
            return ChoiceBox_intro_mapSize;
        }

        private static ChoiceBox<String> createChoiceBoxResDensity() {
            String[] resDensities = MapBuilder.getResourceDensities();
            ChoiceBox<String> ChoiceBox_intro_resDensity = new ChoiceBox<>(FXCollections.observableArrayList(resDensities));
            ChoiceBox_intro_resDensity.setValue(resDensities[0]);
            return ChoiceBox_intro_resDensity;
        }

        private static void updateAccordionPlayersSettings(int playersNr, Accordion Accordion_intro_playersSettings) {
            ObservableList<TitledPane> Accordion_intro_playerSettings_TitledPanes = Accordion_intro_playersSettings.getPanes();
            int oldPlayersNr = Accordion_intro_playerSettings_TitledPanes.size();
            if(playersNr>oldPlayersNr)
            {
                for (int i = oldPlayersNr+1; i <= playersNr; ++i) {
                    Accordion_intro_playerSettings_TitledPanes.add(createTitledPanePlayer(i));
                }
            }
            else if (playersNr<oldPlayersNr)
            {
                Accordion_intro_playerSettings_TitledPanes.remove(playersNr,oldPlayersNr);
            }
        }

        private static TitledPane createTitledPanePlayer(int orderNr) {
            TitledPane TitledPane_intro_player = new TitledPane();
            TitledPane_intro_player.setText("Player #" + orderNr);
                    //region HBox_intro_playerConfig
                    HBox HBox_intro_playerConfig = new HBox();
                    HBox_intro_playerConfig.getStyleClass().add("Alignment-center");
                            //region VBox_intro_playerLabels
                            VBox VBox_intro_playerLabels = new VBox();
                            VBox_intro_playerLabels.getStyleClass().addAll("Alignment-leftCenter", "VBox-intro-playerLabels");
                                    //region Label_intro_playerName
                                    Label Label_intro_playerName = new Label("Name:");
                                    Label_intro_playerName.getStyleClass().addAll("Font-size-S", "Effect-dropshadow");
                                    //endregion
                                    //region Label_intro_playerColor
                                    Label Label_intro_playerColor = new Label("Color:");
                                    Label_intro_playerColor.getStyleClass().addAll("Font-size-S", "Effect-dropshadow");
                                    //endregion
                            VBox_intro_playerLabels.getChildren().addAll(Label_intro_playerName, Label_intro_playerColor);
                            //endregion
                            //region VBox_intro_playerInputs
                            VBox VBox_intro_playerInputs = new VBox();
                            VBox_intro_playerInputs.getStyleClass().addAll("Alignment-rightCenter", "VBox-intro-playerInputs");
                                    //region TextField_intro_playerName
                                    TextField TextField_intro_playerName = new TextField("Player #" + orderNr);
                                    TextField_intro_playerName.getStyleClass().add("TextField-gameTheme"); TextField_intro_playerName.setId("TextField-intro-player" + orderNr + "Name");
                                    TextField_intro_playerName.textProperty().addListener((observable, oldValue, newValue) -> TitledPane_intro_player.setText(newValue));
                                    //endregion
                                    //region HBox_intro_playerColor
                                    HBox HBox_intro_playerColor = new HBox();
                                    HBox_intro_playerColor.getStyleClass().addAll("Alignment-center", "HBox-intro-playerColor");
                                            //region ChoiceBox_intro_playerColor
                                            ImageView img = new ImageView(); img.setFitWidth(30); img.setFitHeight(30);
                                            ChoiceBox<String> ChoiceBox_intro_playerColor = createChoiceBoxPlayerColor(orderNr, img);
                                            ChoiceBox_intro_playerColor.getStyleClass().addAll("ChoiceBox-gameTheme"); ChoiceBox_intro_playerColor.setId("ChoiceBox-intro-player" + orderNr + "Color");
                                            //endregion
                                    HBox_intro_playerColor.getChildren().addAll(ChoiceBox_intro_playerColor, img);
                                    //endregion
                            VBox_intro_playerInputs.getChildren().addAll(TextField_intro_playerName, HBox_intro_playerColor);
                            //endregion
                    HBox_intro_playerConfig.getChildren().addAll(VBox_intro_playerLabels, VBox_intro_playerInputs);
                    //endregion
            TitledPane_intro_player.setContent(HBox_intro_playerConfig);
            return TitledPane_intro_player;
        }

        private static ChoiceBox<String> createChoiceBoxPlayerColor(int playerNr, ImageView img) {
            String[] playerColors = Player.getPlayerColors();
            String color = playerColors[playerNr - 1];
            ChoiceBox<String> ChoiceBox_playerColors = new ChoiceBox<>(FXCollections.observableArrayList(playerColors));
            ChoiceBox_playerColors.setValue(color);
            img.setImage(ImageLoader.getIcon("player",color));
            ChoiceBox_playerColors.valueProperty().addListener((observable, oldValue, newValue) -> img.setImage(ImageLoader.getIcon("player",newValue)));
            return ChoiceBox_playerColors;
        }

    }

    public static class InGameUI {

        public static Parent createInGameUI(Game game) {
            //region VBox_ingame_root
            HBox HBox_ingame_root = new HBox();
            HBox_ingame_root.getStyleClass().add("Background-gameTheme"); HBox_ingame_root.setId("HBox-ingame-root");
                    //region VBox_ingame_left
                    VBox VBox_ingame_left = new VBox();
                    VBox_ingame_left.getStyleClass().add("Alignment-center"); VBox_ingame_left.setId("VBox-ingame-left");
                            //region VBox_ingame_leftUpper
                            VBox VBox_ingame_leftUpper = new VBox();
                            VBox_ingame_leftUpper.getStyleClass().add("Alignment-topCenter"); VBox_ingame_leftUpper.setId("VBox-ingame-leftUpper");
                                    //region Text_ingame_playerPanels
                                    Text Text_ingame_playersPanels = new Text("Players Panels");
                                    Text_ingame_playersPanels.getStyleClass().addAll("Font-size-S", "Font-family-header", "Effect-dropshadow");
                                    //endregion
                                    //region Accordion_ingame_playerPanels
                                    Accordion Accordion_ingame_playersPanels = createAccordionPlayerPanels(game.getPlayers());
                                    Accordion_ingame_playersPanels.getStyleClass().add("Accordion-gameTheme"); Accordion_ingame_playersPanels.setId("Accordion-ingame-playersPanels");
                                    //endregion
                            VBox_ingame_leftUpper.getChildren().addAll(Text_ingame_playersPanels, Accordion_ingame_playersPanels);
                            //endregion
                            //region VBox_ingame_leftLower
                            VBox VBox_ingame_leftLower = new VBox();
                            VBox_ingame_leftLower.getStyleClass().add("Alignment-topCenter"); VBox_ingame_leftLower.setId("VBox-ingame-leftLower");
                                    //region Text_ingame_globalLog
                                    Text Text_ingame_globalLog = new Text("Global Event Log");
                                    Text_ingame_globalLog.getStyleClass().addAll("Font-size-S", "Font-family-header", "Effect-dropshadow");
                                    //endregion
                                    //region ScrollPane_ingame_globalLog
                                    ScrollPane ScrollPane_ingame_globalLog = new ScrollPane(createCapturerTextFlowMessageLog(MessagingSystem.getGlobalLog()));
                                    ScrollPane_ingame_globalLog.getStyleClass().add("ScrollPane-gameTheme"); ScrollPane_ingame_globalLog.setId("ScrollPane-ingame-globalLog");
                                    //endregion
                            VBox_ingame_leftLower.getChildren().addAll(Text_ingame_globalLog, ScrollPane_ingame_globalLog);
                            //endregion
                    VBox_ingame_left.getChildren().addAll(VBox_ingame_leftUpper, VBox_ingame_leftLower);
                    //endregion
                    //region VBox_ingame_right
                    VBox VBox_ingame_right = new VBox();
                    VBox_ingame_right.getStyleClass().add("Alignment-center"); VBox_ingame_right.setId("VBox-ingame-right");
                            //region Text_ingame_pixelMap
                            Text Text_ingame_pixelMap = new Text("PIXEL Map");
                            Text_ingame_pixelMap.getStyleClass().addAll("Font-size-S","Font-family-header","Effect-dropshadow");
                            //endregion
                            //region ZoomableScrollPane_ingame_map
                            ZoomableScrollPane ZoomableScrollPane_ingame_map=new ZoomableScrollPane();
                            ZoomableScrollPane_ingame_map.getStyleClass().add("ZoomableScrollPane-gameTheme"); ZoomableScrollPane_ingame_map.setId("ZoomableScrollPane-ingame-map");
                                    //region Pane_ingame_mapContainer
                                    Pane Pane_ingame_mapContainer = new Pane();
                                            //region Pane_ingame_mapTerrain
                                            Pane Pane_ingame_mapTerrain = new Pane();
                                            Pane_ingame_mapTerrain.setId("Pane-ingame-mapTerrain");
                                            Service<Void> bg1 = new Service<Void>() {
                                                @Override
                                                protected Task<Void> createTask() {
                                                    return new Task<Void>() {
                                                        @Override
                                                        protected Void call() {
                                                            createPaneMapTerrain(game.getMap(),Pane_ingame_mapTerrain);
                                                            return null;
                                                        }
                                                    };
                                                }
                                            };
                                            bg1.setOnSucceeded(event -> {
                                                Pane_ingame_mapContainer.getChildren().add(Pane_ingame_mapTerrain);
                                                Pane_ingame_mapTerrain.toBack();
                                                ZoomableScrollPane_ingame_map.setInside(Pane_ingame_mapContainer);
                                            });
                                            bg1.start();
                                            //endregion
                                            //region Pane_ingame_mapEntities
                                            Pane Pane_ingame_mapEntities = new Pane();
                                            Pane_ingame_mapEntities.setId("Pane-ingame-mapEntities");
                                            Service<Void> bg2 = new Service<Void>() {
                                                @Override
                                                protected Task<Void> createTask() {
                                                    return new Task<Void>() {
                                                        @Override
                                                        protected Void call() {
                                                            createPaneMapEntities(game.getMap(),Pane_ingame_mapEntities);
                                                            return null;
                                                        }
                                                    };
                                                }
                                            };
                                            bg2.setOnSucceeded(event -> {
                                                Pane_ingame_mapContainer.getChildren().add(Pane_ingame_mapEntities);
                                                Pane_ingame_mapEntities.toFront();
                                            });
                                            bg2.start();
                                            //endregion
                                    //endregion
                                    //region Text_ingame_mapLoading
                                    Text Text_ingame_mapLoading = new Text("LOADING...");
                                    Text_ingame_mapLoading.getStyleClass().addAll("Font-size-2XL","Effect-dropshadow"); Text_ingame_mapLoading.setId("Text-ingame-mapLoading");
                                    //endregion
                            ZoomableScrollPane_ingame_map.setInside(Text_ingame_mapLoading);
                            //endregion
                            //region HBox_ingame_buttons
                            HBox HBox_ingame_buttons = new HBox();
                            HBox_ingame_buttons.getStyleClass().add("Alignment-center"); HBox_ingame_buttons.setId("HBox-ingame-buttons");
                                    //region Button_ingame_begin
                                    Button Button_ingame_begin = new Button("BEGIN");
                                    Button_ingame_begin.getStyleClass().addAll("Button-gameTheme","Font-size-S"); Button_ingame_begin.setId("Button-ingame-begin");
                                    //endregion
                                    //region Button_ingame_reset
                                    Button Button_ingame_reset = new Button("RESET");
                                    Button_ingame_reset.getStyleClass().addAll("Button-gameTheme", "Font-size-S"); Button_ingame_reset.setId("Button-ingame-reset");
                                    //endregion
                            HBox_ingame_buttons.getChildren().addAll(Button_ingame_begin,Button_ingame_reset);
                            //endregion
                    VBox_ingame_right.getChildren().addAll(Text_ingame_pixelMap,ZoomableScrollPane_ingame_map,HBox_ingame_buttons);
                    //endregion
            HBox_ingame_root.getChildren().addAll(VBox_ingame_left, VBox_ingame_right);
            //endregion
            return HBox_ingame_root;
        }

        private static void createPaneMapTerrain(Map map, Pane Pane_ingame_mapTerrain) {
            int tileSize = 12;
            ObservableList<Node> Pane_ingame_mapTerrain_Children = Pane_ingame_mapTerrain.getChildren();
            for (int i = 0; i < map.getHeight(); i++) {
                for (int j = 0; j < map.getWidth(); j++) {
                    Rectangle a = new Rectangle(tileSize,tileSize);
                    a.setFill(map.getTerrainAtCoords(new Point(j,i)).getColor());
                    a.setLayoutX(j*tileSize); a.setLayoutY(i*tileSize);
                    Pane_ingame_mapTerrain_Children.add(a);
                }
            }
        }

        private static void createPaneMapEntities(Map map, Pane Pane_ingame_mapEntities) {
            double tileSize=12;
            ObservableList<Node> Pane_ingame_mapEntities_Children=Pane_ingame_mapEntities.getChildren();

            ContextMenu contextMenu = new ContextMenu();
            contextMenu.getStyleClass().add("ContextMenu-gameTheme");
            MenuItem me = new MenuItem();
            me.getStyleClass().add("MenuItem-gameTheme");
            contextMenu.getItems().add(me);

            for(int i=0;i<map.getHeight();i++) {
                for(int j=0;j<map.getWidth();j++) {

                    Point p = new Point(j,i);
                    if(map.getTerrainAtCoords(p).isOperational()) {
                        Capturer_ImageView civ = new Capturer_ImageView();
                        civ.setFitWidth(tileSize); civ.setFitHeight(tileSize);
                        civ.setLayoutX(j*tileSize); civ.setLayoutY(i*tileSize);
                        int x=j,y=i;

                        EventHandler<MouseEvent> enter = event -> {
                            contextMenu.show(civ, Side.BOTTOM,event.getX(),event.getY());
                            MapEntity entity = map.getMapEntityAtCoords(p);
                            String details="";
                            if(entity instanceof Player)
                                details+="Name: "+((Player) entity).getName()+"\nColor: "+((Player) entity).getColor();
                            else if(entity instanceof ResourceBank)
                                details+=entity.getConcreteName()+"\nDurability: "+((ResourceBank) entity).getDurability();
                            else if(entity instanceof Building)
                                details+=entity.getConcreteName()+"\nOwner: "+((Building)entity).getOwner().getName()+" ("+((Building)entity).getOwner().getColor()+")";
                            details+="\nX:"+x+",Y:"+y;
                            contextMenu.getItems().get(0).setText(details);
                        };
                        civ.setOnMouseEntered(enter);

                        EventHandler<MouseEvent> exit = event -> contextMenu.hide();
                        civ.setOnMouseExited(exit);

                        map.getEventBroadcasterAtCoords(p).addEventCapturer(civ);
                        Pane_ingame_mapEntities_Children.add(civ);
                    }
                }
            }
        }

        private static Accordion createAccordionPlayerPanels(List<Player> players) {
            Accordion Accordion_ingame_playerPanels = new Accordion();
            ObservableList<TitledPane> Accordion_ingame_playerPanels_TitledPanes = Accordion_ingame_playerPanels.getPanes();

            for (Player p : players) {
                //region VBox_ingame_playerPanel
                VBox VBox_ingame_playerPanel = new VBox();
                VBox_ingame_playerPanel.getStyleClass().addAll("Alignment-topCenter","VBox-ingame-playerPanel");
                        HBox HBox_ingame_playerMainDetails = createHBoxPlayerMainDetails(p);
                        VBox VBox_ingame_playerResources = new VBox();
                        VBox_ingame_playerResources.getStyleClass().add("Alignment-topCenter");
                                Label Label_ingame_resources = new Label("Resources");
                                Label_ingame_resources.getStyleClass().addAll("Font-size-S","Effect-dropshadow");
                                HBox HBox_ingame_playerResourceBar = createHBoxPlayerResourceBar(p);
                        VBox_ingame_playerResources.getChildren().addAll(Label_ingame_resources,HBox_ingame_playerResourceBar);
                        VBox VBox_ingame_playerBuildings = new VBox();
                        VBox_ingame_playerBuildings.getStyleClass().add("Alignment-topCenter");
                                Label Label_ingame_buildings = new Label("Buildings");
                                Label_ingame_buildings.getStyleClass().addAll("Font-size-S","Effect-dropshadow");
                                ScrollPane ScrollPane_ingame_playerBuildingBar = createScrollPanePlayerBuildingBar(p);
                        VBox_ingame_playerBuildings.getChildren().addAll(Label_ingame_buildings,ScrollPane_ingame_playerBuildingBar);
                VBox_ingame_playerPanel.getChildren().addAll(HBox_ingame_playerMainDetails,VBox_ingame_playerResources,VBox_ingame_playerBuildings);
                //endregion
                Accordion_ingame_playerPanels_TitledPanes.add(new TitledPane(p.getName(), VBox_ingame_playerPanel));
            }
            return Accordion_ingame_playerPanels;
        }

        private static HBox createHBoxPlayerMainDetails(Player p) {
            HBox HBox_ingame_playerMainDetails = new HBox();
            HBox_ingame_playerMainDetails.getStyleClass().addAll("Alignment-center","HBox-ingame-playerMainDetails");
                    ImageView img = new ImageView(p.getIcon());
                    Label Label_ingame_playerPos = new Label("Position:");
                    Label_ingame_playerPos.getStyleClass().addAll("Font-size-S","Effect-dropshadow");
                    Capturer_TextField Capturer_TextField_ingame_playerPos = new Capturer_TextField();
                    Capturer_TextField_ingame_playerPos.getStyleClass().add("TextField-gameTheme");
                    Capturer_TextField_ingame_playerPos.setEditable(false);
                    p.getEventBroadcaster().addEventCapturer(Capturer_TextField_ingame_playerPos);
            HBox_ingame_playerMainDetails.getChildren().addAll(img,Label_ingame_playerPos,Capturer_TextField_ingame_playerPos);

            return HBox_ingame_playerMainDetails;
        }

        private static HBox createHBoxPlayerResourceBar(Player p) {
            HBox HBox_ingame_playerResourceBar = new HBox();
            HBox_ingame_playerResourceBar.getStyleClass().addAll("Alignment-center","HBox-ingame-playerResourceBar");
            ObservableList<Node> HBox_ingame_playerResourceBar_Children = HBox_ingame_playerResourceBar.getChildren();
            for(String res : ResourceFactory.getResourceTypes())
            {
                VBox VBox_ingame_resource = new VBox();
                VBox_ingame_resource.getStyleClass().addAll("Alignment-center","VBox-ingame-resource");
                        HBox HBox_ingame_resname = new HBox();
                        HBox_ingame_resname.getStyleClass().add("Alignment-center");
                                ImageView iv = new ImageView(ImageLoader.getIcon("resource",res.toLowerCase()));
                                iv.setFitWidth(16); iv.setFitHeight(16);
                        HBox_ingame_resname.getChildren().addAll(iv,new Label(res.substring(0,res.length()-12)));
                        Capturer_TextField Capturer_TextField_ingame_resnr = new Capturer_TextField();
                        Capturer_TextField_ingame_resnr.getStyleClass().add("TextField-gameTheme-small");
                        Capturer_TextField_ingame_resnr.setEditable(false);
                        p.getResourceBar().get(res).getEventBroadcaster().addEventCapturer(Capturer_TextField_ingame_resnr);
                VBox_ingame_resource.getChildren().addAll(HBox_ingame_resname,Capturer_TextField_ingame_resnr);
                HBox_ingame_playerResourceBar_Children.add(VBox_ingame_resource);
            }
            return HBox_ingame_playerResourceBar;
        }

        private static ScrollPane createScrollPanePlayerBuildingBar(Player p) {
            ScrollPane ScrollPane_ingame_playerBuildingBar = new ScrollPane();
            ScrollPane_ingame_playerBuildingBar.getStyleClass().addAll("ScrollPane-gameTheme","ScrollPane-ingame-playerBuildingBar");
                VBox VBox_ingame_playerBuildingBar=new VBox();
                VBox_ingame_playerBuildingBar.getStyleClass().addAll("VBox-ingame-playerBuildingBar");
                ObservableList<Node> VBox_ingame_playerBuildingBar_Children = VBox_ingame_playerBuildingBar.getChildren();
                for(String building: BuildingFactory.getBuildingTypes())
                {
                    VBox VBox_ingame_building=new VBox();
                    VBox_ingame_building.getStyleClass().addAll("Alignment-leftCenter","VBox-ingame-building");
                            HBox HBox_ingame_buildingTitle=new HBox();
                            HBox_ingame_buildingTitle.getStyleClass().addAll("Alignment-leftCenter","HBox-ingame-buildingTitle");
                                    ImageView iv = new ImageView(ImageLoader.getIcon(building.toLowerCase(),p.getColor()));
                                    Label Label_ingame_buildingname=new Label(building+"  - ");
                                    Label_ingame_buildingname.getStyleClass().addAll("Font-size-2XS","Effect-dropshadow");
                            HBox_ingame_buildingTitle.getChildren().addAll(iv,Label_ingame_buildingname);
                                    for(HashMap.Entry<String,Integer> resourceEntry : Objects.requireNonNull(BuildingFactory.getRequirements(building)).getRequiredResources().entrySet())
                                    {
                                        ImageView resView = new ImageView(ImageLoader.getIcon("resource",resourceEntry.getKey().toLowerCase()));
                                        resView.setFitWidth(16); resView.setFitHeight(16);
                                        Label resNeededCnt = new Label(""+resourceEntry.getValue());
                                        HBox_ingame_buildingTitle.getChildren().addAll(resView,resNeededCnt);
                                    }
                            HBox HBox_ingame_buildingCount=new HBox();
                            HBox_ingame_buildingCount.getStyleClass().addAll("Alignment-leftCenter","HBox-ingame-buildingTitle");
                                    Label Label_ingame_owned = new Label("Owned: ");
                                    Label_ingame_owned.getStyleClass().addAll("Font-size-2XS","Effect-dropshadow");
                                    Capturer_TextField Capturer_TextField_ingame_ownedCount = new Capturer_TextField();
                                    Capturer_TextField_ingame_ownedCount.getStyleClass().add("TextField-gameTheme-small");
                                    Capturer_TextField_ingame_ownedCount.setEditable(false);
                                    p.getBuildingBar().get(building).getEventBroadcaster().addEventCapturer(Capturer_TextField_ingame_ownedCount);
                            HBox_ingame_buildingCount.getChildren().addAll(Label_ingame_owned,Capturer_TextField_ingame_ownedCount);
                    VBox_ingame_building.getChildren().addAll(HBox_ingame_buildingTitle,HBox_ingame_buildingCount);
                    VBox_ingame_playerBuildingBar_Children.addAll(VBox_ingame_building);
                }
            ScrollPane_ingame_playerBuildingBar.setContent(VBox_ingame_playerBuildingBar);
            return ScrollPane_ingame_playerBuildingBar;
        }

        /*private static Capturer_TextArea createCapturerTextAreaMessageLog(MessageLog log) {
            Capturer_TextArea cta = new Capturer_TextArea();
            log.getEventBroadcaster().addEventCapturer(cta);
            cta.setEditable(false);
            cta.setWrapText(true);
            return cta;
        }*/

        private static Capturer_TextFlow createCapturerTextFlowMessageLog(MessageLog log) {
            Capturer_TextFlow ctf = new Capturer_TextFlow();
            log.getEventBroadcaster().addEventCapturer(ctf);
            return ctf;
        }

    }

    public static class GameBuilder {
        private final Node extractionUI;

        public GameBuilder(Node extractionUI)
        {
            this.extractionUI=extractionUI;
        }

        public Game createGameFromUI()
        {
            VBox choices = (VBox) extractionUI.lookup("#VBox-intro-config1Choices");    //Looking for the container where the intro choices for the game settings are, in order to get the needed values;
            Accordion playersAccordion = (Accordion) extractionUI.lookup("#Accordion-intro-playersSettings");   //Looking for the accordion where the player settings are, in order to get the needed values;
            int playersNr = (int) ((ChoiceBox) choices.lookup("#ChoiceBox-intro-playersNr")).getValue();     //Getting the playersNr from its choicebox
            List<Player> playersList = new LinkedList<>();
            for (int i = 1; i <= playersNr ; i++) {
                String playerName = ((TextField) playersAccordion.lookup("#TextField-intro-player" + i + "Name")).getText();
                String playerColor = (String) ((ChoiceBox) playersAccordion.lookup("#ChoiceBox-intro-player" + i + "Color")).getValue();
                playersList.add(new Player(playerName,playerColor));
            }
            String mapSize = (String) ((ChoiceBox) choices.lookup("#ChoiceBox-intro-mapSize")).getValue();
            String resDensity = (String) ((ChoiceBox) choices.lookup("#ChoiceBox-intro-resDensity")).getValue();

            return new Game(playersList,mapSize,resDensity);
        }
    }
}