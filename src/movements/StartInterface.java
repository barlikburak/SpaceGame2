/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movements;

import javafx.scene.layout.AnchorPane;

/**
 *
 * @author burak
 */
public interface StartInterface {

    public void startGame(Object[] object);//template method

    public void addObjects(AnchorPane gamePane);//nesnelerimizi ekrana ekledik.

    public StartInterface getStartInterface();//bulunduğu class'ı döndürüyor.
}
