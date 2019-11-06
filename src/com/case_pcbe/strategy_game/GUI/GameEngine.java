package com.case_pcbe.strategy_game.GUI;

import com.case_pcbe.strategy_game.GameLogic.Game;
import com.case_pcbe.strategy_game.GameLogic.Player;
import com.case_pcbe.strategy_game.GameLogic.MapLogic.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameEngine extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("PIXEL WARS");
        //primaryStage.setFullScreen(true);
        //Creating the Scene object for the introUI
        Scene introUI = GameUI.createIntroUI();
        //Setting the stage's scene to introUI
        primaryStage.setScene(introUI);
        primaryStage.show();

        //Adding event handler for the play Button inside introUI
        Button playButton = (Button) introUI.lookup("#playButton");
        playButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            //Creating ArrayList<Player> object
            ArrayList<Player> players = new ArrayList<>();
            ChoiceBox playersNrCB = (ChoiceBox) introUI.lookup("#playersNrCB");
            Accordion playersAccordion = (Accordion) introUI.lookup("#playersAccordion");
            for (int i = 1; i <= (int) playersNrCB.getValue(); i++) {
                TextField playerNameTF = (TextField) playersAccordion.lookup("#player" + i + "NameTF");
                ColorPicker playerColorCP = (ColorPicker) playersAccordion.lookup("#player" + i + "ColorCP");
                players.add(new Player(playerNameTF.getText(), playerColorCP.getValue()));
            }
            //Creating the Map object
            ChoiceBox mapSizeCB = (ChoiceBox) introUI.lookup("#mapSizeCB");
            Map map = new Map((String) mapSizeCB.getValue());//7-tiny(0)=7 biggest tile or smallest map...7-small(1)=6 second biggest tile or second smallest map...7-giant(4)=3 smallest tile or biggest map
            //Creating the Game object
            Game g = new Game(players, map);
            //Creating the Scene object for the inGameUI
            Scene inGameUI = GameUI.createInGameUI(g);
            //Setting the stage's scene to inGameUI
            primaryStage.close();
            primaryStage.setScene(inGameUI);
            primaryStage.show();
            System.out.println(primaryStage.getWidth() + " " + primaryStage.getHeight());
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
