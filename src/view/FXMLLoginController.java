/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.AbstractDAO;
import dao.AdminDAO;
import dao.LoginDAO;
import dao.OyuncuDAO;
import entity.Admin;
import entity.Kisi;
import entity.Login;
import entity.Oyuncu;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import movements.Info;
import movements.SpaceGameMainPane;

/**
 * FXML Controller class
 *
 * @author burak
 */
public class FXMLLoginController extends Application implements Initializable {

    @FXML
    private ImageView logo;
    @FXML
    private Button buttonGiris;
    @FXML
    private Button buttonKayit;
    @FXML
    private TextField fieldEPosta;
    @FXML
    private TextField fieldSifre;
    @FXML
    private TextField fieldAd;
    @FXML
    private TextField fieldSoyad;
    @FXML
    private TextField fieldEPostaKayit;
    @FXML
    private TextField fieldSifreKayit;
    @FXML
    private TextField fieldYas;
    @FXML
    private RadioButton radioAdmin;
    @FXML
    private RadioButton radioOyuncu;
    @FXML
    private Text uyari;
    @FXML
    private Text uyariKayit;
    @FXML
    private AnchorPane loginAnchorPane;
    @FXML
    private AnchorPane registerAnchorPane;
    @FXML
    private Hyperlink registerLink;
    @FXML
    private Hyperlink loginLink;

    private Stage loginStage;
    private SpaceGameMainPane anchorPaneStyle;
    private String giris;
    private Info info;
    private AbstractDAO dao;

    /**
     * Efekt verdik.
     */
    @FXML
    private void mouseEnteredLogo() {
        logo.setEffect(new DropShadow());
    }

    /**
     * Efekti kaldırdık.
     */
    @FXML
    private void mouseExitedLogo() {
        logo.setEffect(null);
    }

    /**
     * Login kontrolünü yapar.
     *
     * @param event mouse hareketi
     */
    @FXML
    private void mouseButtonGirisPressed(MouseEvent event) throws Exception {
        buttonGiris.setLayoutY(buttonGiris.getLayoutY() + 4);
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            LoginDAO ldao = new LoginDAO();
            giris = null;
            if (radioOyuncu.isSelected()) {
                giris = "oyuncu";
            } else if (radioAdmin.isSelected()) {
                giris = "admin";
            }
            if (giris != null && ldao.login(fieldEPosta.getText(), fieldSifre.getText(), giris)) {
                start(new Stage());
            } else {
                uyari.setVisible(true);
            }
        }
    }

    /**
     * Tıklama bittikten sonra konumu değiştirdik.
     */
    @FXML
    private void mouseButtonGirisReleased() {
        buttonGiris.setLayoutY(buttonGiris.getLayoutY() - 4);
    }

    /**
     * Efekt verdik.
     */
    @FXML
    private void mouseButtonGirisEntered() {
        buttonGiris.setEffect(new DropShadow());
    }

    /**
     * Efekti kaldırdık.
     */
    @FXML
    private void mouseButtonGirisExited() {
        buttonGiris.setEffect(null);
    }

    /**
     * Kulanıcı seçiminde Admini işaretedik.
     */
    @FXML
    private void mouseRadioAdminAction() {
        if (radioOyuncu.isSelected()) {
            radioOyuncu.setSelected(false);
        }
        radioAdmin.setSelected(true);
    }

    /**
     * Kulanıcı seçiminde Oyuncuyu işaretedik.
     */
    @FXML
    private void mouseRadioOyuncuAction() {
        if (radioAdmin.isSelected()) {
            radioAdmin.setSelected(false);
        }
        radioOyuncu.setSelected(true);
    }

    /**
     * Kayıt ol panelini açıyoruz.
     */
    @FXML
    private void mouseRegisterLinkAction() {
        getAnchorPaneStyle().showPane(registerAnchorPane);
        registerLink.setVisited(false);
    }

    /**
     * Giriş panelini açıyoruz.
     */
    @FXML
    private void mouesLoginLinkAction() {
        getAnchorPaneStyle().showPane(loginAnchorPane);
        loginLink.setVisited(false);
    }

    /**
     * Kayıt işlemi yapılıyor.
     *
     * @param event mouse durumu
     */
    @FXML
    private void mouseButtonKayitPressed(MouseEvent event) {
        buttonKayit.setLayoutY(buttonKayit.getLayoutY() + 4);
        if (event.getButton().equals(MouseButton.PRIMARY) && getInfo().iskayitOnay(fieldAd, fieldSoyad, fieldEPostaKayit, fieldSifreKayit, fieldYas)) {

            Login login = new Login(0, fieldSifreKayit.getText());
            Kisi kisi = new Kisi(0, fieldAd.getText(), fieldSoyad.getText(), fieldEPostaKayit.getText(), login);
            Oyuncu oyuncu = new Oyuncu(0, Short.valueOf(fieldYas.getText()), kisi, null);

            dao = new OyuncuDAO();
            dao.insert(oyuncu);

            uyariKayit.setText("KAYIT BAŞARILI!");
            uyariKayit.setVisible(true);
        } else {
            uyariKayit.setText("Bilgileri Düzgün Giriniz!");
            uyariKayit.setVisible(true);

        }
    }

    /**
     * Kayıt butonuna tıklama bittiğinde çalışır.
     */
    @FXML
    private void mouseButtonKayitReleased() {
        buttonKayit.setLayoutY(buttonKayit.getLayoutY() - 4);
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String fxmlLoaderResource;
        if (giris.toLowerCase().equalsIgnoreCase("admin")) {
            fxmlLoaderResource = "/view/FXMLAdminViewManager.fxml";
        } else {
            fxmlLoaderResource = "/view/FXMLViewManager.fxml";
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlLoaderResource));

        Parent root = loader.load();

        if (giris.toLowerCase().equalsIgnoreCase("admin")) {
            FXMLAdminViewManagerController fXMLAdminViewManagerController = loader.getController();
            fXMLAdminViewManagerController.setMenuStage(primaryStage);
            dao = new AdminDAO();
            fXMLAdminViewManagerController.setAdmin((Admin) dao.findByObject(fieldEPosta.getText()));
        } else {
            FXMLViewManagerController fXMLViewManagerController = loader.getController();
            fXMLViewManagerController.setMenuStage(primaryStage);
            dao = new OyuncuDAO();
            fXMLViewManagerController.setOyuncu((Oyuncu) dao.findByObject(fieldEPosta.getText()));
        }

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        this.loginStage.hide();

        primaryStage.show();
    }

    public SpaceGameMainPane getAnchorPaneStyle() {
        if (this.anchorPaneStyle == null) {
            this.anchorPaneStyle = new SpaceGameMainPane(loginAnchorPane);
        }
        return anchorPaneStyle;
    }

    public Info getInfo() {
        if (this.info == null) {
            this.info = new Info();
        }
        return info;
    }

    public void setLoginStage(Stage loginStage) {
        this.loginStage = loginStage;
    }

}
