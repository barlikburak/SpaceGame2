/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movements;

import dao.AbstractDAO;
import dao.PuanDAO;
import entity.Oyuncu;
import entity.Puan;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.GameObject;
import model.SHIPImage;
import model.SHIPPickerInterface;

/**
 *
 * @author burak
 */
public class Game {

    private final SHIPToPickContext shipToPick;
    private final AnchorPane gamePane;
    private final GridPane gridPane1;
    private final GridPane gridPane2;
    private final Stage menuStage;
    private final Stage gameStage;
    private final Puan puan;

    private GameObject ship;
    private StartInterface slowingPanelLayout;
    private StartInterface lifeLayout;
    private StartInterface starLayout;
    private SpaceGame spaceGame;

    public Game(SHIPToPickContext shipToPick, AnchorPane gamePane, GridPane gridPane1, GridPane gridPane2, Stage menuStage, Stage gameStage, Oyuncu oyuncu) {
        this.shipToPick = shipToPick;
        this.gamePane = gamePane;
        this.gridPane1 = gridPane1;
        this.gridPane2 = gridPane2;
        this.menuStage = menuStage;
        this.gameStage = gameStage;
        this.puan = new Puan();
        puan.setOyuncu(oyuncu);
    }

    /**
     * nesnelerimizi oluşturduk(hazırladık).
     */
    public void startBooting() {
        ship = new SHIPImage(shipToPick.getSHIP());
        slowingPanelLayout = new SlowingPanelLayout(ship);
        lifeLayout = new LifeLayout(ship, shipToPick.getSHIP().getUrlLife());
        starLayout = new StarLayout(ship);
        spaceGame = new SpaceGame();
    }

    /**
     * oyun arkaplanını oluşturduk.
     */
    public void createScreen() {
        spaceGame.createBackground(gridPane1, gridPane2);
    }

    /**
     * oyun ekranına bütün nesnelerimizi yerleştirdik.
     */
    public void init() {
        slowingPanelLayout.addObjects(gamePane);
        lifeLayout.addObjects(gamePane);
        starLayout.addObjects(gamePane);
    }

    /**
     * Oyunda sürekli çalışacak metod'dur.
     *
     * @return oyun bittiğinde false döndürür.
     */
    public boolean initializeListeners() {
        spaceGame.moveBackground(gridPane1, gridPane2);
        slowingPanelLayout.startGame(null);
        Object[] puans = {puan};
        starLayout.startGame(puans);
        Object[] bool = new Object[1];
        bool[0] = false;
        lifeLayout.startGame(bool);
        return (boolean) bool[0];
    }

    /**
     *
     * @param event klavye dinleyicisi
     */
    public void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT) {
            ((SlowingPanelLayout) slowingPanelLayout.getStartInterface()).setIsLeftKeyPressed(true);
        }
        if (event.getCode() == KeyCode.RIGHT) {
            ((SlowingPanelLayout) slowingPanelLayout.getStartInterface()).setIsRightKeyPressed(true);
        }
        if (event.getCode() == KeyCode.SPACE) {
            ((LifeLayout) lifeLayout).setIsSpaceKeyPressed(true);
        }
    }

    /**
     *
     * @param event klavye dinleyicisi
     */
    public void onKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT) {
            ((SlowingPanelLayout) slowingPanelLayout.getStartInterface()).setIsLeftKeyPressed(false);
        }
        if (event.getCode() == KeyCode.RIGHT) {
            ((SlowingPanelLayout) slowingPanelLayout.getStartInterface()).setIsRightKeyPressed(false);
        }
        if (event.getCode() == KeyCode.SPACE) {
            ((LifeLayout) lifeLayout).setIsSpaceKeyPressed(false);
        }
    }

    /**
     * oyun ekranını kapatıp diğer sayfaya geçer.
     */
    public void stopGame() {
        gameStage.close();
        menuStage.show();
    }

    /**
     * kazanılan puanı veritabanına kayıt eder.
     */
    public void savePuan() {
        AbstractDAO dao = new PuanDAO();
        dao.insert(puan);
    }

}
