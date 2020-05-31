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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javafx.scene.input.MouseEvent;

//This class makes part of the design pattern MVC togheter with class MyShapes and scene.fxml
public class FactoryController {
	
	
	Vector<MyShapes> v;
	
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
    void initialize() {

    	v = new Vector<MyShapes>();  	
    	
    	//Energy source
    	MyShapes energySource = new MyShapes(EShape.EnergySource);
    	gridPane1.add(energySource, 0, 0);
	
    	energySource.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
		    	addShape(EShape.EnergySource);
			}   		
    	});
    	
    	
    	//Mono-physical converter
    	MyShapes monoPhysicalConverter = new MyShapes(EShape.MonoPhysicalConverter);
    	gridPane1.add(monoPhysicalConverter, 1, 0);
    	
    	monoPhysicalConverter.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
				addShape(EShape.MonoPhysicalConverter);
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
			}   		
    	});
    	
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
			}
		}
	}
	
	public void addShape(EShape eshape)
	{
		MyShapes myShape = new MyShapes(eshape);
		v.add(myShape);
		paneDessin.getChildren().add(myShape);	
				    	
		paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setOnMouseReleased(new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent event) 
			{
				move(event);					
			}
		});
	}
	
}