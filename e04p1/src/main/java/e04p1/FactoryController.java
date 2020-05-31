package e04p1;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import e04p1.MyShapes.EShape;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
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
import javafx.stage.FileChooser;
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
    void Save(ActionEvent event)
    {
    	labelStatusBar.setText("Saving...");
    	FileChooser fileChooser = new FileChooser();

        //Set extension filter
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));

        //Prompt user to select a file
        File file = fileChooser.showSaveDialog(null);

        if(file != null){
            try {                
                WritableImage writableImage = new WritableImage((int)paneDessin.getWidth() , (int)paneDessin.getHeight());
                Image canvas = paneDessin.snapshot(null, writableImage);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(canvas, null);
                ImageIO.write(renderedImage, "png", file);
                labelStatusBar.setText("Saved");
            } catch (IOException ex) { ex.printStackTrace(); }
        }
    }
    
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
    	Vindex = 0;
    	v = new Vector<MyShapes>();  
    	myBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2.5)));
    	
    	paneDessin.setBorder(myBorder);
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