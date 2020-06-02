package e04p2;

import e04p2.MyShapes.EShape;

//This class makes part of the design pattern STATE PATERN together with class State and DrawState
public class ConnectionState implements State {
	
	public ConnectionState (FactoryController fController)
	{
		fController.getTitledPane1().setExpanded(false);
		fController.getTitledPane2().setExpanded(false);
		fController.getTitledPane3().setExpanded(false);
		fController.getTitledPane4().setExpanded(false);
		fController.getTitledPane1().setCollapsible(false);
		fController.getTitledPane2().setCollapsible(false);
		fController.getTitledPane3().setCollapsible(false);
		fController.getTitledPane4().setCollapsible(false);
		fController.getButtonArrows().setStyle("-fx-background-color: green;");
	}
	
	public void simpleRedArrow(FactoryController fController)
	{
		fController.setArrowStyle(EShape.SimpleArrow);
		fController.setStatusBarMessage("Select objects to add simple connection.");
	}
	
	public void doubleBlackArrow(FactoryController fController)
	{
		fController.setArrowStyle(EShape.DoubleArrow);
		fController.setStatusBarMessage("Select objects to add double connection.");
	}
	
	public void changeState(FactoryController fController)
	{
		fController.setState(new DrawState(fController));
	}
	
	public String toString()
	{
		return "Mode connexions (connection state)";
	}
	
}