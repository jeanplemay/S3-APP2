package e04p3;

import e04p3.MyShapes.EShape;

//This class makes part of the design pattern COMMAND with class FactoryController, Invoker, Command,
//AddShapeCommand, AddArrowCommand and MoveShape Command;
public class AddArrowCommand extends Command {
	private FactoryController fController;
	private EShape eshape;
	private double x1;
	private double y1;
	private double x2;
	private double y2;

	public AddArrowCommand(FactoryController fController,EShape eshape, double x1, double y1,double x2, double y2) {
		this.fController = fController;
		this.eshape = eshape;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public void execute() {
		MyShapes arrow = new MyShapes(eshape, x1, y1, x2, y2);
		fController.getPaneDessin().getChildren().add(arrow);
		fController.getV().add(arrow);
		
	}

	public void unexecute() {
		fController.getPaneDessin().getChildren().remove(fController.getPaneDessin().getChildren().size() - 1);
		fController.getV().remove(fController.getV().size()-1);
		
	}
}
