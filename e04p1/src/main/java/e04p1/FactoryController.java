package e04p1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class FactoryController {

    @FXML
    private MenuItem menuDelete;

    @FXML
    private Button buttonFullScreen;

    @FXML
    private MenuItem menuClose;

    @FXML
    private VBox vboxLeft;

    @FXML
    private TitledPane titledPane1;

    @FXML
    private TitledPane titledPane2;

    @FXML
    private VBox vboxTop;

    @FXML
    private TitledPane titledPane3;

    @FXML
    private TitledPane titledPane4;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Label labelModes;

    @FXML
    private Label labelStatusBar;

    @FXML
    private Pane paneDessin;

    @FXML
    private HBox hBoxOutils;

    @FXML
    private Label labelOptions;

    @FXML
    private Button buttonAdd;

    @FXML
    void menuCloseClicked(ActionEvent event) {
    	labelStatusBar.setText("Fermeture de l'application...");
    }

    @FXML
    void menuDeleteClicked(ActionEvent event) {
    	labelStatusBar.setText("Supression de l'�l�ment...");
    }
    
    @FXML
    void buttonAddClicked(ActionEvent event) {
    	labelStatusBar.setText("Ajout d'un �l�ment...");
    }

    @FXML
    void buttonFullScreenClicked(ActionEvent event) {
    	labelStatusBar.setText("Activation du mode plein �cran...");
    }
    @FXML
    void picture1Clicked(ActionEvent event) {
    	labelStatusBar.setText("Image 1 selectionné");
    }
    @FXML
    void picture2Clicked(ActionEvent event) {
    	labelStatusBar.setText("Image 2 selectionné");
    }
    @FXML
    void picture3Clicked(ActionEvent event) {
    	labelStatusBar.setText("Image 3 selectionné");
    }
    @FXML
    void picture4Clicked(ActionEvent event) {
    	labelStatusBar.setText("Image 4 selectionné");
    }
    @FXML
    void picture5Clicked(ActionEvent event) {
    	labelStatusBar.setText("Image 5 selectionné");
    }
    @FXML
    void picture6Clicked(ActionEvent event) {
    	labelStatusBar.setText("Image 6 selectionné");
    }
    @FXML
    void picture7Clicked(ActionEvent event) {
    	labelStatusBar.setText("Image 7 selectionné");   
    	ImageView image7 = new ImageView(new Image(Main.class.getResourceAsStream("Frog.png")));    	
    	}
	
}


    


