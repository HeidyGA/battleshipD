package org.example.battleshipd.view;

import org.example.battleshipd.controller.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStage extends Stage {

    private GameController gameController;

    public GameStage () throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/battleshipd/game-view.fxml"));
        Parent root;
        root = loader.load();
        gameController = loader.getController();
        setTitle("BattleShip");
        Scene scene = new Scene(root);
        getIcons().add(
                new Image(
                        String.valueOf(getClass().getResource("/org/example/battleshipd/images/fire.png"))));
        setScene(scene);
        setResizable(false);
        show();
    }

    public GameController getGameController() {
        return gameController;
    }

    public static GameStage getInstance() throws IOException {
        return GameStageHolder.INSTANCE = new GameStage();
    }

    private static class GameStageHolder {
        private static GameStage INSTANCE;
    }
    public static void deleteInstance() {
        GameStageHolder.INSTANCE.close();
        GameStageHolder.INSTANCE = null;
    }
}
