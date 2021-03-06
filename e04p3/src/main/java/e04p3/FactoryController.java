package e04p3;

import java.util.Vector;
import e04p3.MyShapes.EShape;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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

//This class makes part of the design pattern COMMAND with class FactoryController, Invoker, Command,
//AddShapeCommand, AddArrowCommand and MoveShape Command;

//This class makes part of the design pattern MVC together with class MyShapes and scene.fxml
public class FactoryController {
	
	Invoker invoker;
	
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
    private MenuItem menuUndo;
    
    @FXML
    private MenuItem menuRedo;
    
    
   
      
    
    public EShape getDraggedShape() {
		return draggedShape;
	}
	public void setDraggedShape(EShape draggedShape) {
		this.draggedShape = draggedShape;
	}
	public int getDraggedIndex() {
		return draggedIndex;
	}
	public void setDraggedIndex(int draggedIndex) {
		this.draggedIndex = draggedIndex;
	}
	public Pane getPaneDessin() {
		return paneDessin;
	}
	public void setPaneDessin(Pane paneDessin) {
		this.paneDessin = paneDessin;
	}
	public Vector<MyShapes> getV() {
		return v;
	}
	public void setV(Vector<MyShapes> v) {
		this.v = v;
	}
	
	@FXML
    void menuUndoClicked(ActionEvent event) {
		invoker.undo();
		
	}
	
	@FXML
    void menuRedoClicked(ActionEvent event) {
		invoker.redo();
		
	}
	
	
	@FXML
	// BOUTON POUR CHANGER DE MODE
    void buttonArrowsClicked(ActionEvent event) {
    	labelStatusBar.setText("Mode arrows");
    	
    	if(this.mode == 0)
    	{
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
	    					addArrow(arrowBeginX,arrowBeginY,event.getX(),event.getY());
	    					arrowBeginX = -1;
	    					arrowBeginY = -1;
	    				}	
                    }  		
        	});
    	}
    	
    	else if(this.mode == 1)
    	{
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
    void initialize() {
    	invoker = new Invoker(this);
    	
    	Vindex = 0;
    	v = new Vector<MyShapes>();  
    	myBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2.5)));
    	paneDessin.setBorder(myBorder);
    	
    	// DRAG AND DROP
    	
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
				if(draggedIndex == -1) addShape(draggedShape, event.getSceneX() - 166, event.getSceneY() - 50);
				else moveShape(draggedShape, event.getSceneX() - 166, event.getSceneY() - 50);
                event.consume();
                }  		
    	});
    	
    	
    	// AJOUR DES COMPOSANTS DANS LA PALETTE

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
				draggedIndex =-1;
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
				draggedIndex =-1;
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
				draggedIndex =-1;
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
				draggedIndex =-1;
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
				draggedIndex =-1;
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
				draggedIndex =-1;
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
				draggedIndex =-1;
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
				draggedIndex =-1;
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
				draggedIndex =-1;
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
				draggedIndex =-1;
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
				draggedIndex =-1;
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
				draggedIndex =-1;
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
				draggedIndex =-1;
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
				draggedIndex =-1;
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
				draggedIndex =-1;
			}    		
    	}); 
    }
   	
    // Ajout d'une forme
	public void addShape(EShape eshape, double x, double y)
	{
		Command co = new AddShapeCommand(this,eshape,x,y);
		invoker.addCommand(co);
		co.execute();

	}
	
	// D�placement d'une forme
	public void moveShape(EShape eshape, double x, double y)
	{
		Command co = new MoveShapeCommand(this,eshape,x,y);
		invoker.addCommand(co);
		co.execute();

	}
	
	//Ajout d'une fl�che
	public void addArrow(double x1, double y1, double x2, double y2)
	{
		Command co = new AddArrowCommand(this,EShape.Arrow,x1,y1,x2,y2);
		invoker.addCommand(co);
		co.execute();

	}
	
}