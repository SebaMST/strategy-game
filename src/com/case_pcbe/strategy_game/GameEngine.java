package com.case_pcbe.strategy_game;

import com.case_pcbe.strategy_game.GUI.GameUI;
import com.case_pcbe.strategy_game.GameLogic.Game;
import com.case_pcbe.strategy_game.GameLogic.Player;
import com.case_pcbe.strategy_game.GameLogic.MapLogic.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameEngine extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("PIXEL WARS");
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        //primaryStage.setFullScreen(true);
        //Creating the Scene object for the introUI
        Scene introUI = GameUI.createIntroUI();
        //Setting the stage's scene to introUI
        primaryStage.setScene(introUI);
        primaryStage.show();
        //Adding event handler for the play button to transit to the next scene (after creating the map/game object from the form data)
        Button playButton = (Button) introUI.lookup("#Button-play");
        playButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            //Creating ArrayList<Player> object
            ArrayList<Player> players = new ArrayList<>();
            ChoiceBox playersNrCB = (ChoiceBox) introUI.lookup("#ChoiceBox-playersNr");
            Accordion playersAccordion = (Accordion) introUI.lookup("#Accordion-playersSettings");
            for (int i = 1; i <= (int) playersNrCB.getValue(); i++) {
                TextField playerNameTF = (TextField) playersAccordion.lookup("#TextField-player" + i + "Name");
                ColorPicker playerColorCP = (ColorPicker) playersAccordion.lookup("#ColorPicker-player" + i + "Color");
                players.add(new Player(playerNameTF.getText(), playerColorCP.getValue()));
            }
            //Creating the Map object
            ChoiceBox mapSizeCB = (ChoiceBox) introUI.lookup("#ChoiceBox-mapSize");
            Map map = new Map((String) mapSizeCB.getValue());//7-tiny(0)=7 biggest tile or smallest map...7-small(1)=6 second biggest tile or second smallest map...7-giant(4)=3 smallest tile or biggest map
            //Creating the Game object
            Game g = new Game(players, map);
            //Creating the Scene object for the inGameUI
            Scene inGameUI = GameUI.createInGameUI(g);
            //Setting the stage's scene to inGameUI
            primaryStage.setScene(inGameUI);
            primaryStage.setFullScreen(true);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
