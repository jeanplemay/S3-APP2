package e04p3;

import e04p3.MyShapes.EShape;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class AddShapeCommand extends Command{

	private FactoryController fController;
	private EShape eshape;
	private double x;
	private double y;

	public AddShapeCommand(FactoryController fController, EShape eshape,double x, double y) {
		this.fController = fController;
		this.eshape = eshape;
		this.x = x;
		this.y = y;
	}

	public void execute() {
		MyShapes myShape = new MyShapes(eshape);
		fController.getV().add(myShape);
		fController.getPaneDessin().getChildren().add(myShape);	
		fController.getPaneDessin().getChildren().get(fController.getPaneDessin().getChildren().size() - 1).setLayoutX(x);
		fController.getPaneDessin().getChildren().get(fController.getPaneDessin().getChildren().size() - 1).setLayoutY(y);
		fController.getPaneDessin().getChildren().get(fController.getPaneDessin().getChildren().size() - 1).setOnDragDetected(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {				
				Dragboard db = fController.getPaneDessin().getChildren().get(fController.getPaneDessin().getChildren().size() - 1).startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
		        content.putString("Dragged");
				db.setContent(content);
				fController.setDraggedShape(eshape);		
				for(int i = 0; i < fController.getPaneDessin().getChildren().size(); i++)
				{
					if(fController.getPaneDessin().getChildren().get(i).equals(((Node) event.getSource())))
					{
						fController.setDraggedIndex(i);
						break;
					}
				}
			}
			
		});
		fController.getPaneDessin().getChildren().get(fController.getPaneDessin().getChildren().size() - 1).setOnDragDone(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				fController.getPaneDessin().getChildren().remove(fController.getDraggedIndex());					
			}
			
		});
		
	}

	public void unexecute() {
		fController.getPaneDessin().getChildren().remove(fController.getPaneDessin().getChildren().size() - 1);
		fController.getV().remove(fController.getV().size()-1);
		
	}
	
}
