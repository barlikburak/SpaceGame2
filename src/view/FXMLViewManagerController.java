package view;

import dao.OyuncuDAO;
import dao.PuanDAO;
import entity.Oyuncu;
import entity.Puan;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.SHIPPickerBlue;
import model.SHIPPickerGreen;
import model.SHIPPickerOrange;
import model.SHIPPickerRed;
import movements.ButtonStyle;
import movements.DAOContext;
import movements.Info;
import movements.SHIPToPickContext;
import movements.SpaceGameMainPane;

public class FXMLViewManagerController extends Application implements Initializable {

    @FXML
    private Button playButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button helpButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button pointsButton;
    @FXML
    private Button startButton;
    @FXML
    private Button updateOyuncuButton;
    @FXML
    private AnchorPane shipChooserPane;
    @FXML
    private AnchorPane updatePane;
    @FXML
    private AnchorPane helpPane;
    @FXML
    private AnchorPane pointsPane;
    @FXML
    private ImageView logo;
    @FXML
    private VBox shipBlue;
    @FXML
    private VBox shipGreen;
    @FXML
    private VBox shipOrange;
    @FXML
    private VBox shipRed;
    @FXML
    private ImageView imageRed;
    @FXML
    private ImageView imageOrange;
    @FXML
    private ImageView imageGreen;
    @FXML
    private ImageView imageBlue;
    @FXML
    private ImageView shipBlueCircle;
    @FXML
    private ImageView shipGreenCircle;
    @FXML
    private ImageView shipOrangeCircle;
    @FXML
    private ImageView shipRedCircle;
    @FXML
    private Label helpLabel;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField age;
    @FXML
    private ListView listViewPoints;
    @FXML
    private ListView listViewTime;
    @FXML
    private Text updateText;

    private Stage menuStage;
    private ButtonStyle buttonStyle;
    private SpaceGameMainPane anchorPaneStyle;
    private Oyuncu oyuncu;
    private SHIPToPickContext shipToPickContext;
    private DAOContext daoContext;

