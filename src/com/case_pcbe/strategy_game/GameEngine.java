package com.case_pcbe.strategy_game;

import com.case_pcbe.strategy_game.GUI.GameUI;
import com.case_pcbe.strategy_game.GameLogic.Game;
import com.case_pcbe.strategy_game.GameLogic.Player;
import com.case_pcbe.strategy_game.GameLogic.MapLogic.Map;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

public class GameEngine extends Application {
    private Stage mainStage;
    private Scene mainScene;

    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        mainStage.setTitle("PIXEL WARS");
        mainStage.setFullScreen(true);
        mainStage.setFullScreenExitHint("");
        mainStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        //Creating empty scene
        mainScene = new Scene(new Region());
        //Setting scene's css reference
        mainScene.getStylesheets().add("res/css/game.css");
        //Setting stage's scene
        mainStage.setScene(mainScene);
        game();
    }

    public void game() {
        //Creating the Parent object for the introUI
        Parent intro = GameUI.IntroUI.createIntroUI();
        //Setting the scene object from the root intro
        mainScene.setRoot(intro);
        //Showing the main stage
        if (!mainStage.isShowing()) {
            mainStage.show();
        }
        //Adding event handler for the play button to transit to the next scene (after creating the map/game object from the form data)
        Button playButton = (Button) mainScene.lookup("#Button-intro-play");
        playButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            long l = System.currentTimeMillis();
            VBox choices = (VBox) mainScene.lookup("#VBox-intro-config1Choices");
            ChoiceBox playersNrCB = (ChoiceBox) choices.lookup("#ChoiceBox-intro-playersNr");
            Accordion playersAccordion = (Accordion) mainScene.lookup("#Accordion-intro-playersSettings");
            //Creating ArrayList<Player> object
            ArrayList<Player> players = new ArrayList<>();
            for (int i = 1; i <= (int) playersNrCB.getValue(); i++) {
                TextField playerNameTF = (TextField) playersAccordion.lookup("#TextField-intro-player" + i + "Name");
                ChoiceBox playerColorCB = (ChoiceBox) playersAccordion.lookup("#ChoiceBox-intro-player" + i + "Color");
                players.add(new Player(playerNameTF.getText(), (String) playerColorCB.getValue()));
            }
            //Creating the Map object
            ChoiceBox mapSizeCB = (ChoiceBox) choices.lookup("#ChoiceBox-intro-mapSize");
            Map map = new Map((String) mapSizeCB.getValue());//7-tiny(0)=7 biggest tile or smallest map...7-small(1)=6 second biggest tile or second smallest map...7-giant(4)=3 smallest tile or biggest map
            //Creating the Game object
            Game g = new Game(players, map);
            //Setting the scene's root to inGameUI
            Parent ingame = GameUI.InGameUI.createInGameUI(g);
            mainScene.setRoot(ingame);
            System.out.println(System.currentTimeMillis() - l);
        });

        Button quitButton = (Button) mainScene.lookup("#Button-intro-quit");
        quitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> mainStage.close());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
