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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

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
    	gridPane1.add(energySource.getShape(), 0, 0);
	
    	energySource.getShape().setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
		    	MyShapes energySource = new MyShapes(EShape.EnergySource);
		    	v.add(energySource);
		    	paneDessin.getChildren().add(energySource.getShape());	
		    			    	
		    	paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setOnMouseReleased(new EventHandler<MouseEvent>() 
		    	{
					@Override
					public void handle(MouseEvent event) 
					{
						move(event);					
					}
		    	});
			}   		
    	});
    	
    	
    	//Mono-physical converter
    	MyShapes monoPhysicalConverter = new MyShapes(EShape.MonoPhysicalConverter);
    	gridPane1.add(monoPhysicalConverter.getShape(), 1, 0);
    	
    	monoPhysicalConverter.getShape().setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
				MyShapes monoPhysicalConverter = new MyShapes(EShape.MonoPhysicalConverter);
				v.add(monoPhysicalConverter);
		    	paneDessin.getChildren().add(monoPhysicalConverter.getShape());	
		    	
		    	paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setOnMouseReleased(new EventHandler<MouseEvent>() 
		    	{
					@Override
					public void handle(MouseEvent event) 
					{
						move(event);
					}
		    	});
			}   		
    	});
    	
    	//Multi-physical converter
    	MyShapes multiPhysicalConverter = new MyShapes(EShape.MultiPhysicalConverter);
    	gridPane1.add(multiPhysicalConverter.getShape(), 0, 1);
    	
    	multiPhysicalConverter.getShape().setOnMouseClicked(new EventHandler<MouseEvent>(){

    			@Override
    			public void handle(MouseEvent event)
    			{				
    				MyShapes multiPhysicalConverter = new MyShapes(EShape.MultiPhysicalConverter);
    				v.add(multiPhysicalConverter);
    		    	paneDessin.getChildren().add(multiPhysicalConverter.getShape());	
    		    		    			    	    	    	
    		    	paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setOnMouseReleased(new EventHandler<MouseEvent>() 
    		    	{
    					@Override
    					public void handle(MouseEvent event) 
    					{
    						move(event);
    					}
    		    	});
    			}   		
        	});    	
    	
    	//Energy accumulation
    	MyShapes energyAccumulation = new MyShapes(EShape.EnergyAccumulation);
    	gridPane1.add(energyAccumulation.getShape(), 1, 1);
    	
    	energyAccumulation.getShape().setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
				MyShapes energyAccumulation = new MyShapes(EShape.EnergyAccumulation);
				v.add(energyAccumulation);
		    	paneDessin.getChildren().add(energyAccumulation.getShape());	
		    	
		    	paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setOnMouseReleased(new EventHandler<MouseEvent>() 
		    	{
					@Override
					public void handle(MouseEvent event) 
					{
						move(event);
					}
		    	});
			}   		
    	});
    	
    	//Mono-physical coupling
    	MyShapes monoPhysicalCoupling = new MyShapes(EShape.MonoPhysicalCoupling);
    	gridPane1.add(monoPhysicalCoupling.getShape(), 0, 2);
    	
    	monoPhysicalCoupling.getShape().setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{		
				MyShapes monoPhysicalCoupling = new MyShapes(EShape.MonoPhysicalCoupling);
				v.add(monoPhysicalCoupling);
		    	paneDessin.getChildren().add(monoPhysicalCoupling.getShape());	
		
		    	paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setOnMouseReleased(new EventHandler<MouseEvent>() 
		    	{
					@Override
					public void handle(MouseEvent event) 
					{
						move(event);
					}
		    	});
			}   		
    	});
    	
    	//Multi-physical coupling
    	MyShapes multiPhysicalCoupling = new MyShapes(EShape.MultiPhysicalCoupling);
    	gridPane1.add(multiPhysicalCoupling.getShape(), 1, 2);
    	
    	multiPhysicalCoupling.getShape().setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
				MyShapes multiPhysicalCoupling = new MyShapes(EShape.MultiPhysicalCoupling);
				v.add(multiPhysicalCoupling);
		    	paneDessin.getChildren().add(multiPhysicalCoupling.getShape());	
	    	
		    	paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setOnMouseReleased(new EventHandler<MouseEvent>() 
		    	{
					@Override
					public void handle(MouseEvent event) 
					{
						move(event);
					}
		    	});
			}   		
    	});
    	
    	//Direct inversion
    	MyShapes directInversion = new MyShapes(EShape.DirectInversion);
    	gridPane2.add(directInversion.getShape(), 0, 0);
    	
    	directInversion.getShape().setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
				MyShapes directInversion = new MyShapes(EShape.DirectInversion);
				v.add(directInversion);
		    	paneDessin.getChildren().add(directInversion.getShape());	

		    	paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setOnMouseReleased(new EventHandler<MouseEvent>() 
		    	{
					@Override
					public void handle(MouseEvent event) 
					{
						move(event);
					}
		    	});
			}   		
    	});
    	
    	
    	//Indirect inversion
    	MyShapes indirectInversion = new MyShapes(EShape.IndirectInversion);
    	gridPane2.add(indirectInversion.getShape(), 1, 0);
    	
    	indirectInversion.getShape().setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
				MyShapes indirectInversion = new MyShapes(EShape.IndirectInversion);
				v.add(indirectInversion);
		    	paneDessin.getChildren().add(indirectInversion.getShape());	
	    	
		    	paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setOnMouseReleased(new EventHandler<MouseEvent>() 
		    	{
					@Override
					public void handle(MouseEvent event) 
					{
						move(event);
					}
		    	});
			}   		
    	});
    	
    	//Strategy
    	MyShapes strategy = new MyShapes(EShape.Strategy);
    	gridPane3.add(strategy.getShape(), 0, 0);
    	
    	strategy.getShape().setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
				MyShapes strategy = new MyShapes(EShape.Strategy);
				v.add(strategy);
		    	paneDessin.getChildren().add(strategy.getShape());	
	    	
		    	paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setOnMouseReleased(new EventHandler<MouseEvent>() 
		    	{
					@Override
					public void handle(MouseEvent event) 
					{
						move(event);
					}
		    	});
			}   		
    	});
    	
    	//Energy source estimator
    	MyShapes energySourceEstimator = new MyShapes(EShape.EnergySourceEstimator);
    	gridPane4.add(energySourceEstimator.getShape(),0,0);
    	
    	energySourceEstimator.getShape().setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
				MyShapes energySourceEstimator = new MyShapes(EShape.EnergySourceEstimator);
				v.add(energySourceEstimator);
		    	paneDessin.getChildren().add(energySourceEstimator.getShape());	
	    	
		    	paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setOnMouseReleased(new EventHandler<MouseEvent>() 
		    	{
					@Override
					public void handle(MouseEvent event) 
					{
						move(event);
					}
		    	});
			}   		
    	});
    	
    	//Mono-physical converter estimator
    	MyShapes monoPhysicalConverterEstimator = new MyShapes(EShape.MonoPhysicalConverterEstimator);
    	gridPane4.add(monoPhysicalConverterEstimator.getShape(), 1, 0);
    	
    	monoPhysicalConverterEstimator.getShape().setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
				MyShapes monoPhysicalConverterEstimator = new MyShapes(EShape.MonoPhysicalConverterEstimator);
				v.add(monoPhysicalConverterEstimator);
		    	paneDessin.getChildren().add(monoPhysicalConverterEstimator.getShape());	

		    	paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setOnMouseReleased(new EventHandler<MouseEvent>() 
		    	{
					@Override
					public void handle(MouseEvent event) 
					{
						move(event);
					}
		    	});
			}   		
    	});    	
    	
    	//Multi-physical converter estimator
    	MyShapes multiPhysicalConverterEstimator = new MyShapes(EShape.MultiPhysicalConverterEstimator);
    	gridPane4.add(multiPhysicalConverterEstimator.getShape(), 0, 1);
    	
    	multiPhysicalConverterEstimator.getShape().setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
				MyShapes multiPhysicalConverterEstimator = new MyShapes(EShape.MultiPhysicalConverterEstimator);
				v.add(multiPhysicalConverterEstimator);
		    	paneDessin.getChildren().add(multiPhysicalConverterEstimator.getShape());	
	    	
		    	paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setOnMouseReleased(new EventHandler<MouseEvent>() 
		    	{
					@Override
					public void handle(MouseEvent event) 
					{
						move(event);
					}
		    	});
			}   		
    	});
    	
    	//Energy accumulation estimator
    	MyShapes energyAccumulationEstimator = new MyShapes(EShape.EnergyAccumulationEstimator);
    	gridPane4.add(energyAccumulationEstimator.getShape(), 1, 1);
    	
    	energyAccumulationEstimator.getShape().setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
				MyShapes energyAccumulationEstimator = new MyShapes(EShape.EnergyAccumulationEstimator);
				v.add(energyAccumulationEstimator);
		    	paneDessin.getChildren().add(energyAccumulationEstimator.getShape());	
	    	
		    	paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setOnMouseReleased(new EventHandler<MouseEvent>() 
		    	{
					@Override
					public void handle(MouseEvent event) 
					{
						move(event);
					}
		    	});
			}   		
    	});
    	 
    	//Mono-physical coupling estimator
    	MyShapes monoPhysicalCouplingEstimator = new MyShapes(EShape.MonoPhysicalCouplingEstimator);
    	gridPane4.add(monoPhysicalCouplingEstimator.getShape(), 0, 2);
    	
    	monoPhysicalCouplingEstimator.getShape().setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
				MyShapes monoPhysicalCouplingEstimator = new MyShapes(EShape.MonoPhysicalCouplingEstimator);
				v.add(monoPhysicalCouplingEstimator);
		    	paneDessin.getChildren().add(monoPhysicalCouplingEstimator.getShape());	
	    	
		    	paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setOnMouseReleased(new EventHandler<MouseEvent>() 
		    	{
					@Override
					public void handle(MouseEvent event) 
					{
						move(event);
					}
		    	});
			}   		
    	});    	
    	
    	//Multi-physical coupling estimator
    	MyShapes multiPhysicalCouplingEstimator = new MyShapes(EShape.MultiPhysicalCouplingEstimator);
    	gridPane4.add(multiPhysicalCouplingEstimator.getShape(), 1, 2);
    	
    	multiPhysicalCouplingEstimator.getShape().setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event)
			{				
				MyShapes multiPhysicalCouplingEstimator = new MyShapes(EShape.MultiPhysicalCouplingEstimator);
				v.add(multiPhysicalCouplingEstimator);
		    	paneDessin.getChildren().add(multiPhysicalCouplingEstimator.getShape());
	    	
		    	paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setOnMouseReleased(new EventHandler<MouseEvent>() 
		    	{
		    		@Override
					public void handle(MouseEvent event)
		    		{
						move(event);
					}
		    	});
			}   		
    	});
    	
    }
    
	public void move(MouseEvent event) 
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
}


    


