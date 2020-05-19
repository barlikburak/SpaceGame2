/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.AdminDAO;
import dao.GameDAO;
import dao.OyuncuDAO;
import dao.PuanDAO;
import entity.Admin;
import entity.Kisi;
import entity.Login;
import entity.Oyuncu;
import entity.Puan;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import movements.ButtonStyle;
import movements.DAOContext;
import movements.SpaceGameMainPane;

/**
 * FXML Controller class
 *
 * @author burak
 */
public class FXMLAdminViewManagerController implements Initializable {

    @FXML
    private ImageView logo;
    @FXML
    private Button exitButton;
    @FXML
    private Button pointsButton;
    @FXML
    private Button helpButton;
    @FXML
    private Button oyuncuButton;
    @FXML
    private Button adminButton;
    @FXML
    private Button updateHelpButton;
    @FXML
    private Button updateAndInsertButton;
    @FXML
    private AnchorPane pointsPane;
    @FXML
    private AnchorPane helpPane;
    @FXML
    private AnchorPane playerPane;
    @FXML
    private AnchorPane adminPane;
    @FXML
    private AnchorPane updateAndInsertPane;
    @FXML
    private ListView listViewPoints;
    @FXML
    private ListView listViewPlayer;
    @FXML
    private ListView listViewAdmin;
    @FXML
    private TextArea textAreaUpdate;
    @FXML
    private Text updateHelpText;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField password;
    @FXML
    private Hyperlink linkInsert;
    @FXML
    private Hyperlink linkUpdate;
    @FXML
    private Text successful;

    private Stage menuStage;
    private Admin admin;
    private ButtonStyle buttonStyle;
    private SpaceGameMainPane anchorPaneStyle;
    private DAOContext daoContext;

    @FXML
    private void linkInsertClicked() {
        name.setText("");
        surname.setText("");
        email.setText("");
        phone.setText("");
        password.setText("");
        getAnchorPaneStyle().showPane(updateAndInsertPane);
        updateAndInsertButton.setText("Insert");
        linkInsert.setVisited(false);
    }

    @FXML
    private void linkUpdateClicked() {
        name.setText(admin.getKisi().getAd());
        surname.setText(admin.getKisi().getSoyad());
        email.setText(admin.getKisi().getE_posta());
        phone.setText(admin.getTelefon());
        password.setText(admin.getKisi().getLogin().getSifre());
        updateAndInsertButton.setText("Update");
        linkUpdate.setVisited(false);
        getAnchorPaneStyle().showPane(updateAndInsertPane);
    }

    @FXML
    private void mousePressedUpdateAndInsertButton() {
        buttonStyle.setOnMousePressed(updateAndInsertButton);
        updateAndInsertButton.setPrefHeight(30);
        Login tempLogin = new Login(admin.getKisi().getLogin().getLogin_id(),
                password.getText());
        Kisi tempKisi = new Kisi(admin.getKisi().getKisi_id(), name.getText(),
                surname.getText(), email.getText(), tempLogin);
        Admin tempAdmin = new Admin(admin.getAdmin_id(),
                phone.getText(), tempKisi);

        daoContext.setDao(new AdminDAO());

        if (updateAndInsertButton.getText().toLowerCase().equalsIgnoreCase("update")) {
            admin = tempAdmin;
            daoContext.update(admin);
        } else {
            daoContext.insert(tempAdmin);
        }
        successful.setVisible(true);
    }

    @FXML
    private void mouseReleasedUpdateAndInsertButton() {
        buttonStyle.setOnMouseReleased(updateAndInsertButton);
        updateAndInsertButton.setPrefHeight(26);
    }

    @FXML
    private void mouseEnteredUpdateAndInsertButton() {
        getButtonStyle().setOnMouseEntered(updateAndInsertButton);
    }

    @FXML
    private void mouseExitedUpdateAndInsertButton() {
        getButtonStyle().setOnMouseExited(updateAndInsertButton);
    }

