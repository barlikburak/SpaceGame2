/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacegame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.FXMLLoginController;

/**
 *
 * @author burak
 */
public class SpaceGame extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXMLLogin.fxml"));
        Parent root = loader.load();

        FXMLLoginController fXMLLoginController = loader.getController();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        fXMLLoginController.setLoginStage(stage);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
