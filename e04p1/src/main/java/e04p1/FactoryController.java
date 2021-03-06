package e04p1;

import java.util.Vector;
import e04p1.MyShapes.EShape;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
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
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

//This class makes part of the design pattern MVC together with class MyShapes and scene.fxml
public class FactoryController {
	
	Vector<MyShapes> v;
	
	EShape draggedShape;
	
	int draggedIndex;
	
	int deleteIndex;
	
	int Vindex;
	
	Border myBorder;
	
	int mode = 0; // 0 = dessin de composants, 1 = dessin de fl�ches
	double arrowBeginX = -1;
	double arrowBeginY = -1;
	
	
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
    private Button Save;
    
    @FXML  
    private Button SaveFXML;
    
    @FXML
    private Button buttonArrows;
    
    @FXML
    // BOUTTON POUR CHANGER DE MODE 
    void buttonArrowsClicked(ActionEvent event) {
    	labelStatusBar.setText("Mode arrows");
    	
    	if(this.mode == 0)
    	{
    		// SET MODE ARROWS
    		mode = 1;
    		titledPane1.setExpanded(false);
        	titledPane2.setExpanded(false);
        	titledPane3.setExpanded(false);
        	titledPane4.setExpanded(false);
        	titledPane1.setCollapsible(false);
        	titledPane2.setCollapsible(false);
        	titledPane3.setCollapsible(false);
        	titledPane4.setCollapsible(false);
        	buttonArrows.setStyle("-fx-background-color: green;");
        	
        	paneDessin.setOnMouseClicked(new EventHandler<MouseEvent>() {
    			@Override
    			public void handle(MouseEvent event) {
	    				if(arrowBeginX == -1)
	    				{
	    					arrowBeginX = event.getX();
	    					arrowBeginY = event.getY();
	    				}
	    				else
	    				{
	    					Shape line = new Line(arrowBeginX,arrowBeginY,event.getX(),event.getY());
	    					double width = event.getX()-arrowBeginX;
	    					double height = arrowBeginY-event.getY();
	    					double angle = Math.toDegrees(Math.atan(height/width));
	    					if(width < 0) angle += 180;
	    					if(angle < 0) angle += 360;
	    					Shape line2 = new Line(event.getX(),event.getY(),event.getX()-5,event.getY()-5);
	    					line2.getTransforms().add(new Rotate(-angle, event.getX(), event.getY()) );
	    					Shape line3 = new Line(event.getX(),event.getY(),event.getX()-5,event.getY()+5);
	    					line3.getTransforms().add(new Rotate(-angle, event.getX(), event.getY()) );
	    					Group arrow = new Group(line,line2,line3) ;
	    					paneDessin.getChildren().add(arrow);
	    					arrowBeginX = -1;
	    					arrowBeginY = -1;
	    				}	
                    }  		
        	});
    	}
    	
    	else if(this.mode == 1)
    	{
    		//SET MODE DESSIN
    		mode = 0;
    		titledPane1.setCollapsible(true);
        	titledPane2.setCollapsible(true);
        	titledPane3.setCollapsible(true);
        	titledPane4.setCollapsible(true);
        	buttonArrows.setStyle(null);
        	paneDessin.setOnMouseClicked(null);
    	}
    	
    }
    @FXML
    void menuCloseClicked(ActionEvent event) {
    	labelStatusBar.setText("Fermeture de l'application...");
    }

    @FXML
    void menuDeleteClicked(ActionEvent event) {
    	labelStatusBar.setText("Supression de l'element...");
    }
    
    @FXML
    void buttonAddClicked(ActionEvent event) {
    	labelStatusBar.setText("Ajout d'un element...");
    }

    @FXML
    void buttonFullScreenClicked(ActionEvent event) {
    	labelStatusBar.setText("Activation du mode plein ecran...");
    }