    @FXML
    private void mouseReleasedUpdateHelpButton(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            getButtonStyle().setOnMouseReleased(updateHelpButton);
            updateHelpButton.setPrefHeight(26);
        }
    }

    @FXML
    private void mousePressedUpdateHelpButton(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            getButtonStyle().setOnMousePressed(updateHelpButton);
            updateHelpButton.setPrefHeight(30);
            updateHelpText.setVisible(true);
            updateHelpText.setText("SUCCESSFUL!");

            daoContext.setDao(new GameDAO());

            daoContext.update(textAreaUpdate.getText());
        }
    }

    @FXML
    private void mouseEnteredUpdateHelpButton() {
        getButtonStyle().setOnMouseEntered(updateHelpButton);
    }

    @FXML
    private void mouseExitedUpdateHelpButton() {
        getButtonStyle().setOnMouseExited(updateHelpButton);
    }

    @FXML
    private void mouseReleasedAdminButton(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            getButtonStyle().setOnMouseReleased(adminButton);
        }
    }

    @FXML
    private void mousePressedAdminButton(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            getButtonStyle().setOnMousePressed(adminButton);
            successful.setVisible(false);
            ListView listView;

            daoContext.setDao(new AdminDAO());
            ArrayList<Admin> adminList = (ArrayList<Admin>) daoContext.findAll();
            listViewAdmin.getItems().clear();
            for (int i = 0; i < adminList.size(); i++) {
                listView = new ListView();
                listView.setOrientation(Orientation.HORIZONTAL);
                listView.setPrefHeight(50);
                listView.getItems().add("Name: "
                        + adminList.get(i).getKisi().getAd());
                listView.getItems().add("Surname: "
                        + adminList.get(i).getKisi().getSoyad());
                listView.getItems().add("EMail: "
                        + adminList.get(i).getKisi().getE_posta());
                listView.getItems().add("Phone: "
                        + adminList.get(i).getTelefon());
                listViewAdmin.getItems().add(listView);
            }
            getAnchorPaneStyle().showPane(adminPane);
        }
    }

    @FXML
    private void mouseEnteredAdminButton() {
        getButtonStyle().setOnMouseEntered(adminButton);
    }

    @FXML
    private void mouseExitedAdminButton() {
        getButtonStyle().setOnMouseExited(adminButton);
    }

    @FXML
    private void mouseReleasedOyuncuButton(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            getButtonStyle().setOnMouseReleased(oyuncuButton);
        }
    }

    @FXML
    private void mousePressedOyuncuButton(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            getButtonStyle().setOnMousePressed(oyuncuButton);
            String str;
            ArrayList<Puan> puanList;
            ListView listView;
            listViewPlayer.getItems().clear();

            daoContext.setDao(new OyuncuDAO());
            ArrayList<Oyuncu> oyuncuList = (ArrayList<Oyuncu>) daoContext.findAll();

            daoContext.setDao(new PuanDAO());
            for (int i = 0; i < oyuncuList.size(); i++) {
                listView = new ListView();
                listView.setOrientation(Orientation.HORIZONTAL);
                listView.setPrefHeight(50);
                listView.getItems().add("Name: " + oyuncuList.get(i).getKisi().getAd());
                listView.getItems().add("Surname: " + oyuncuList.get(i).getKisi().getSoyad());
                listView.getItems().add("Age: " + oyuncuList.get(i).getYas());
                str = "Score: ";
                puanList = (ArrayList<Puan>) daoContext.findById(oyuncuList.get(i).getOyuncu_id());
                if (!puanList.isEmpty()) {
                    str += puanList.get(0).getPuan();
                }
                listView.getItems().add(str);
                listViewPlayer.getItems().add(listView);
            }
            getAnchorPaneStyle().showPane(playerPane);
        }
    }

    @FXML
    private void mouseEnteredOyuncuButton() {
        getButtonStyle().setOnMouseEntered(oyuncuButton);
    }

    @FXML
    private void mouseExitedOyuncuButton() {
        getButtonStyle().setOnMouseExited(oyuncuButton);
    }

    @FXML
    private void mouseReleasedHelpButton(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            getButtonStyle().setOnMouseReleased(helpButton);
        }
    }

    @FXML
    private void mousePressedHelpButton(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            getButtonStyle().setOnMousePressed(helpButton);

            daoContext.setDao(new GameDAO());

            textAreaUpdate.setText((String) daoContext.findAll());
            getAnchorPaneStyle().showPane(helpPane);
        }
    }

    @FXML
    private void mouseEnteredHelpButton() {
        getButtonStyle().setOnMouseEntered(helpButton);
    }

    @FXML
    private void mouseExitedHelpButton() {
        getButtonStyle().setOnMouseExited(helpButton);
    }

    @FXML
    private void mouseReleasedPointsButton(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            getButtonStyle().setOnMouseReleased(pointsButton);
        }
    }

    @FXML
    private void mousePressedPointsButton(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            getButtonStyle().setOnMousePressed(pointsButton);
            ArrayList<Puan> puanList;
            ListView listView;
            listViewPoints.getItems().clear();

            daoContext.setDao(new PuanDAO());
            puanList = (ArrayList<Puan>) daoContext.findAll();
            for (int i = 0; i < puanList.size(); i++) {
                listView = new ListView();
                listView.setOrientation(Orientation.HORIZONTAL);
                listView.setPrefHeight(50);
                listView.getItems().add("Name: "
                        + puanList.get(i).getOyuncu().getKisi().getAd()
                        + " "
                        + (puanList.get(i).getOyuncu().getKisi().getSoyad()));
                listView.getItems().add("Point :"
                        + puanList.get(i).getPuan());
                listView.getItems().add("Time: "
                        + puanList.get(i).getZaman());
                listViewPoints.getItems().add(listView);
            }
            getAnchorPaneStyle().showPane(pointsPane);
        }
    }

    @FXML
    private void mouseEnteredPointsButton() {
        getButtonStyle().setOnMouseEntered(pointsButton);
    }

    @FXML
    private void mouseExitedPointsButton() {
        getButtonStyle().setOnMouseExited(pointsButton);
    }

    @FXML
    private void mouseEnteredLogo() {
        logo.setEffect(new DropShadow());
    }

    @FXML
    private void mouseExitedLogo() {
        logo.setEffect(null);
    }

    @FXML
    private void mouseReleasedExitButton(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            getButtonStyle().setOnMouseReleased(exitButton);
        }
    }

    @FXML
    private void mousePressedExitButton(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            getButtonStyle().setOnMousePressed(exitButton);
            Platform.exit();
        }
    }

    @FXML
    private void mouseEnteredExitButton() {
        getButtonStyle().setOnMouseEntered(exitButton);
    }

    @FXML
    private void mouseExitedExitButton() {
        getButtonStyle().setOnMouseExited(exitButton);
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        daoContext = new DAOContext();
    }

    public SpaceGameMainPane getAnchorPaneStyle() {
        if (this.anchorPaneStyle == null) {
            this.anchorPaneStyle = new SpaceGameMainPane();
        }
        return anchorPaneStyle;
    }

    public ButtonStyle getButtonStyle() {
        if (this.buttonStyle == null) {
            this.buttonStyle = new ButtonStyle();
        }
        return buttonStyle;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setMenuStage(Stage menuStage) {
        this.menuStage = menuStage;
    }

}
