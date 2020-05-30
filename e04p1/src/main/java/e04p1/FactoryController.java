package e04p1;

import java.util.Vector;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class FactoryController {
	
	static final int a = 21;
	static final int b = 2;
	static final int c = 30;

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
    private GridPane gridPane1;

    @FXML
    private GridPane gridPane2;

    @FXML
    private GridPane gridPane3;

    @FXML
    private GridPane gridPane4;

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
    	labelStatusBar.setText("Image 1 selectionn�");
    }
    @FXML
    void picture2Clicked(ActionEvent event) {
    	labelStatusBar.setText("Image 2 selectionn�");
    }
    @FXML
    void picture3Clicked(ActionEvent event) {
    	labelStatusBar.setText("Image 3 selection�");
    }
    @FXML
    void picture4Clicked(ActionEvent event) {
    	labelStatusBar.setText("Image 4 selectionn�");
    }
    @FXML
    void picture5Clicked(ActionEvent event) {
    	labelStatusBar.setText("Image 5 selectionn�");
    }
    @FXML
    void picture6Clicked(ActionEvent event) {
    	labelStatusBar.setText("Image 6 selectionn�");
    }
    @FXML
    void picture7Clicked(ActionEvent event) {
    	labelStatusBar.setText("Image 7 selectionn�");   
    	ImageView image7 = new ImageView(new Image(Main.class.getResourceAsStream("Frog.png")));    	
    	}
    double x;
    double y;
    @FXML
    void initialize() {
    	
    	
    	Vector<MyShapes> v = new Vector<MyShapes>();
    	
    	//Energy source
    	Shape oval = new Ellipse(a,a/2);
    	oval.setFill(Color.web("98FB98"));
    	oval.setStroke(Color.web("005000"));
    	oval.setStrokeWidth(b);
    	gridPane1.add(oval, 0, 0);
    	
    	
    	oval.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{
				
				Shape oval = new Ellipse(a,a/2);
		    	oval.setFill(Color.web("98FB98"));
		    	oval.setStroke(Color.web("005000"));
		    	oval.setStrokeWidth(b);
		    	MyShapes MSoval1 = new MyShapes(oval);
		    	v.add(MSoval1);
		    	paneDessin.getChildren().add(oval);	
		    	
		    	
		    	paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setLayoutY(v.lastElement().x);
		    	paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setLayoutX(v.lastElement().y);
		    	
		    	
		    	
		    	paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setOnMouseReleased(new EventHandler<MouseEvent>() 
		    	{
					@Override
					public void handle(MouseEvent event) 
					{
						for(int i = 0; i < v.size(); i++)
						{
							if(v.elementAt(i).shape.equals(((Node) event.getSource())))
							{
								v.elementAt(i).x += event.getX();
								v.elementAt(i).y += event.getY();
								((Node) event.getSource()).setLayoutX(v.elementAt(i).x);
								((Node) event.getSource()).setLayoutY(v.elementAt(i).y);
							}
						}

						
					}
		    	});
			}   		
    	});
    	
    	//Mono-physical converter
    	Shape rectangle = new Rectangle(a,a);
    	rectangle.setFill(Color.web("FFD700"));
    	rectangle.setStroke(Color.web("FF0000"));
    	rectangle.setStrokeWidth(b);
    	gridPane1.add(rectangle, 1, 0);
    	
    	//Multi-physical converter
    	Shape circle = new Circle(a/2);
    	circle.setFill(Color.web("FFD700"));
    	circle.setStroke(Color.web("FF0000"));
    	circle.setStrokeWidth(b);
    	gridPane1.add(circle, 0, 1);
    	
    	//Energy accumulation
    	Shape shape = Shape.subtract(new Rectangle(a/2,a), new Line(a/2,0,0,a));
    	shape.setFill(Color.web("FFD700"));
    	shape.setStroke(Color.web("FF0000"));
    	shape.setStrokeWidth(b);
    	gridPane1.add(shape, 1, 1);
    	
    	//Mono-physical coupling
    	Shape shape2 = Shape.union(new Rectangle(a,a), new Rectangle(a*2/3,a*2/3,a,a));
    	shape2.setFill(Color.web("FFD700"));
    	shape2.setStroke(Color.web("FF0000"));
    	shape2.setStrokeWidth(b);
    	gridPane1.add(shape2, 0, 2);
    	
    	//Multi-physical coupling
    	Shape shape3 = Shape.union( new Circle(a/2),  new Circle(0,a*2/3,a/2));
    	shape3.setFill(Color.web("FFD700"));
    	shape3.setStroke(Color.web("FF0000"));
    	shape3.setStrokeWidth(b);
    	gridPane1.add(shape3, 1, 2);
    	
    	//Direct inversion
    	Shape polygon = new Polygon(
        		a/4.0,0.0,
        		a*5.0/4.0,0.0,
        		a*1.0,a*1.0,
        		0.0,a*1.0
        		);
    	polygon.setFill(Color.web("87CEEB"));
    	polygon.setStroke(Color.web("0000FF"));
    	polygon.setStrokeWidth(b/2.0);
    	gridPane2.add(polygon, 0, 0);
    	
    	//Indirect inversion
    	Shape polygon2 = new Polygon(
        		a/4.0,0.0,
        		a*5.0/4.0,0.0,
        		a*1.0,a*1.0,
        		0.0,a*1.0
        		);
    	Shape shape4 = Shape.subtract(polygon2, new Line(a/4,0,a,a));
    	shape4.setFill(Color.web("87CEEB"));
    	shape4.setStroke(Color.web("0000FF"));
    	shape4.setStrokeWidth(b/2.0);
    	gridPane2.add(shape4, 1, 0);
    	
    	//Strategy
    	Shape polygon3 = new Polygon(
        		a/4.0,0.0,
        		a*13.0/4.0,0.0,
        		a*3.0,a*1.0,
        		0.0,a*1.0
        		);
    	polygon3.setFill(Color.web("0000FF"));
    	polygon3.setStroke(Color.web("0000FF"));
    	polygon3.setStrokeWidth(b/2.0);
    	gridPane3.add(polygon3, 0, 0);
    	
    	//Energy source estimator
    	Shape oval2 = new Ellipse(a,a/2);
    	oval2.setFill(Color.web("EE82EE"));
    	oval2.setStroke(Color.web("0000FF"));
    	oval2.setStrokeWidth(b/2.0);
    	gridPane4.add(oval2,0,0);
    	
    	//Mono-physical converter estimator
    	Shape rectangle5 = new Rectangle(a,a);
    	rectangle5.setFill(Color.web("EE82EE"));
    	rectangle5.setStroke(Color.web("0000FF"));
    	rectangle5.setStrokeWidth(b/2.0);
    	gridPane4.add(rectangle5, 1, 0);
    	
    	//Multi-physical converter estimator
    	Shape circle2 = new Circle(a/2);
    	circle2.setFill(Color.web("EE82EE"));
    	circle2.setStroke(Color.web("0000FF"));
    	circle2.setStrokeWidth(b/2.0);
    	gridPane4.add(circle2, 0, 1);
    	
    	//Energy accumulation estimator
    	Shape shape5 = Shape.subtract(new Rectangle(a/2,a), new Line(a/2,0,0,a));
    	shape5.setFill(Color.web("EE82EE"));
    	shape5.setStroke(Color.web("0000FF"));
    	shape5.setStrokeWidth(b/2.0);
    	gridPane4.add(shape5, 1, 1);
    	 
    	//Mono-physical coupling estimator
    	Shape shape6 = Shape.union(new Rectangle(a,a), new Rectangle(a*2/3,a*2/3,a,a));
    	shape6.setFill(Color.web("EE82EE"));
    	shape6.setStroke(Color.web("0000FF"));
    	shape6.setStrokeWidth(b/2.0);
    	gridPane4.add(shape6, 0, 2);
    	
    	//Multi-physical coupling estimator
    	Shape shape7 = Shape.union( new Circle(a/2),  new Circle(0,a*2/3,a/2));
    	shape7.setFill(Color.web("EE82EE"));
    	shape7.setStroke(Color.web("0000FF"));
    	shape7.setStrokeWidth(b/2.0);
    	gridPane4.add(shape7, 1, 2);
    	
    	


    }
	
}


    


