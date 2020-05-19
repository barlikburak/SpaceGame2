/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movements;

import dao.AbstractDAO;
import dao.GameDAO;
import dao.KisiDAO;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Help yazısını animasyon şeklinde gösterir ve kontrol işlemleri için gerekli
 * metotlar vardır.
 *
 * @author burak
 */
public class Info extends AnimationTimer {

    private AbstractDAO dao;
    private String strHelp;
    private int index;
    private Label label;

    /**
     *
     * @param e_posta
     * @return girilen değerin e-posta kriterine uyup uymadığını kontrol eder.
     */
    private boolean isEPostaKontrol(String e_posta) {
        int length = e_posta.length();
        for (int i = 0; i < length; i++) {
            if (e_posta.charAt(i) == '@') {
                return e_posta.charAt(length - 1) == 'm'
                        && e_posta.charAt(length - 2) == 'o'
                        && e_posta.charAt(length - 3) == 'c'
                        && e_posta.charAt(length - 4) == '.';
            }
        }
        return false;
    }

    /**
     *
     * @param fieldAd
     * @param fieldSoyad
     * @param fieldEPostaKayit
     * @param fieldSifreKayit
     * @param fieldYas
     * @return bilgilerin doğru olup olmadığı sonucunu döndürür.
     */
    public boolean iskayitOnay(TextField fieldAd, TextField fieldSoyad, TextField fieldEPostaKayit, TextField fieldSifreKayit, TextField fieldYas) {
        try {
            return !fieldAd.getText().equalsIgnoreCase("")
                    && !fieldSoyad.getText().equalsIgnoreCase("")
                    && isEPostaKontrol(fieldEPostaKayit.getText())
                    && !fieldSifreKayit.getText().equalsIgnoreCase("")
                    && Long.valueOf(fieldYas.getText()) > 0
                    && !(boolean) (new KisiDAO()).findByObject(fieldEPostaKayit.getText());
        } catch (NumberFormatException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return false;
    }
    
    /**
     *
     * @param fieldAd
     * @param fieldSoyad
     * @param fieldSifreKayit
     * @param fieldYas
     * @return bilgilerin doğru olup olmadığı sonucunu döndürür.
     */
    public boolean iskayitOnay(TextField fieldAd, TextField fieldSoyad, TextField fieldSifreKayit, TextField fieldYas) {
        try {
            return !fieldAd.getText().equalsIgnoreCase("")
                    && !fieldSoyad.getText().equalsIgnoreCase("")
                    && !fieldSifreKayit.getText().equalsIgnoreCase("")
                    && Long.valueOf(fieldYas.getText()) > 0;
        } catch (NumberFormatException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return false;
    }

    public void help(Label label) {
        this.label = label;
        dao = new GameDAO();
        start();
    }

    private int labelSetTex(Label label) {
        label.setText(label.getText() + strHelp.charAt(index));
        return ++index;
    }

    @Override
    public void handle(long now) {
        if (labelSetTex(label) == strHelp.length()) {
            super.stop();
        }
    }

    @Override
    public void stop() {
        super.stop();
        dao = null;
        strHelp = null;
        System.gc();
    }

    @Override
    public void start() {
        strHelp = (String) dao.findAll();
        label.setText("");
        index = 0;
        super.start();
    }

    public AbstractDAO getDao() {
        return dao;
    }

    public void setDao(AbstractDAO dao) {
        this.dao = dao;
    }
}
