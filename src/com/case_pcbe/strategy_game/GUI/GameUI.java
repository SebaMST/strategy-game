package com.case_pcbe.strategy_game.GUI;

import com.case_pcbe.strategy_game.GameLogic.Messaging.MessageLog;
import com.case_pcbe.strategy_game.GameLogic.Messaging.MessagingSystem;
import com.case_pcbe.strategy_game.GameLogic.Game;
import com.case_pcbe.strategy_game.GameLogic.MapLogic.Map;
import com.case_pcbe.strategy_game.GameLogic.Player;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.awt.*;
import java.util.*;

public class GameUI {
    private static final double SCREEN_WIDTH, SCREEN_HEIGHT;

    static {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        SCREEN_WIDTH = screenSize.getWidth();
        SCREEN_HEIGHT = screenSize.getHeight();
        System.out.println(SCREEN_WIDTH + " " + SCREEN_HEIGHT);
    }

    private static <T extends Node> void setupElement(T r, String id, Double layoutX, Double layoutY) {
        if (id != null) {
            r.setId(id);
        }
        if (layoutX != null) {
            r.setLayoutX(layoutX);
        }
        if (layoutY != null) {
            r.setLayoutY(layoutY);
        }
    }

    private static <T extends Region> void setupElement(T r, String id, Double layoutX, Double layoutY, Double width, Double height) {
        setupElement(r, id, layoutX, layoutY);
        if (width != null) {
            r.setPrefWidth(width);
        }
        if (height != null) {
            r.setPrefHeight(height);
        }
    }

    private static void updatePlayersAccordion(int playersNr, Accordion playersAccordion) {
        ObservableList<TitledPane> playersTitledPanes = playersAccordion.getPanes();
        playersTitledPanes.clear();

        for (int i = 1; i <= playersNr; ++i) {
            HBox HBox_playerConfig = new HBox();
            ObservableList<Node> HBox_playerConfig_Children = HBox_playerConfig.getChildren();
            VBox VBox_playerLabels = new VBox();
            VBox_playerLabels.getStyleClass().add("VBox-playerLabels");
            ObservableList<Node> VBox_playerLabels_Children = VBox_playerLabels.getChildren();
            Label Label_playerName = new Label("Name:");
            Label_playerName.getStyleClass().addAll("Text-sizeS", "Text-shadowEffect");

            Label Label_playerColor = new Label("Color:");
            Label_playerColor.getStyleClass().addAll("Text-sizeS", "Text-shadowEffect");

            VBox_playerLabels_Children.addAll(Label_playerName, Label_playerColor);
            VBox VBox_playerInputs = new VBox();
            VBox_playerInputs.getStyleClass().add("VBox-playerInputs");
            ObservableList<Node> VBox_playerInputs_Children = VBox_playerInputs.getChildren();
            TextField TextField_playerName = new TextField("Player #" + i);
            TextField_playerName.setId("TextField-player" + i + "Name");

            ColorPicker ColorPicker_playerColor = new ColorPicker();
            ColorPicker_playerColor.setId("ColorPicker-player" + i + "Color");

            VBox_playerInputs_Children.addAll(TextField_playerName, ColorPicker_playerColor);
            HBox_playerConfig_Children.addAll(VBox_playerLabels, VBox_playerInputs);

            playersTitledPanes.add(new TitledPane("Player #" + i, HBox_playerConfig));
        }
    }

