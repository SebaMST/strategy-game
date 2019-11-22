package PixelWars;

import PixelWars.GUI.GameUI;
import PixelWars.GameLogic.Game;
import PixelWars.GameLogic.Messaging.MessagingSystem;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class GameEngine extends Application {
    private Stage mainStage;
    private Scene mainScene;

    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        //Initializing the stage
        mainStage.setTitle("PIXEL WARS");
        mainStage.setFullScreen(true);
        mainStage.setFullScreenExitHint("");
        mainStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        //Initializing the scene
        mainScene = new Scene(new Region());//dummy scene
        mainScene.getStylesheets().add("res/css/game.css");//Setting scene's css reference
        //Setting the stage's scene
        mainStage.setScene(mainScene);
        //Initializing GameUI Components
        GameUI.init();
        game();
    }

    private void game() {
        Parent intro = GameUI.IntroUI.createIntroUI();      //Creating the Parent object for the introUI
        HBox introButtons = (HBox) intro.lookup("#HBox-intro-buttons");     //Looking for the container where the intro buttons are, in order to set event handlers for what could come next

        Button playButton = (Button) introButtons.lookup("#Button-intro-play");     //Setting event handler for clicking the play button
        playButton.setOnMouseClicked(playEvent -> {
                    Game g = new GameUI.GameBuilder(intro).createGameFromUI();

                    //Setting the scene's root to inGameUI

                    Parent ingame = GameUI.InGameUI.createInGameUI(g);

                    HBox ingameButtons = (HBox) ingame.lookup("#HBox-ingame-buttons");

                    Button beginButton = (Button) ingameButtons.lookup("#Button-ingame-begin");
                    beginButton.setOnMouseClicked(beginEvent -> {
                        beginButton.setDisable(true);
                        g.begin();
                    });
                    Button resetButton = (Button) ingameButtons.lookup("#Button-ingame-reset");
                    resetButton.setOnMouseClicked(resetEvent -> {
                        MessagingSystem.reset();
                        g.stop();
                        game();
                    });

                    //Setting the main scene's root as the ingameUI
                    mainScene.setRoot(ingame);
        });
        Button quitButton = (Button) introButtons.lookup("#Button-intro-quit");
        quitButton.setOnMouseClicked(quitEvent -> mainStage.close());

        //Setting main scene's root as the introUI
        mainScene.setRoot(intro);
        //Showing the main stage
        if (!mainStage.isShowing()) {
            mainStage.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
