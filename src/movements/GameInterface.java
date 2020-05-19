/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movements;

import javafx.scene.layout.GridPane;
import model.GameObject;

/**
 *
 * @author burak
 */
public interface GameInterface {

    public void setNewElementRotate(GameObject object);

    public void setNewElementPosition(GameObject object);

    public boolean checkIfElementsCollide(GameObject element, GameObject ship);

    public void moveBackground(GridPane gridPane1, GridPane gridPane2);

    public void createBackground(GridPane gridPane1, GridPane gridPane2);

}
