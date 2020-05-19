/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entity.Oyuncu;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.SHIPPickerInterface;
import movements.Game;
import movements.GameFacade;
import movements.SHIPToPickContext;

/**
 * FXML Controller class
 *
 * @author burak
 */
public class FXMLGameViewManagerController implements Initializable {

    @FXML
    private AnchorPane gamePane;
    @FXML
    private GridPane gridPane1;
    @FXML
    private GridPane gridPane2;
    @FXML
    private Button play;

    private Stage menuStage;
    private Stage gameStage;
    private SHIPToPickContext shipToPick;
    private GameFacade gameFacade;
    private Oyuncu oyuncu;

    @FXML
    private void initializeGame() {
        this.play.setVisible(false);
        Game game = new Game(shipToPick, gamePane, gridPane1, gridPane2, menuStage, gameStage, oyuncu);
        gameFacade = new GameFacade(game);
        gameFacade.start();
    }

    @FXML
    private void playButtonEntered() {
    }

    @FXML
    private void playButtonExited() {
    }

    @FXML
    private void onKeyPressed(KeyEvent event) {
        gameFacade.keyPressed(event);
    }

    @FXML
    private void onKeyReleased(KeyEvent event) {
        gameFacade.keyRelasedKey(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setMenuStage(Stage menuStage) {
        this.menuStage = menuStage;
    }

    public void setGameStage(Stage gameStage) {
        this.gameStage = gameStage;
    }

    public void setShipToPick(SHIPToPickContext shipToPick) {
        this.shipToPick = shipToPick;
    }

    public void setOyuncu(Oyuncu oyuncu) {
        this.oyuncu = oyuncu;
    }

}