    public static Scene createIntroUI() {
        HBox HBox_root = new HBox();
        Scene introUI = new Scene(HBox_root);
        introUI.getStylesheets().add("res/css/intro.css");
        HBox_root.setId("HBox-root");
        ObservableList<Node> HBox_root_Children = HBox_root.getChildren();
        VBox VBox_mainPanel = new VBox();
        VBox_mainPanel.setId("VBox-mainPanel");
        ObservableList<Node> VBox_mainPanel_Children = VBox_mainPanel.getChildren();
        VBox VBox_welcome = new VBox();
        VBox_welcome.setId("VBox-welcome");
        ObservableList<Node> VBox_welcome_Children = VBox_welcome.getChildren();
        Text Text_title = new Text("PIXEL Wars");
        Text_title.setId("Text-title");
        Text_title.getStyleClass().addAll("Text-size3XL", "Text-shadowEffect");

        Text Text_motto = new Text("The PIXEL world awaits you: Grind, Build, Conquer!\n\n");
        Text_motto.getStyleClass().addAll("Text-sizeM", "Text-shadowEffect");

        Text Text_adv = new Text("Firstly, let us configure the game for you:");
        Text_adv.setId("Text-adv");
        Text_adv.getStyleClass().addAll("Text-sizeS", "Text-shadowEffect");

        VBox_welcome_Children.addAll(Text_title, Text_motto, Text_adv);
        HBox HBox_config1 = new HBox();
        HBox_config1.setId("HBox-config1");
        ObservableList<Node> HBox_config1_Children = HBox_config1.getChildren();
        VBox VBox_labels = new VBox();
        VBox_labels.setId("VBox-labels");
        ObservableList<Node> VBox_labels_Children = VBox_labels.getChildren();
        Label Label_playersNr = new Label("Nr. of players:");
        Label_playersNr.getStyleClass().addAll("Text-sizeS", "Text-shadowEffect");

        Label Label_mapSize = new Label("Map size:");
        Label_mapSize.getStyleClass().addAll("Text-sizeS", "Text-shadowEffect");

        Label Label_resDensity = new Label("Resources density:");
        Label_resDensity.getStyleClass().addAll("Text-sizeS", "Text-shadowEffect");

        VBox_labels_Children.addAll(Label_playersNr, Label_mapSize, Label_resDensity);
        VBox VBox_choices = new VBox();
        VBox_choices.setId("VBox-choices");
        ObservableList<Node> VBox_choices_Children = VBox_choices.getChildren();
        ChoiceBox ChoiceBox_playersNr = new ChoiceBox(FXCollections.observableArrayList(2, 3, 4, 5, 6, 7, 8));
        ChoiceBox_playersNr.setId("ChoiceBox-playersNr");
        ChoiceBox_playersNr.setValue(2);

        ChoiceBox ChoiceBox_mapSize = new ChoiceBox(FXCollections.observableArrayList("Tiny", "Small", "Medium", "Large", "Giant"));
        ChoiceBox_mapSize.setId("ChoiceBox-mapSize");
        ChoiceBox_mapSize.setValue("Tiny");

        ChoiceBox ChoiceBox_resDensity = new ChoiceBox(FXCollections.observableArrayList("Starvation", "Moderate", "Richness"));
        ChoiceBox_resDensity.setId("ChoiceBox-resDensity");
        ChoiceBox_resDensity.setValue("Moderate");

        VBox_choices_Children.addAll(ChoiceBox_playersNr, ChoiceBox_mapSize, ChoiceBox_resDensity);
        HBox_config1_Children.addAll(VBox_labels, VBox_choices);
        VBox VBox_config2 = new VBox();
        VBox_config2.setId("HBox-config2");
        ObservableList<Node> VBox_config2_Children = VBox_config2.getChildren();
        Text Text_playerSettings = new Text("Players Settings");
        Text_playerSettings.setId("Text-playersSettings");
        Text_playerSettings.getStyleClass().addAll("Text-sizeS", "Text-shadowEffect");

        Accordion Accordion_playersSettings = new Accordion();
        Accordion_playersSettings.setId("Accordion-playersSettings");
        updatePlayersAccordion((int) ChoiceBox_playersNr.getValue(), Accordion_playersSettings);
        ChoiceBox_playersNr.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                updatePlayersAccordion((int) ChoiceBox_playersNr.getValue(), Accordion_playersSettings);
            }
        });
        VBox_config2_Children.addAll(Text_playerSettings, Accordion_playersSettings);
        HBox VBox_buttons = new HBox();
        VBox_buttons.setId("VBox-buttons");
        ObservableList<Node> VBox_buttons_Children = VBox_buttons.getChildren();
        Button BTN_play = new Button("PLAY");
        BTN_play.setId("Button-play");
        BTN_play.getStyleClass().addAll("Button-mainPanel");

        Button BTN_quit = new Button("QUIT");
        BTN_quit.setId("Button-quit");
        BTN_quit.getStyleClass().add("Button-mainPanel");

        VBox_buttons_Children.addAll(BTN_play, BTN_quit);
        VBox_mainPanel_Children.addAll(VBox_welcome, HBox_config1, VBox_config2, VBox_buttons);
        HBox_root_Children.add(VBox_mainPanel);

        return introUI;
    }

    public static Scene createInGameUI(Game g) {
        AnchorPane root = new AnchorPane();
        setupElement(root, "inGameUIroot", null, null, 1920.0D, 1080.0D);
        ObservableList<Node> rootChildren = root.getChildren();
        root.setStyle("-fx-background-color: BLACK");

        AnchorPane leftSide = new AnchorPane();
        setupElement(leftSide, "leftSide", null, null, 520.0D, 1080.0D);
        leftSide.setStyle("-fx-background-color: GREY");
        rootChildren.add(leftSide);
        ObservableList<Node> leftSideChildren = leftSide.getChildren();

        Accordion playersPanelsAccordion = playersAsAccordion(g.getPlayers());
        setupElement(playersPanelsAccordion, "playersPanelsAccordion", null, null, 320.0D, 700.0D);
        leftSideChildren.add(playersPanelsAccordion);

        TextArea globalLogTA = messageLogAsTextArea(MessagingSystem.MESSAGE_LOG);
        setupElement(globalLogTA, "globalLogTA", null, 700.0D, 520.0D, 380.0D);
        leftSideChildren.add(globalLogTA);

        Pane mapPane = mapAsPane(g.getMap());
        setupElement(mapPane, "mapPane", 530.0D, 30.0D, 1260.0D, 840.0D);
        rootChildren.add(mapPane);
        return new Scene(root);
    }

    private static Pane mapAsPane(Map map) {
        Pane mapPane = new Pane();
        ObservableList<Node> mapTilesList = mapPane.getChildren();
        double[][] noiseMatrix = map.getNoiseMatrix();
        int tileSize = map.getTileSize();

        for (int i = 0; i < map.getHeight(); ++i) {
            for (int j = 0; j < map.getWidth(); ++j) {
                for (int h = 0; h < Map.terrainHeights.length; ++h) {
                    if (noiseMatrix[i][j] <= Map.terrainHeights[h]) {
                        Rectangle r = new Rectangle();
                        r.setWidth(tileSize);
                        r.setHeight(tileSize);
                        r.setY(i * tileSize);
                        r.setX(j * tileSize);
                        r.setFill(Map.terrainColors[h]);
                        mapTilesList.add(r);
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
        TextArea gameLog = new TextArea();
        gameLog.setText(log.toString());
        return gameLog;
    }
}