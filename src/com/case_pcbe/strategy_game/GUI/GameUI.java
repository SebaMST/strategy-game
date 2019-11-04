package com.case_pcbe.strategy_game.GUI;

import com.case_pcbe.strategy_game.Console.MessageLog;
import com.case_pcbe.strategy_game.Console.MessagingSystem;
import com.case_pcbe.strategy_game.GameLogic.Game;
import com.case_pcbe.strategy_game.GameLogic.MapLogic.Map;
import com.case_pcbe.strategy_game.GameLogic.Player;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class GameUI {
    /*Not useful here. Just send a new empty Node Object instead of the Class object.
    public static <T extends Node> T setupElement(Class<T> nodeType,String id,Double layoutX,Double layoutY,Boolean visible) throws Exception {
        return setupElement(nodeType.newInstance(),id,layoutX,layoutY,visible);
    }*/
        /*Not useful here. Just send a new empty Region Object instead of the Class object.
    public static <T extends Region> T setupElement(Class<T> nodeType, String id, Double layoutX, Double layoutY, Boolean visible, Double width, Double height) throws Exception {
        return setupElement(nodeType.newInstance(), id, layoutX, layoutY, visible,width, height);
    }*/

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
            Label playerNameLabel = new Label("Name:");
            setupElement(playerNameLabel, null, 35.0D, 50.0D);
            playerNameLabel.setFont(Font.font("System", 16.0D));
            Label playerColorLabel = new Label("Color:");
            setupElement(playerColorLabel, null, 35.0D, 110.0D);
            playerColorLabel.setFont(Font.font("System", 16.0D));

            TextField playerNameInput = new TextField("Player #" + i);
            setupElement(playerNameInput, "player" + i + "NameTF", 100.0D, 45.0D, 180.0D, null);
            playerNameInput.setFont(Font.font("System", 16.0D));
            ColorPicker playerColorPicker = new ColorPicker();
            setupElement(playerColorPicker, "player" + i + "ColorCP", 100.0D, 110.0);

            AnchorPane playerAP = new AnchorPane(playerNameLabel, playerColorLabel, playerNameInput, playerColorPicker);
            playersTitledPanes.add(new TitledPane("Player #" + i, playerAP));
        }

    }

    public static Scene createIntroUI() {
        AnchorPane root = new AnchorPane();
        setupElement(root, "introUIroot", null, null, 400.0D, 700.0D);
        ObservableList<Node> rootChildren = root.getChildren();

        Text introDialog = new Text("Welcome to PIXEL Wars! First, we need to configure the game for you:");
        setupElement(introDialog, "introDialog", 14.0D, 27.0D);
        rootChildren.add(introDialog);

        Label l1 = new Label("Nr. of players:");
        setupElement(l1, null, 40.0D, 50.0D);
        l1.setFont(Font.font("System", 16.0D));
        Label l2 = new Label("Map size:");
        setupElement(l2, null, 40.0D, 90.0D);
        l2.setFont(Font.font("System", 16.0D));
        Label l3 = new Label("Resources density:");
        setupElement(l3, null, 40.0D, 130.0D);
        l3.setFont(Font.font("System", 16.0D));

        rootChildren.add(l1);
        rootChildren.add(l2);
        rootChildren.add(l3);

        ChoiceBox playersNrCB = new ChoiceBox(FXCollections.observableArrayList(2, 3, 4, 5, 6, 7, 8));
        setupElement(playersNrCB, "playersNrCB", 210D, 50.0D, 150.0D, null);
        playersNrCB.setValue(2);
        ChoiceBox mapSizeCB = new ChoiceBox(FXCollections.observableArrayList("Tiny", "Small", "Medium", "Large", "Giant"));
        setupElement(mapSizeCB, "mapSizeCB", 210.0D, 90.0D, 150.0D, null);
        mapSizeCB.setValue("Tiny");
        ChoiceBox resDensityCB = new ChoiceBox(FXCollections.observableArrayList("Starvation", "Moderate", "Richness"));
        setupElement(resDensityCB, "resDensityCB", 210.0D, 130.0D, 150.0D, null);
        resDensityCB.setValue("Starvation");
        rootChildren.add(playersNrCB);
        rootChildren.add(mapSizeCB);
        rootChildren.add(resDensityCB);

        AnchorPane accordionAnchor = new AnchorPane();
        setupElement(accordionAnchor, null, null, 180.0D, 400.D, 440.D);
        rootChildren.add(accordionAnchor);
        ObservableList<Node> accordionAnchorChildren = accordionAnchor.getChildren();

        Label l4 = new Label("Player Settings");
        setupElement(l4, null, 145.0D, 8.0D);
        l4.setFont(Font.font("System", 16.0D));
        accordionAnchorChildren.add(l4);

        Accordion playersAccordion = new Accordion();
        setupElement(playersAccordion, "playersAccordion", 40.0D, 40.0D, 320.0D, 400.0D);
        updatePlayersAccordion((int) playersNrCB.getValue(), playersAccordion);

        playersNrCB.valueProperty().addListener((ChangeListener<Object>) (observable, oldValue, newValue) -> {
            updatePlayersAccordion((int) playersNrCB.getValue(), playersAccordion);
        });
        accordionAnchorChildren.add(playersAccordion);

        Button playButton = new Button("PLAY");
        setupElement(playButton, "playButton", 150.0D, 640.0D, 100.0D, 20.0D);
        rootChildren.add(playButton);
        return new Scene(root);
    }

    public static Scene createInGameUI(Game g) {

        AnchorPane root = new AnchorPane();
        setupElement(root, "inGameUIroot", null, null, 1600.0D, 900.0D);
        ObservableList<Node> rootChildren = root.getChildren();

        Accordion playersPanelsAccordion = playersAsAccordion(g.getPlayers());
        setupElement(playersPanelsAccordion, "playersPanelsAccordion", null, null, 320.0D, 600.0D);
        rootChildren.add(playersPanelsAccordion);

        TextArea globalLogTA = messageLogAsTextArea(MessagingSystem.MESSAGE_LOG);
        setupElement(globalLogTA, "globalLogTA", null, 600.0D, 320.0D, 300.0D);
        rootChildren.add(globalLogTA);

        Pane mapPane = mapAsPane(g.getMap());
        setupElement(mapPane, "mapPane", 320.0D, null, 1280.0D, 900.0D);
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