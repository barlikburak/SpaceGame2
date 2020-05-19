/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movements;

import java.util.Random;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.GameObject;

/**
 *
 * @author burak
 */
public class SpaceGame implements GameInterface {

    private final String BACKGROUND_IMAGE;
    private final Random random;

    public SpaceGame() {
        this.BACKGROUND_IMAGE = "/view/resources/background.png";//Oyun arkaplan resmi
        this.random = new Random();
    }

    /**
     * Gridpane içine arkaplan resimlerini yerleştirdik.
     *
     * @param gridPane1
     * @param gridPane2
     */
    @Override
    public void createBackground(GridPane gridPane1, GridPane gridPane2) {
        for (int i = 0; i < 12; i++) {
            ImageView backgroundImage1 = new ImageView(BACKGROUND_IMAGE);
            ImageView backgroundImage2 = new ImageView(BACKGROUND_IMAGE);
            GridPane.setConstraints(backgroundImage1, i % 3, i / 3);
            GridPane.setConstraints(backgroundImage2, i % 3, i / 3);
            gridPane1.getChildren().add(backgroundImage1);
            gridPane2.getChildren().add(backgroundImage2);
        }
    }

    /**
     * Oyunun arka planını hareket ettirdik.
     *
     * @param gridPane1
     * @param gridPane2
     */
    @Override
    public void moveBackground(GridPane gridPane1, GridPane gridPane2) {
        gridPane1.setLayoutY(gridPane1.getLayoutY() + 1);
        gridPane2.setLayoutY(gridPane2.getLayoutY() + 1);

        if (gridPane1.getLayoutY() >= 800) {
            gridPane1.setLayoutY(-800);
        }
        if (gridPane2.getLayoutY() >= 800) {
            gridPane2.setLayoutY(-800);
        }
    }

    /**
     *
     * @param element Uzay gemisine doğru gelen nesneler
     * @param ship Uzay Gemisi
     * @return çarpışma olup olmadığı sonucunu döndürür.
     */
    @Override
    public boolean checkIfElementsCollide(GameObject element, GameObject ship) {
        return element.getLayoutY() + element.getFitHeight() > ship.getLayoutY()
                && ship.getLayoutX() + ship.getFitWidth() >= element.getLayoutX()
                && ship.getLayoutX() <= element.getLayoutX() + element.getFitWidth();
    }

    /**
     * Gelen nesneye yeni konum bilgisi ataması gerçekleşir.
     *
     * @param object aşağıya doğru akan nesneler
     */
    @Override
    public void setNewElementPosition(GameObject object) {
        object.setLayoutX(random.nextInt(400));
        object.setLayoutY(-(random.nextInt(1000) + 200));

    }

    /**
     * Gelen nesneye yeni rotasyon(nesnenin açısı) ataması gerçekleşir.
     *
     * @param object aşağıya doğru akan nesneler
     */
    @Override
    public void setNewElementRotate(GameObject object) {
        object.setRotate(random.nextInt(360));
    }

}
