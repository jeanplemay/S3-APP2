package e04p1;

import java.util.Vector;

import e04p1.MyShapes.EShape;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

//This class makes part of the design pattern MVC together with class MyShapes and scene.fxml
public class FactoryController {
	
	
	Vector<MyShapes> v;
	
	EShape draggedShape;
	
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
    boolean success;
    @FXML
    void initialize() {
    	//MouseControlUtil.makeDraggable();
    	paneDessin.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(2.5))));
    	paneDessin.setOnDragEntered(new EventHandler<DragEvent>() {
			@Override	
			public void handle(DragEvent event) {
				labelStatusBar.setText("DragOnPaneDessin");
				event.acceptTransferModes(TransferMode.ANY);
		    	paneDessin.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(2.5))));
			}	
    	});
    	paneDessin.setOnDragExited(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				paneDessin.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(2.5))));			
			}
    		
    	});
    	paneDessin.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				event.acceptTransferModes(TransferMode.ANY);				
			}   		
    	});
    	paneDessin.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {	
				addShape(draggedShape, event.getSceneX() - 166, event.getSceneY() - 50);
                event.consume();
                }  		
    	});


    	v = new Vector<MyShapes>();  	
    	
    	//Energy source
    	MyShapes energySource = new MyShapes(EShape.EnergySource);
    	gridPane1.add(energySource, 0, 0);
    	
    	
    	energySource.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {				
				labelStatusBar.setText("EnergySourceDrag");
				Dragboard db = energySource.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
		        content.putString("Dragged");
				db.setContent(content);
				draggedShape = EShape.EnergySource;		
			}    		
    	});
	
    	/*energySource.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
		    	addShape(EShape.EnergySource);
		    	event.consume();
		    	labelStatusBar.setText("EnergySource");
			}   		
    	});*/
    	
   	
    	//Mono-physical converter
 /*   	MyShapes monoPhysicalConverter = new MyShapes(EShape.MonoPhysicalConverter);
    	gridPane1.add(monoPhysicalConverter, 1, 0);
    	
    	monoPhysicalConverter.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
				addShape(EShape.MonoPhysicalConverter);
				event.consume();
			}   		
    	});
    	
    	//Multi-physical converter
    	MyShapes multiPhysicalConverter = new MyShapes(EShape.MultiPhysicalConverter);
    	gridPane1.add(multiPhysicalConverter, 0, 1);
    	
    	multiPhysicalConverter.setOnMouseClicked(new EventHandler<MouseEvent>(){

    			@Override
    			public void handle(MouseEvent event)
    			{				
    				addShape(EShape.MultiPhysicalConverter);
    				event.consume();
    			}   		
        	});    	
    	
    	//Energy accumulation
    	MyShapes energyAccumulation = new MyShapes(EShape.EnergyAccumulation);
    	gridPane1.add(energyAccumulation, 1, 1);
    	
    	energyAccumulation.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
				addShape(EShape.EnergyAccumulation);
				event.consume();
			}   		
    	});
    	
    	//Mono-physical coupling
    	MyShapes monoPhysicalCoupling = new MyShapes(EShape.MonoPhysicalCoupling);
    	gridPane1.add(monoPhysicalCoupling, 0, 2);
    	
    	monoPhysicalCoupling.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{		
				addShape(EShape.MonoPhysicalCoupling);
				event.consume();
			}   		
    	});
    	
    	//Multi-physical coupling
    	MyShapes multiPhysicalCoupling = new MyShapes(EShape.MultiPhysicalCoupling);
    	gridPane1.add(multiPhysicalCoupling, 1, 2);
    	
    	multiPhysicalCoupling.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
				addShape(EShape.MultiPhysicalCoupling);
				event.consume();
			}   		
    	});
    	
    	//Direct inversion
    	MyShapes directInversion = new MyShapes(EShape.DirectInversion);
    	gridPane2.add(directInversion, 0, 0);
    	
    	directInversion.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
				addShape(EShape.DirectInversion);
				event.consume();
			}   		
    	});
    	
    	
    	//Indirect inversion
    	MyShapes indirectInversion = new MyShapes(EShape.IndirectInversion);
    	gridPane2.add(indirectInversion, 1, 0);
    	
    	indirectInversion.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
				addShape(EShape.IndirectInversion);
				event.consume();
			}   		
    	});
    	
    	//Strategy
    	MyShapes strategy = new MyShapes(EShape.Strategy);
    	gridPane3.add(strategy, 0, 0);
    	
    	strategy.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
				addShape(EShape.Strategy);
				event.consume();
			}   		
    	});
    	
    	//Energy source estimator
    	MyShapes energySourceEstimator = new MyShapes(EShape.EnergySourceEstimator);
    	gridPane4.add(energySourceEstimator,0,0);
    	
    	energySourceEstimator.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
				addShape(EShape.EnergySourceEstimator);
				event.consume();
			}   		
    	});
    	
    	//Mono-physical converter estimator
    	MyShapes monoPhysicalConverterEstimator = new MyShapes(EShape.MonoPhysicalConverterEstimator);
    	gridPane4.add(monoPhysicalConverterEstimator, 1, 0);
    	
    	monoPhysicalConverterEstimator.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
				addShape(EShape.MonoPhysicalConverterEstimator);
				event.consume();
			}   		
    	});    	
    	
    	//Multi-physical converter estimator
    	MyShapes multiPhysicalConverterEstimator = new MyShapes(EShape.MultiPhysicalConverterEstimator);
    	gridPane4.add(multiPhysicalConverterEstimator, 0, 1);
    	
    	multiPhysicalConverterEstimator.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
				addShape(EShape.MultiPhysicalConverterEstimator);
				event.consume();
			}   		
    	});
    	
    	//Energy accumulation estimator
    	MyShapes energyAccumulationEstimator = new MyShapes(EShape.EnergyAccumulationEstimator);
    	gridPane4.add(energyAccumulationEstimator, 1, 1);
    	
    	energyAccumulationEstimator.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
				addShape(EShape.EnergyAccumulationEstimator);
				event.consume();
			}   		
    	});
    	 
    	//Mono-physical coupling estimator
    	MyShapes monoPhysicalCouplingEstimator = new MyShapes(EShape.MonoPhysicalCouplingEstimator);
    	gridPane4.add(monoPhysicalCouplingEstimator, 0, 2);
    	
    	monoPhysicalCouplingEstimator.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
				addShape(EShape.MonoPhysicalCouplingEstimator);
				event.consume();
			}   		
    	});    	
    	
    	//Multi-physical coupling estimator
    	MyShapes multiPhysicalCouplingEstimator = new MyShapes(EShape.MultiPhysicalCouplingEstimator);
    	gridPane4.add(multiPhysicalCouplingEstimator, 1, 2);
    	
    	multiPhysicalCouplingEstimator.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
				addShape(EShape.MultiPhysicalCouplingEstimator);
				event.consume();
			}   		
    	});
    	*/
    }
   
	public void move(MouseEvent event) 
	{
		for(int i = 0; i < v.size(); i++)
		{
			if(v.elementAt(i).equals(((Node) event.getSource())))
			{
				v.elementAt(i).x += event.getX();
				v.elementAt(i).y += event.getY();
				((Node) event.getSource()).setLayoutX(v.elementAt(i).x);
				((Node) event.getSource()).setLayoutY(v.elementAt(i).y);
				break;
			}
		}	
	}
	
	public void addShape(EShape eshape, double x, double y)
	{
		MyShapes myShape = new MyShapes(eshape);
		v.add(myShape);
		paneDessin.getChildren().add(myShape);	
		myShape.setX(x);
		myShape.setY(y);
		paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setLayoutX(myShape.getX());
		paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setLayoutY(myShape.getY());
		paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setOnMouseReleased(new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent event) 
			{
				move(event);	
				event.consume();
			}
		});
	}
	
}