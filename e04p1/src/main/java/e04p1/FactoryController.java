package e04p1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class FactoryController {

    @FXML
    private TextField heightField;

    @FXML
    private TextField widthField;

    @FXML
    private TextField areaText; 

    @FXML
    private Label areaLabel;

    @FXML
    private Label widthLabel;

    @FXML
    private Label heightLabel;
    
    @FXML
    private Button rectangleButton;
    
    @FXML
    private Button circleButton;
    
    @FXML
    private void circleButtonClicked(ActionEvent event) {
    	
    	
    }
    
    @FXML
    private void rectangleButtonClicked(ActionEvent event) {
    	
    }
    
}
