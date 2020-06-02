package e04p4;

import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import e04p4.MyShapes.EShape;
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
	int deleteID;
	int Vid = 0;
	
	Border myBorder;
	
	private State state;
	
	int lastClickedIndex = -1;
	private EShape arrowStyle = null;
	
	
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
    private Button buttonArrows;
    
    @FXML
    private MenuItem doubleArrow;
    
    @FXML
    private MenuItem simpleArrow;
  
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	
	public TitledPane getTitledPane1() {
		return titledPane1;
	}
	public void setTitledPane1(TitledPane titledPane1) {
		this.titledPane1 = titledPane1;
	}
	public TitledPane getTitledPane2() {
		return titledPane2;
	}
	public void setTitledPane2(TitledPane titledPane2) {
		this.titledPane2 = titledPane2;
	}
	public TitledPane getTitledPane3() {
		return titledPane3;
	}
	public void setTitledPane3(TitledPane titledPane3) {
		this.titledPane3 = titledPane3;
	}
	public TitledPane getTitledPane4() {
		return titledPane4;
	}
	public void setTitledPane4(TitledPane titledPane4) {
		this.titledPane4 = titledPane4;
	}
	public Pane getPaneDessin() {
		return paneDessin;
	}
	public void setPaneDessin(Pane paneDessin) {
		this.paneDessin = paneDessin;
	}
	public Button getButtonArrows() {
		return buttonArrows;
	}
	public void setButtonArrows(Button buttonArrows) {
		this.buttonArrows = buttonArrows;
	}
	
	public void setStatusBarMessage(String text) {
		this.labelStatusBar.setText(text);
	}
	public Vector<MyShapes> getV() {
		return v;
	}
	public void setV(Vector<MyShapes> v) {
		this.v = v;
	}
	public EShape getArrowStyle() {
		return arrowStyle;
	}
	public void setArrowStyle(EShape arrowStyle) {
		this.arrowStyle = arrowStyle;
	}
	
	@FXML
    void buttonArrowsClicked(ActionEvent event) {
		state.changeState(this);
		labelStatusBar.setText(state.toString());
		event.consume();
	}
	
	@FXML
    void simpleArrowClicked(ActionEvent event) {
		state.simpleRedArrow(this);
		event.consume();
	}
	
	@FXML
    void doubleArrowClicked(ActionEvent event) {
		state.doubleBlackArrow(this);
		event.consume();
	}

    	
    
    @FXML
    void menuCloseClicked(ActionEvent event) {
    	labelStatusBar.setText("Fermeture de l'application...");
    	event.consume();
    }

    @FXML
    void menuDeleteClicked(ActionEvent event) {
    	labelStatusBar.setText("Supression de l'element...");
    	event.consume();
    }
    
    @FXML
    void buttonAddClicked(ActionEvent event) {
    	labelStatusBar.setText("Ajout d'un element...");
    	event.consume();
    }

    @FXML
    void buttonFullScreenClicked(ActionEvent event) {
    	labelStatusBar.setText("Activation du mode plein ecran...");
    	event.consume();
    }
    @FXML
    void Clear(ActionEvent event) {
        v.removeAllElements();
        paneDessin.getChildren().clear();
        event.consume();
    }
    @FXML
    void OpenFXML(ActionEvent event) {
    	labelStatusBar.setText("Opening From xml...");
    	FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xml files (*.xml)", "*.xml"));
        fileChooser.setInitialFileName("myCanvas.txt");
        File file = fileChooser.showOpenDialog(null);

        v.removeAllElements();
        paneDessin.getChildren().clear();
        
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            double x=0;
            double y=0;
            EShape tempShape = null;
            while ((line = reader.readLine()) != null) {
	        	 line.trim();
            	 String[] splited = line.substring(0, line.length()-1).split(" ");
            	 if(splited[0].contentEquals("<MyShapes"))
            	 {
            		 for(int i =1 ; i< splited.length ; i++)
                	 {
            			 String[] splited2 = splited[i].split("=");
            			 
            			 switch (splited2[0])
            			 {
	            			 case "eshape":
	            				 tempShape = EShape.valueOf(splited2[1].replaceAll("\"", ""));
	            				 break;
	            			 case "x":
	            				 x = Double.parseDouble(splited2[1].replaceAll("\"", ""));
	            				 break;
	            			 case "y":
	            				 y = Double.parseDouble(splited2[1].replaceAll("\"", ""));
	            				 break;
	            			 case "index1":
	            				 x =(int) Double.parseDouble(splited2[1].replaceAll("\"", ""));
	            				 break;
	            			 case "index2":
	            				 y =(int) Double.parseDouble(splited2[1].replaceAll("\"", ""));
	            				 break;
            			 }
            			 
                	 }
            		 if(tempShape == EShape.SimpleArrow || tempShape == EShape.DoubleArrow) {
            			int lastClickedIndex = ((int)x);
      					int clickedIndex = ((int)y);
      					
      				// TYPES DE FORMES
     					EShape eshape1 = ((MyShapes) paneDessin.getChildren().get(lastClickedIndex)).getMyEShape();
     					EShape eshape2 = ((MyShapes) paneDessin.getChildren().get(clickedIndex)).getMyEShape();
     					
     					// AJUSTEMENTS POUR LES CARR�S
     					double ajustX1 = paneDessin.getChildren().get(lastClickedIndex).getLayoutBounds().getWidth();
     					double ajustY1 = paneDessin.getChildren().get(lastClickedIndex).getLayoutBounds().getHeight()/2;
     					double ajustX2 = 0;
     					double ajustY2 = paneDessin.getChildren().get(clickedIndex).getLayoutBounds().getHeight()/2;
     					if(paneDessin.getChildren().get(lastClickedIndex).getLayoutX() >
     						paneDessin.getChildren().get(clickedIndex).getLayoutX())
     					{
     						ajustX1 =0;
     						ajustX2 = paneDessin.getChildren().get(clickedIndex).getLayoutBounds().getWidth();
     					}
     					
     					// AJUSTEMENTS SI LA FORME DE D�PART EST UN ROND/OVALE 
     					if(eshape1 == EShape.EnergySource || eshape1 == EShape.MultiPhysicalConverter ||
     							eshape1 == EShape.EnergySourceEstimator || eshape1 == EShape.MultiPhysicalConverterEstimator)
     					{
     						ajustX1 /= 2;
     						ajustY1 = 0;
     						if(paneDessin.getChildren().get(lastClickedIndex).getLayoutX() >
     						paneDessin.getChildren().get(clickedIndex).getLayoutX())
         					{
         						ajustX1 = -paneDessin.getChildren().get(lastClickedIndex).getLayoutBounds().getWidth() /2;
         					}
     					}
     					
     					// AJUSTEMENTS SI LA FORME D'ARRIV�E EST UN ROND/OVALE 
     					if(eshape2 == EShape.EnergySource || eshape2 == EShape.MultiPhysicalConverter ||
     							eshape2 == EShape.EnergySourceEstimator || eshape2 == EShape.MultiPhysicalConverterEstimator)
     					{
     						ajustX2 = -paneDessin.getChildren().get(clickedIndex).getLayoutBounds().getWidth() /2;
     						ajustY2 = 0;
     						if(paneDessin.getChildren().get(lastClickedIndex).getLayoutX() >
     						paneDessin.getChildren().get(clickedIndex).getLayoutX())
         					{
         						ajustX2 = paneDessin.getChildren().get(clickedIndex).getLayoutBounds().getWidth() /2;
         					}
     					}
     				
      					MyShapes arrow = new MyArrow(tempShape,
      							v.get((int) x).getX()+ajustX1,
      							v.get((int) x).getY()+ajustY1,
      							v.get((int) y).getX()+ajustX2,
      							v.get((int) y).getY()+ajustY2,
      							(int)x, (int)y);
     					paneDessin.getChildren().add(arrow);
     					v.add(arrow);
      				}else	addShape(tempShape, x, y);
            		 labelStatusBar.setText("Opened");
                  }
            }
             } catch (IOException e) {
                 e.printStackTrace();
             }		
        
    }
    
    @FXML
    void SaveFXML(ActionEvent event) {
    	labelStatusBar.setText("Saving to xml...");
    	FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xml files (*.xml)", "*.xml"));
        fileChooser.setInitialFileName("myCanvas.xml");
        
        EShape shapeWrite;
		try{
			FileWriter file = new FileWriter(fileChooser.showSaveDialog(null));
			for(int i = 0; i < v.size(); i++)
			{
				if(v.elementAt(i).getMyEShape() == EShape.SimpleArrow || v.elementAt(i).getMyEShape() == EShape.DoubleArrow)
				{
					file.write("<MyShapes index1=" + (char)'"');
					file.write((int) ((MyArrow) v.elementAt(i)).getStartShapeIndex() + "");
					file.write((char)'"' + " index2=" + (char)'"');
					file.write((int) ((MyArrow) v.elementAt(i)).getEndShapeIndex() +"");
				} else {
					file.write("<MyShapes x=" + (char)'"');
					file.write((int) v.elementAt(i).getX() + "");
					file.write((char)'"' + " y=" + (char)'"');
					file.write((int) v.elementAt(i).getY() + "");
				}
				shapeWrite = v.elementAt(i).getMyEShape();
				file.write((char)'"' + " eshape=" + (char)'"');
				file.write(shapeWrite.toString()+(char)'"' + ">\n");				
			}
			file.close();
            labelStatusBar.setText("Saved");
		} catch (IOException e){
			e.printStackTrace();               
			labelStatusBar.setText("Fichier Introuvable");
		}
		event.consume();
    }
      
    @FXML
    void Open(ActionEvent event) throws IOException {
    	labelStatusBar.setText("Opening from .txt...");
    	FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt"));
        File file = fileChooser.showOpenDialog(null);

        v.removeAllElements();
        paneDessin.getChildren().clear();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            double x;
            double y;
            EShape tempShape;
            while ((line = reader.readLine()) != null) {
	        	 line.trim();
            	 String[] splited = line.split(" ");
            	 x = Double.parseDouble(splited[0]);
            	 y = Double.parseDouble(splited[1]);
            	 tempShape = EShape.valueOf(splited[2]);
 				if(tempShape == EShape.SimpleArrow || tempShape == EShape.DoubleArrow) {
 					int lastClickedIndex = ((int)x);
 					int clickedIndex = ((int)y);
 				// TYPES DE FORMES
					EShape eshape1 = ((MyShapes) paneDessin.getChildren().get(lastClickedIndex)).getMyEShape();
					EShape eshape2 = ((MyShapes) paneDessin.getChildren().get(clickedIndex)).getMyEShape();
					
					// AJUSTEMENTS POUR LES CARR�S
					double ajustX1 = paneDessin.getChildren().get(lastClickedIndex).getLayoutBounds().getWidth();
					double ajustY1 = paneDessin.getChildren().get(lastClickedIndex).getLayoutBounds().getHeight()/2;
					double ajustX2 = 0;
					double ajustY2 = paneDessin.getChildren().get(clickedIndex).getLayoutBounds().getHeight()/2;
					if(paneDessin.getChildren().get(lastClickedIndex).getLayoutX() >
						paneDessin.getChildren().get(clickedIndex).getLayoutX())
					{
						ajustX1 =0;
						ajustX2 = paneDessin.getChildren().get(clickedIndex).getLayoutBounds().getWidth();
					}
					
					// AJUSTEMENTS SI LA FORME DE D�PART EST UN ROND/OVALE 
					if(eshape1 == EShape.EnergySource || eshape1 == EShape.MultiPhysicalConverter ||
							eshape1 == EShape.EnergySourceEstimator || eshape1 == EShape.MultiPhysicalConverterEstimator)
					{
						ajustX1 /= 2;
						ajustY1 = 0;
						if(paneDessin.getChildren().get(lastClickedIndex).getLayoutX() >
						paneDessin.getChildren().get(clickedIndex).getLayoutX())
    					{
    						ajustX1 = -paneDessin.getChildren().get(lastClickedIndex).getLayoutBounds().getWidth() /2;
    					}
					}
					
					// AJUSTEMENTS SI LA FORME D'ARRIV�E EST UN ROND/OVALE 
					if(eshape2 == EShape.EnergySource || eshape2 == EShape.MultiPhysicalConverter ||
							eshape2 == EShape.EnergySourceEstimator || eshape2 == EShape.MultiPhysicalConverterEstimator)
					{
						ajustX2 = -paneDessin.getChildren().get(clickedIndex).getLayoutBounds().getWidth() /2;
						ajustY2 = 0;
						if(paneDessin.getChildren().get(lastClickedIndex).getLayoutX() >
						paneDessin.getChildren().get(clickedIndex).getLayoutX())
    					{
    						ajustX2 = paneDessin.getChildren().get(clickedIndex).getLayoutBounds().getWidth() /2;
    					}
					}
				
 					MyShapes arrow = new MyArrow(tempShape,
 							v.get((int) x).getX()+ajustX1,
 							v.get((int) x).getY()+ajustY1,
 							v.get((int) y).getX()+ajustX2,
 							v.get((int) y).getY()+ajustY2,
 							(int)x, (int)y);
					paneDessin.getChildren().add(arrow);
					v.add(arrow);
 				}else	addShape(tempShape, x, y);
 				labelStatusBar.setText("Opened");
             }
        } catch (IOException e) {
            e.printStackTrace();
        }
        event.consume();
    }
    
    @FXML
    void Save(ActionEvent event)
    {
    	labelStatusBar.setText("Saving to .txt...");
    	FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt"));
        fileChooser.setInitialFileName("myCanvas.txt");

        EShape shapeWrite;
		try{
			FileWriter file = new FileWriter(fileChooser.showSaveDialog(null));
			for(int i = 0; i < v.size(); i++)
			{
				if(v.elementAt(i).getMyEShape() == EShape.SimpleArrow || v.elementAt(i).getMyEShape() == EShape.DoubleArrow)
				{
					file.write((int) ((MyArrow) v.elementAt(i)).getStartShapeIndex() + " ");
					file.write((int) ((MyArrow) v.elementAt(i)).getEndShapeIndex() + " ");
				} else {
					file.write((int) v.elementAt(i).getX() + " ");
					file.write((int) v.elementAt(i).getY()+ " ");
				}
				shapeWrite = v.elementAt(i).getMyEShape();
				file.write(shapeWrite.toString()+ "\n");				
			}
			file.close();
            labelStatusBar.setText("Saved");
		} catch (IOException e){
			e.printStackTrace();               
			labelStatusBar.setText("Fichier Introuvable");
		}
		event.consume();
    }
    @FXML
    void SaveImage(ActionEvent event)
    {
    	labelStatusBar.setText("Saving...");
    	FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));
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
        event.consume();
    }
    
    
    @FXML
    void initialize() {
    	// STATE PATTERN
    	state = new DrawState(this);
    	labelStatusBar.setText(state.toString());
    	
    	v = new Vector<MyShapes>();  
    	myBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2.5)));
    	
    	paneDessin.setBorder(myBorder);
    	paneDessin.setOnDragEntered(new EventHandler<DragEvent>() {
			@Override	
			public void handle(DragEvent event) {
				event.acceptTransferModes(TransferMode.ANY);
		    	paneDessin.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2.5))));
		    	event.consume();
			}	
    	});
    	paneDessin.setOnDragExited(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				paneDessin.setBorder(myBorder);
				event.consume();
			}
    		
    	});
    	paneDessin.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				event.acceptTransferModes(TransferMode.ANY);
				event.consume();
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
				event.consume();
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
				event.consume();
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
				event.consume();
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
				event.consume();
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
				event.consume();
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
				event.consume();
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
				event.consume();
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
				event.consume();
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
				event.consume();
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
				event.consume();
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
				event.consume();
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
				event.consume();
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
				event.consume();
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
				event.consume();
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
				event.consume();
			}    		
    	}); 
    }
   	
	public void addShape(EShape eshape, double x, double y)
	{
		MyShapes myShape = new MyShapes(eshape);
		v.add(myShape);
		paneDessin.getChildren().add(myShape);	
		myShape.setX(x);
		myShape.setY(y);
		myShape.setID(Vid++);
		paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setLayoutX(x);
		paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setLayoutY(y);
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
						deleteID = ((MyShapes) event.getSource()).getID();
						break;
					}
				}
				event.consume();					
			}
			
		});
		paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setOnDragDone(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				for(int i = 0; i < v.size(); i++)
				{
					if(v.elementAt(i).getID() == deleteID)
					{
						v.remove(i);
						break;
					}
				}
				paneDessin.getChildren().remove(draggedIndex);
				event.consume();
			}
			
		});
		
		paneDessin.getChildren().get(paneDessin.getChildren().size() - 1).setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println(event.getSource().toString());
				
					if(arrowStyle != null)
					{
						int clickedIndex = 0;
						for(int i = 0; i < paneDessin.getChildren().size(); i++)
						{
							if(paneDessin.getChildren().get(i).equals(((Node) event.getSource())))
							{
								clickedIndex = i;
								break;
							}
						}
						
	    				if(lastClickedIndex == -1)
	    				{
	    					lastClickedIndex = clickedIndex;
	    				}
	    				else
	    				{
	    					// TYPES DE FORMES
	    					EShape eshape1 = ((MyShapes) paneDessin.getChildren().get(lastClickedIndex)).getMyEShape();
	    					EShape eshape2 = ((MyShapes) paneDessin.getChildren().get(clickedIndex)).getMyEShape();
	    					
	    					// AJUSTEMENTS POUR LES CARR�S
	    					double ajustX1 = paneDessin.getChildren().get(lastClickedIndex).getLayoutBounds().getWidth();
	    					double ajustY1 = paneDessin.getChildren().get(lastClickedIndex).getLayoutBounds().getHeight()/2;
	    					double ajustX2 = 0;
	    					double ajustY2 = paneDessin.getChildren().get(clickedIndex).getLayoutBounds().getHeight()/2;
	    					if(paneDessin.getChildren().get(lastClickedIndex).getLayoutX() >
	    						paneDessin.getChildren().get(clickedIndex).getLayoutX())
	    					{
	    						ajustX1 =0;
	    						ajustX2 = paneDessin.getChildren().get(clickedIndex).getLayoutBounds().getWidth();
	    					}
	    					
	    					// AJUSTEMENTS SI LA FORME DE D�PART EST UN ROND/OVALE 
	    					if(eshape1 == EShape.EnergySource || eshape1 == EShape.MultiPhysicalConverter ||
	    							eshape1 == EShape.EnergySourceEstimator || eshape1 == EShape.MultiPhysicalConverterEstimator)
	    					{
	    						ajustX1 /= 2;
	    						ajustY1 = 0;
	    						if(paneDessin.getChildren().get(lastClickedIndex).getLayoutX() >
	    						paneDessin.getChildren().get(clickedIndex).getLayoutX())
		    					{
		    						ajustX1 = -paneDessin.getChildren().get(lastClickedIndex).getLayoutBounds().getWidth() /2;
		    					}
	    					}
	    					
	    					// AJUSTEMENTS SI LA FORME D'ARRIV�E EST UN ROND/OVALE 
	    					if(eshape2 == EShape.EnergySource || eshape2 == EShape.MultiPhysicalConverter ||
	    							eshape2 == EShape.EnergySourceEstimator || eshape2 == EShape.MultiPhysicalConverterEstimator)
	    					{
	    						ajustX2 = -paneDessin.getChildren().get(clickedIndex).getLayoutBounds().getWidth() /2;
	    						ajustY2 = 0;
	    						if(paneDessin.getChildren().get(lastClickedIndex).getLayoutX() >
	    						paneDessin.getChildren().get(clickedIndex).getLayoutX())
		    					{
		    						ajustX2 = paneDessin.getChildren().get(clickedIndex).getLayoutBounds().getWidth() /2;
		    					}
	    					}
	    					
	    					// AJOUT DE LA FL�CHE
	    					MyShapes arrow = new MyArrow(arrowStyle,
	    							paneDessin.getChildren().get(lastClickedIndex).getLayoutX() + ajustX1,
	    							paneDessin.getChildren().get(lastClickedIndex).getLayoutY() + ajustY1,
	    							paneDessin.getChildren().get(clickedIndex).getLayoutX() + ajustX2,
	    							paneDessin.getChildren().get(clickedIndex).getLayoutY() + ajustY2,
	    							lastClickedIndex,clickedIndex );
	    					paneDessin.getChildren().add(arrow);
	    					v.add(arrow);
	    					lastClickedIndex = -1;
	    				}
					}
					
					event.consume();
                }  		
    	});
		
	}
	
}