    @FXML
    private void startMousePressed(MouseEvent event) throws Exception {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            getButtonStyle().setOnMousePressed(startButton);
            if (this.shipToPickContext != null) {
                start(new Stage());
            }
        }
    }

    @FXML
    private void startMouseReleased(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            getButtonStyle().setOnMouseReleased(startButton);
        }
    }

    @FXML
    private void MouseClikedShipBlue() {
        setIsCircleNotChoosen();
        shipToPickContext = new SHIPToPickContext(new SHIPPickerBlue());
        shipToPickContext.setCircleChoosenImage(shipBlueCircle);
    }

    @FXML
    private void MouseClikedShipGreen() {
        if (oyuncu.getShipList().size() > 1) {
            setIsCircleNotChoosen();
            shipToPickContext = new SHIPToPickContext(new SHIPPickerGreen());
            shipToPickContext.setCircleChoosenImage(shipGreenCircle);
        }
    }

    @FXML
    private void MouseClikedShipOrange() {
        if (oyuncu.getShipList().size() > 2) {
            setIsCircleNotChoosen();
            shipToPickContext = new SHIPToPickContext(new SHIPPickerOrange());
            shipToPickContext.setCircleChoosenImage(shipOrangeCircle);
        }
    }

    @FXML
    private void MouseClikedShipRed() {
        if (oyuncu.getShipList().size() > 3) {
            setIsCircleNotChoosen();
            shipToPickContext = new SHIPToPickContext(new SHIPPickerRed());
            shipToPickContext.setCircleChoosenImage(shipRedCircle);
        }
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
    private void mouseReleasedPlayButton(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            getButtonStyle().setOnMouseReleased(playButton);
        }
    }

    @FXML
    private void mousePressedPlayButton(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            getButtonStyle().setOnMousePressed(playButton);
            getAnchorPaneStyle().showPane(shipChooserPane);
            if (oyuncu.getShipList().size() < 4) {
                imageRed.setImage(new Image("/view/resources/shipchooser/lock.png"));
            }
            if (oyuncu.getShipList().size() < 3) {
                imageOrange.setImage(new Image("/view/resources/shipchooser/lock.png"));
            }
            if (oyuncu.getShipList().size() < 2) {
                imageGreen.setImage(new Image("/view/resources/shipchooser/lock.png"));
            }
        }
    }

    @FXML
    private void mouseEnteredPlayButton() {
        getButtonStyle().setOnMouseEntered(playButton);
    }

    @FXML
    private void mouseExitedPlayButton() {
        getButtonStyle().setOnMouseExited(playButton);
    }

    @FXML
    private void mouseReleasedUpdateButton(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            getButtonStyle().setOnMouseReleased(updateButton);
        }
    }

    @FXML
    private void mousePressedUpdateButton(MouseEvent event) {
        password.setText(oyuncu.getKisi().getLogin().getSifre());
        name.setText(oyuncu.getKisi().getAd());
        surname.setText(oyuncu.getKisi().getSoyad());
        email.setText(oyuncu.getKisi().getE_posta());
        age.setText(String.valueOf(oyuncu.getYas()));
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            getButtonStyle().setOnMousePressed(updateButton);
            getAnchorPaneStyle().showPane(updatePane);
        }
    }

    @FXML
    private void mousePressedUpdateOyuncuButton(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            getButtonStyle().setOnMousePressed(updateOyuncuButton);
            updateOyuncuButton.setPrefHeight(30);
            oyuncu.getKisi().getLogin().setSifre(password.getText());
            oyuncu.getKisi().setAd(name.getText());
            oyuncu.getKisi().setSoyad(surname.getText());
            oyuncu.setYas(Short.valueOf(age.getText()));
            daoContext.setDao(new OyuncuDAO());
            updateText.setVisible(true);
            if (oyuncu.getKisi().getE_posta().equalsIgnoreCase(email.getText())) {
                if ((new Info()).iskayitOnay(name, surname, password, age)) {
                    daoContext.update(this.oyuncu);
                    updateText.setText("BAŞARILI");
                } else {
                    updateText.setText("HATALI BİLGİ!");
                }
            } else {
                oyuncu.getKisi().setE_posta(email.getText());
                if ((new Info()).iskayitOnay(name, surname, email, password, age)) {
                    daoContext.update(this.oyuncu);
                    updateText.setText("BAŞARILI");
                } else {
                    updateText.setText("HATALI BİLGİ!");
                }
            }
        }
    }

    @FXML
    private void mouseReleasedUpdateOyuncuButton(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            getButtonStyle().setOnMouseReleased(updateOyuncuButton);
            updateOyuncuButton.setPrefHeight(26);
        }
    }

    @FXML
    private void mouseEnteredUpdateOyuncuButton() {
        getButtonStyle().setOnMouseEntered(updateOyuncuButton);
    }

    @FXML
    private void mouseExitedUpdateOyuncuButton() {
        getButtonStyle().setOnMouseExited(updateOyuncuButton);
    }

    @FXML
    private void mouseEnteredUpdateButton() {
        getButtonStyle().setOnMouseEntered(updateButton);
    }

    @FXML
    private void mouseExitedUpdateButton() {
        getButtonStyle().setOnMouseExited(updateButton);
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
            getAnchorPaneStyle().showPane(helpPane);
            (new Info()).help(helpLabel);
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
            daoContext.setDao(new PuanDAO());
            ArrayList<Puan> puanList = (ArrayList<Puan>) daoContext.findById(oyuncu.getOyuncu_id());
            String str;
            listViewPoints.getItems().remove(0, listViewPoints.getItems().size());
            listViewTime.getItems().remove(0, listViewTime.getItems().size());
            for (int i = 0; i < puanList.size(); i++) {
                str = (i + 1) + "-) " + puanList.get(i).getPuan();
                listViewPoints.getItems().add(str);
                str = (i + 1) + "-) " + puanList.get(i).getZaman();
                listViewTime.getItems().add(str);
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        daoContext = new DAOContext();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXMLGameViewManager.fxml"));

        Parent root = loader.load();

        FXMLGameViewManagerController fXMLGameViewManagerController = loader.getController();
        fXMLGameViewManagerController.setShipToPick(this.shipToPickContext);
        fXMLGameViewManagerController.setMenuStage(this.menuStage);
        fXMLGameViewManagerController.setGameStage(primaryStage);
        fXMLGameViewManagerController.setOyuncu(oyuncu);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        this.menuStage.hide();

        primaryStage.show();
    }

    public void setMenuStage(Stage menuStage) {
        this.menuStage = menuStage;
    }

    public ButtonStyle getButtonStyle() {
        if (this.buttonStyle == null) {
            this.buttonStyle = new ButtonStyle();
        }
        return buttonStyle;
    }

    public SpaceGameMainPane getAnchorPaneStyle() {
        if (this.anchorPaneStyle == null) {
            this.anchorPaneStyle = new SpaceGameMainPane();
        }
        return anchorPaneStyle;
    }

    private void setIsCircleNotChoosen() {
        if (shipToPickContext != null) {
            shipToPickContext.setCircleNotChoosesen();
        }
    }

    public void setOyuncu(Oyuncu oyuncu) {
        this.oyuncu = oyuncu;
    }

}