    @FXML
    // Initialisation de l'interface
    void initialize() {
    	Vindex = 0;
    	v = new Vector<MyShapes>();  
    	myBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2.5)));
    	paneDessin.setBorder(myBorder);
    	
    	//DRAG AND DROP
    	
    	paneDessin.setOnDragEntered(new EventHandler<DragEvent>() {
			@Override	
			public void handle(DragEvent event) {
				event.acceptTransferModes(TransferMode.ANY);
		    	paneDessin.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2.5))));
			}	
    	});
    	paneDessin.setOnDragExited(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				paneDessin.setBorder(myBorder);
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
    	
    	
    	// AJOUT DES COMPOSANTS DANS LA PALETTE

    	//Energy source
    	MyShapes energySource = new MyShapes(EShape.EnergySource);
    	gridPane1.add(energySource, 0, 0);
    	    	
    	energySource.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {				
				Dragboard db = energySource.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
		        content.putString("Dragged");
				db.setContent(content);
				draggedShape = EShape.EnergySource;		
			}    		
    	});
 	
    	//Mono-physical converter
    	MyShapes monoPhysicalConverter = new MyShapes(EShape.MonoPhysicalConverter);
    	gridPane1.add(monoPhysicalConverter, 1, 0);
    	
    	monoPhysicalConverter.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {				
				Dragboard db = monoPhysicalConverter.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
		        content.putString("Dragged");
				db.setContent(content);
				draggedShape = EShape.MonoPhysicalConverter;		
			}    		
    	});
    	
    	//Multi-physical converter
    	MyShapes multiPhysicalConverter = new MyShapes(EShape.MultiPhysicalConverter);
    	gridPane1.add(multiPhysicalConverter, 0, 1);
    	
    	multiPhysicalConverter.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {				
				Dragboard db = multiPhysicalConverter.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
		        content.putString("Dragged");
				db.setContent(content);
				draggedShape = EShape.MultiPhysicalConverter;		
			}    		
    	});  	
    	
    	//Energy accumulation
    	MyShapes energyAccumulation = new MyShapes(EShape.EnergyAccumulation);
    	gridPane1.add(energyAccumulation, 1, 1);
    	
    	energyAccumulation.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {				
				Dragboard db = energyAccumulation.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
		        content.putString("Dragged");
				db.setContent(content);
				draggedShape = EShape.EnergyAccumulation;		
			}    		
    	}); 
    	
    	//Mono-physical coupling
    	MyShapes monoPhysicalCoupling = new MyShapes(EShape.MonoPhysicalCoupling);
    	gridPane1.add(monoPhysicalCoupling, 0, 2);
    	
    	monoPhysicalCoupling.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {				
				Dragboard db = monoPhysicalCoupling.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
		        content.putString("Dragged");
				db.setContent(content);
				draggedShape = EShape.MonoPhysicalCoupling;		
			}    		
    	}); 
    	
    	//Multi-physical coupling
    	MyShapes multiPhysicalCoupling = new MyShapes(EShape.MultiPhysicalCoupling);
    	gridPane1.add(multiPhysicalCoupling, 1, 2);
    	
    	multiPhysicalCoupling.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {				
				Dragboard db = multiPhysicalCoupling.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
		        content.putString("Dragged");
				db.setContent(content);
				draggedShape = EShape.MultiPhysicalCoupling;		
			}    		
    	}); 
    	
    	//Direct inversion
    	MyShapes directInversion = new MyShapes(EShape.DirectInversion);
    	gridPane2.add(directInversion, 0, 0);
    	
    	directInversion.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {				
				Dragboard db = directInversion.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
		        content.putString("Dragged");
				db.setContent(content);
				draggedShape = EShape.DirectInversion;		
			}    		
    	}); 
    	
    	
    	//Indirect inversion
    	MyShapes indirectInversion = new MyShapes(EShape.IndirectInversion);
    	gridPane2.add(indirectInversion, 1, 0);
    	
    	indirectInversion.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {				
				Dragboard db = indirectInversion.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
		        content.putString("Dragged");
				db.setContent(content);
				draggedShape = EShape.IndirectInversion;		
			}    		
    	}); 
    	
    	
    	//Strategy
    	MyShapes strategy = new MyShapes(EShape.Strategy);
    	gridPane3.add(strategy, 0, 0);
    	
    	strategy.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {				
				Dragboard db = strategy.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
		        content.putString("Dragged");
				db.setContent(content);
				draggedShape = EShape.Strategy;		
			}    		
    	}); 
    	
    	
    	//Energy source estimator
    	MyShapes energySourceEstimator = new MyShapes(EShape.EnergySourceEstimator);
    	gridPane4.add(energySourceEstimator,0,0);
    	
    	energySourceEstimator.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {				
				Dragboard db = energySourceEstimator.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
		        content.putString("Dragged");
				db.setContent(content);
				draggedShape = EShape.EnergySourceEstimator;		
			}    		
    	}); 
    	
    	//Mono-physical converter estimator
    	MyShapes monoPhysicalConverterEstimator = new MyShapes(EShape.MonoPhysicalConverterEstimator);
    	gridPane4.add(monoPhysicalConverterEstimator, 1, 0);
    	
    	monoPhysicalConverterEstimator.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {				
				Dragboard db = monoPhysicalConverterEstimator.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
		        content.putString("Dragged");
				db.setContent(content);
				draggedShape = EShape.MonoPhysicalConverterEstimator;		
			}    		
    	});  	
    	
    	//Multi-physical converter estimator
    	MyShapes multiPhysicalConverterEstimator = new MyShapes(EShape.MultiPhysicalConverterEstimator);
    	gridPane4.add(multiPhysicalConverterEstimator, 0, 1);
    	
    	multiPhysicalConverterEstimator.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {				
				Dragboard db = multiPhysicalConverterEstimator.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
		        content.putString("Dragged");
				db.setContent(content);
				draggedShape = EShape.MultiPhysicalConverterEstimator;		
			}    		
    	}); 
    	
    	//Energy accumulation estimator
    	MyShapes energyAccumulationEstimator = new MyShapes(EShape.EnergyAccumulationEstimator);
    	gridPane4.add(energyAccumulationEstimator, 1, 1);
    	
    	energyAccumulationEstimator.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {				
				Dragboard db = energyAccumulationEstimator.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
		        content.putString("Dragged");
				db.setContent(content);
				draggedShape = EShape.EnergyAccumulationEstimator;		
			}    		
    	}); 
    	 
    	//Mono-physical coupling estimator
    	MyShapes monoPhysicalCouplingEstimator = new MyShapes(EShape.MonoPhysicalCouplingEstimator);
    	gridPane4.add(monoPhysicalCouplingEstimator, 0, 2);
    	
    	monoPhysicalCouplingEstimator.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {				
				Dragboard db = monoPhysicalCouplingEstimator.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
		        content.putString("Dragged");
				db.setContent(content);
				draggedShape = EShape.MonoPhysicalCouplingEstimator;		
			}    		
    	}); 
    	   	
    	
    	//Multi-physical coupling estimator
    	MyShapes multiPhysicalCouplingEstimator = new MyShapes(EShape.MultiPhysicalCouplingEstimator);
    	gridPane4.add(multiPhysicalCouplingEstimator, 1, 2);
    	
    	multiPhysicalCouplingEstimator.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {				
				Dragboard db = multiPhysicalCouplingEstimator.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
		        content.putString("Dragged");
				db.setContent(content);
				draggedShape = EShape.MultiPhysicalCouplingEstimator;		
			}    		
    	}); 
    }
   	
    // M�thode pour ajouter un element
	public void addShape(EShape eshape, double x, double y)
	{
		MyShapes myShape = new MyShapes(eshape);
		v.add(myShape);
		paneDessin.getChildren().add(myShape);	
		paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setLayoutX(x);
		paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setLayoutY(y);
		//myShape.setIndex(Vindex);
		paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setOnDragDetected(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {				
				Dragboard db = paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
		        content.putString("Dragged");
				db.setContent(content);
				draggedShape = eshape;		
				for(int i = 0; i < paneDessin.getChildren().size(); i++)
				{
					if(paneDessin.getChildren().get(i).equals(((Node) event.getSource())))
					{
						draggedIndex = i;
						break;
					}
				}
			}
			
		});
		paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setOnDragDone(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				//v.remove(((MyShapes) paneDessin.getChildren().get(draggedIndex)).getIndex());
				paneDessin.getChildren().remove(draggedIndex);					
			}
			
		});
		
	}
	
}