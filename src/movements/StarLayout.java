/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movements;

import entity.Puan;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.GameObject;
import model.PointsLabel;
import model.Star;

/**
 *
 * @author burak
 */
public class StarLayout extends SHIPLayout implements StartInterface {

    private GameObject star;
    private Label pointsLabel;
    private int points;
    private String textToSet;

    public StarLayout(GameObject ship) {
        super(ship);
        this.star = new Star();//Yıldız nesnesini oluşturduk.
        this.pointsLabel = new PointsLabel();//puan tablosu nesnesini oluşturduk.
        this.points = 0;
        textToSet = "";
    }

    /**
     *
     * @param object 1 nesne bulunmaktadır o da puan'dır.
     */
    @Override
    public void startGame(Object[] object) {
        move();
        setPoints();
        ((Puan) object[0]).setPuan(points);
    }

    /**
     * puan durumunu yazdırıyoruz.
     */
    private void setPoints() {
        if (checkIfElementsCollide(star, getShip())) {
            setNewElementPosition(star);
            points++;
            textToSet = "POINTS : ";
            if (points < 10) {
                textToSet += "0";
            }
            textToSet += points;
            pointsLabel.setText(textToSet);
        }
    }

    /**
     * Yıldız nesnesinin hareket durumu
     */
    @Override
    protected void move() {
        if (star.getLayoutY() < 1000) {
            star.setLayoutY(star.getLayoutY() + 7.7f);
        } else {
            setNewElementPosition(star);
            setNewElementRotate(star);
        }
    }

    @Override
    public void addObjects(AnchorPane gamePane) {
        gamePane.getChildren().add(this.star);
        gamePane.getChildren().add(this.pointsLabel);
    }

    @Override
    public StartInterface getStartInterface() {
        return this;
    }

    public GameObject getStar() {
        return star;
    }

    public void setStar(GameObject star) {
        this.star = star;
    }

    public Label getPointsLabel() {
        return pointsLabel;
    }

    public void setPointsLabel(Label pointsLabel) {
        this.pointsLabel = pointsLabel;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getTextToSet() {
        return textToSet;
    }

    public void setTextToSet(String textToSet) {
        this.textToSet = textToSet;
    }

}
