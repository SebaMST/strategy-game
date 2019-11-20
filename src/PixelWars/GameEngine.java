package PixelWars;

import PixelWars.GUI.GameUI;
import PixelWars.GameLogic.Game;
import PixelWars.GameLogic.Messaging.MessagingSystem;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

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
                    VBox choices = (VBox) mainScene.lookup("#VBox-intro-config1Choices");    //Looking for the container where the intro choices for the game settings are, in order to get the needed values;
                    Accordion playersAccordion = (Accordion) mainScene.lookup("#Accordion-intro-playersSettings");   //Looking for the accordion where the player settings are, in order to get the needed values;
                    int playersNr = (int) ((ChoiceBox) choices.lookup("#ChoiceBox-intro-playersNr")).getValue();     //Getting the playersNr from its choicebox
                    List<Player> playersList = new LinkedList<>();
                    for (int i = 1; i <= playersNr ; i++) {
                        String playerName = ((TextField) playersAccordion.lookup("#TextField-intro-player" + i + "Name")).getText();
                        String playerColor = (String) ((ChoiceBox) playersAccordion.lookup("#ChoiceBox-intro-player" + i + "Color")).getValue();
                        playersList.add(new Player(playerName,playerColor));
                    }

                    String mapSize = (String) ((ChoiceBox) choices.lookup("#ChoiceBox-intro-mapSize")).getValue();
                    String resDensity = (String) ((ChoiceBox) choices.lookup("#ChoiceBox-intro-resDensity")).getValue();

                    Game g = new Game(playersList,mapSize,resDensity);

                    //Setting the scene's root to inGameUI
                    Parent ingame = GameUI.InGameUI.createInGameUI(g);
                    HBox ingameButtons = (HBox) ingame.lookup("#HBox-ingame-buttons");

                    Button beginButton = (Button) ingameButtons.lookup("#Button-ingame-begin");
                    beginButton.setOnMouseClicked(beginEvent -> g.begin());
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
