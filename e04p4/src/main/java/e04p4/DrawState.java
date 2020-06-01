package e04p4;

//This class makes part of the design pattern STATE PATERN together with class State and ConnectionState
public class DrawState implements State {
	
	public DrawState (FactoryController fController)
	{
		fController.getTitledPane1().setCollapsible(true);
		fController.getTitledPane2().setCollapsible(true);
		fController.getTitledPane3().setCollapsible(true);
		fController.getTitledPane4().setCollapsible(true);
    	fController.getButtonArrows().setStyle(null);
    	fController.getPaneDessin().setOnMouseClicked(null);
    	fController.setArrowStyle(null);
	}
	
	public void simpleRedArrow(FactoryController fController)
	{
		fController.setStatusBarMessage("You must be in Arrow Mode.");
	}
	
	public void doubleBlackArrow(FactoryController fController)
	{
		fController.setStatusBarMessage("You must be in Arrow Mode.");
	}
	
	public void changeState(FactoryController fController)
	{
		fController.setState(new ConnectionState(fController));
	}
	
	public String toString()
	{
		return "Mode dessin (draw state)";
	}

	
	

}
