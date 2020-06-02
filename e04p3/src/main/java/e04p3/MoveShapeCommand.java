package e04p3;

import e04p3.MyShapes.EShape;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

//This class makes part of the design pattern COMMAND with class FactoryController, Invoker, Command,
//AddShapeCommand, AddArrowCommand and MoveShape Command;
public class MoveShapeCommand extends Command {

	private FactoryController fController;
	private EShape eshape;
	private double x;
	private double y;
	private double oldX;
	private double oldY;
	private int index;
	private Node oldShape;

	public MoveShapeCommand(FactoryController fController, EShape eshape,double x, double y) {
		this.fController = fController;
		this.eshape = eshape;
		this.x = x;
		this.y = y;
		this.index=-1;
	}

	public void execute() {
		if(this.index == -1) this.index = fController.getDraggedIndex();
		
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
		
		oldX = fController.getPaneDessin().getChildren().get(fController.getDraggedIndex()).getLayoutX();
		oldY = fController.getPaneDessin().getChildren().get(fController.getDraggedIndex()).getLayoutY();
		oldShape = fController.getPaneDessin().getChildren().get(fController.getPaneDessin().getChildren().size() - 1);
		
		fController.getPaneDessin().getChildren().remove(this.index);
	}

	public void unexecute() {
		int oldIndex=-1;
		for(int i = 0; i < fController.getPaneDessin().getChildren().size(); i++)
		{
			if(fController.getPaneDessin().getChildren().get(i)== oldShape)
			{
				oldIndex = i;
				break;
			}
		}
		fController.getPaneDessin().getChildren().remove(oldIndex);
		fController.getV().remove(fController.getV().size()-1);
		
		MyShapes myShape = new MyShapes(eshape);
		fController.getV().add(myShape);
		fController.getPaneDessin().getChildren().add(myShape);	
		fController.getPaneDessin().getChildren().get(fController.getPaneDessin().getChildren().size() - 1).setLayoutX(oldX);
		fController.getPaneDessin().getChildren().get(fController.getPaneDessin().getChildren().size() - 1).setLayoutY(oldY);
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
		
	}
	
}