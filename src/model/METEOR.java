/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author burak
 */
public enum METEOR {//Meteor resimleri ve boyutları vardır.
    MeteorBrown_big1("/view/resources/meteor/meteorBrown_big1.png", 80),
    MeteorBrown_big2("/view/resources/meteor/meteorBrown_big2.png", 70),
    MeteorBrown_big3("/view/resources/meteor/meteorBrown_big3.png", 60),
    MeteorBrown_big4("/view/resources/meteor/meteorBrown_big4.png", 51),
    MeteorBrown_med1("/view/resources/meteor/meteorBrown_med1.png", 43),
    MeteorGrey_big1("/view/resources/meteor/meteorGrey_big1.png", 80),
    MeteorGrey_big2("/view/resources/meteor/meteorGrey_big2.png", 70),
    MeteorGrey_big3("/view/resources/meteor/meteorGrey_big3.png", 60),
    MeteorGrey_big4("/view/resources/meteor/meteorGrey_big4.png", 51),
    MeteorGrey_med1("/view/resources/meteor/meteorGrey_med1.png", 43);

    private final String URL;
    private final int SIZE;

    private METEOR(String URL, int SIZE) {
        this.URL = URL;
        this.SIZE = SIZE;
    }

    public String getURL() {
        return URL;
    }

    public int getSIZE() {
        return SIZE;
    }

}