package com.case_pcbe.strategy_game.GUI;

import com.case_pcbe.strategy_game.Console.MessageLog;
import com.case_pcbe.strategy_game.Console.MessagingSystem;
import com.case_pcbe.strategy_game.GameLogic.Game;
import com.case_pcbe.strategy_game.GameLogic.MapLogic.Map;
import com.case_pcbe.strategy_game.GameLogic.Player;
import javafx.application.Application;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameUI extends Application {
    private static Stage gameStage = new Stage();

    public GameUI() {
    }

    public void start(Stage primaryStage) throws Exception {
        gameStage.setTitle("PIXEL WARS");
        gameStage.setResizable(false);
        Scene introScene = this.createIntroScene();
        gameStage.setScene(introScene);
        gameStage.show();
    }

    public Scene createIntroScene() {
        AnchorPane root = new AnchorPane();
        root.setPrefSize(400.0D, 700.0D);
        ObservableList<Node> rootChildren = root.getChildren();
        Text introDialog = new Text();
        introDialog.setText("Welcome to PIXEL Wars! First, we need to configure the game for you:");
        introDialog.setLayoutX(14.0D);
        introDialog.setLayoutY(27.0D);
        rootChildren.add(introDialog);
        Label l1 = new Label("Nr. of players:");
        l1.setFont(Font.font("System", 16.0D));
        l1.setLayoutX(40.0D);
        l1.setLayoutY(50.0D);
        rootChildren.add(l1);
        ChoiceBox playersNr = new ChoiceBox();
        playersNr.setId("playersNr");
        playersNr.setItems(FXCollections.observableArrayList(new Integer[]{2, 3, 4, 5, 6, 7, 8}));
        playersNr.setPrefWidth(150.0D);
        playersNr.setLayoutX(210.0D);
        playersNr.setLayoutY(50.0D);
        AnchorPane accordionAnchor = new AnchorPane();
        accordionAnchor.setPrefSize(400.0D, 440.0D);
        accordionAnchor.setLayoutY(180.0D);
        accordionAnchor.setVisible(false);
        rootChildren.add(accordionAnchor);
        ObservableList<Node> accordionAnchorChildren = accordionAnchor.getChildren();
        Label al = new Label("Players Settings");
        al.setFont(Font.font("System", 16.0D));
        al.setLayoutX(145.0D);
        al.setLayoutY(8.0D);
        accordionAnchorChildren.add(al);
        Accordion playersAccordion = new Accordion();
        playersAccordion.setPrefSize(320.0D, 400.0D);
        playersAccordion.setLayoutX(40.0D);
        playersAccordion.setLayoutY(40.0D);
        playersAccordion.setId("playersSettings");
        accordionAnchorChildren.add(playersAccordion);
        ObservableList<TitledPane> playersTitledPanes = playersAccordion.getPanes();
        playersNr.valueProperty().addListener((observable, oldVal, newVal) -> {
            accordionAnchor.setVisible(true);
            playersTitledPanes.clear();
            int nr = (Integer) playersNr.getValue();

            for (int i = 1; i <= nr; ++i) {
                AnchorPane playerAP = new AnchorPane();
                playersTitledPanes.add(new TitledPane("Player #" + i, playerAP));
                ObservableList<Node> playerAPChildren = playerAP.getChildren();
                Label playerNameLabel = new Label("Name:");
                playerNameLabel.setLayoutY(50.0D);
                playerNameLabel.setLayoutX(35.0D);
                playerNameLabel.setFont(Font.font("System", 16.0D));
                playerAPChildren.add(playerNameLabel);
                TextField playerNameInput = new TextField();
                playerNameInput.setLayoutY(45.0D);
                playerNameInput.setLayoutX(100.0D);
                playerNameInput.setPrefWidth(180.0D);
                playerNameInput.setFont(Font.font("System", 16.0D));
                playerNameInput.setId("player" + i + "Name");
                playerAPChildren.add(playerNameInput);
                Label playerColorLabel = new Label("Color:");
                playerColorLabel.setLayoutY(110.0D);
                playerColorLabel.setLayoutX(35.0D);
                playerColorLabel.setFont(Font.font("System", 16.0D));
                playerAPChildren.add(playerColorLabel);
                ColorPicker playerColorPicker = new ColorPicker();
                playerColorPicker.setLayoutY(110.0D);
                playerColorPicker.setLayoutX(100.0D);
                playerAPChildren.add(playerColorPicker);
            }

        });
        rootChildren.add(playersNr);
        Label l2 = new Label("Map size:");
        l2.setFont(Font.font("System", 16.0D));
        l2.setLayoutX(40.0D);
        l2.setLayoutY(90.0D);
        rootChildren.add(l2);
        ChoiceBox mapSize = new ChoiceBox();
        mapSize.setId("mapSize");
        mapSize.setItems(FXCollections.observableArrayList(new String[]{"Tiny", "Small", "Medium", "Large", "Giant"}));
        mapSize.setPrefWidth(150.0D);
        mapSize.setLayoutX(210.0D);
        mapSize.setLayoutY(90.0D);
        rootChildren.add(mapSize);
        Label l3 = new Label("Resources density:");
        l3.setFont(Font.font("System", 16.0D));
        l3.setLayoutX(40.0D);
        l3.setLayoutY(130.0D);
        rootChildren.add(l3);
        ChoiceBox resDensity = new ChoiceBox();
        resDensity.setId("resDensity");
        resDensity.setItems(FXCollections.observableArrayList(new String[]{"Starvation", "Moderate", "Richness"}));
        resDensity.setPrefWidth(150.0D);
        resDensity.setLayoutX(210.0D);
        resDensity.setLayoutY(130.0D);
        rootChildren.add(resDensity);
        Button playButton = new Button("PLAY");
        playButton.setPrefSize(100.0D, 20.0D);
        playButton.setLayoutX(150.0D);
        playButton.setLayoutY(640.0D);
        playButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            Accordion a = (Accordion) playersAccordion.lookup("#playersSettings");
            int nr = (Integer) playersNr.getValue();

            for (int i = 1; i <= nr; ++i) {
                TextField tf = (TextField) (a.lookup("#player" + i + "Name"));
                String s = tf.getText();
                Game.players.add(new Player(s, Color.GOLD));
            }

            Map.createMap((String) (mapSize.getValue()));//7-tiny(0)=7 biggest tile or smallest map...7-small(1)=6 second biggest tile or second smallest map...7-giant(4)=3 smallest tile or biggest map
            gameStage.close();
            gameStage.setScene(this.createInGameScene());
            gameStage.show();
        });
        rootChildren.add(playButton);
        return new Scene(root);
    }

    public Scene createInGameScene() {
        ArrayList<Player> players = Game.players;
        MessageLog globalLog = MessagingSystem.MESSAGE_LOG;
        Map map = Game.MAP;
        AnchorPane root = new AnchorPane();
        root.setPrefSize(1600.0D, 900.0D);
        Accordion playersPanel = playersAsAccordion(players);
        playersPanel.setId("playersPanel");
        playersPanel.setPrefSize(320.0D, 600.0D);
        TextArea gameLog = messageLogAsTextArea(globalLog);
        gameLog.setId("globalLog");
        gameLog.setEditable(false);
        gameLog.setPrefSize(320.0D, 300.0D);
        gameLog.setLayoutY(600.0D);
        long start = System.currentTimeMillis();
        Pane mapPane = mapAsPane();
        mapPane.setId("mapPane");
        mapPane.setPrefSize(1280.0D, 900.0D);
        mapPane.setLayoutX(320.0D);
        long elapsedTime = System.currentTimeMillis() - start;
        System.out.println(elapsedTime);
        root.getChildren().addAll(new Node[]{playersPanel, gameLog, mapPane});
        return new Scene(root, 1600.0D, 900.0D);
    }

    public static Pane mapAsPane() {
        Pane mapPane = new Pane();
        ObservableList<Node> mapTilesList = mapPane.getChildren();
        double[][] noiseMatrix = Map.getNoiseMatrix();
        int tileSize = Map.getTileSize();

        for (int i = 0; i < Map.getHeight(); ++i) {
            for (int j = 0; j < Map.getWidth(); ++j) {
                for (int h = 0; h < Map.terrainHeights.length; ++h) {
                    if (noiseMatrix[i][j] <= Map.terrainHeights[h]) {
                        Rectangle r = new Rectangle();
                        r.setWidth((double) tileSize);
                        r.setHeight((double) tileSize);
                        r.setY((double) (i * tileSize));
                        r.setX((double) (j * tileSize));
                        r.setFill(Map.terrainColors[h]);
                        mapTilesList.add(r);
                        break;
                    }
                }
            }
        }

        return mapPane;
    }

    public static Accordion playersAsAccordion(ArrayList<Player> players) {
        Accordion playersPanel = new Accordion();
        ObservableList<TitledPane> playerPanes = playersPanel.getPanes();

        for (Player p : players) {
            playerPanes.add(new TitledPane(p.getName(), new AnchorPane()));
        }
        return playersPanel;
    }

    public static TextArea messageLogAsTextArea(MessageLog log) {
        TextArea gameLog = new TextArea();
        gameLog.setText(log.toString());
        return gameLog;
    }

    public static void main(String[] args) {
        launch(args);
    }
}