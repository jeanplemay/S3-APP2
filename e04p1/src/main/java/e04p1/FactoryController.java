package e04p1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import labo.ShapeFactory.eShape;
import labo.Circle;
import labo.Rectangle;

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
    	
    	Shape shape = ShapeFactory.getShape(eShape.Circle,
    			Integer.parseInt(widthField.getText()),
    			Integer.parseInt(heightField.getText()) );   
    	areaText.setText(""+shape.getArea());
    }
    
    @FXML
    private void rectangleButtonClicked(ActionEvent event) {
    	Shape shape = ShapeFactory.getShape(eShape.Rectangle,
    			Integer.parseInt(widthField.getText()),
    			Integer.parseInt(heightField.getText()) );   
    	areaText.setText(""+shape.getArea());
    }
    
}
