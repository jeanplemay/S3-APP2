package e04p4;

//This class makes part of the design pattern STATE PATERN together with class ConnectionState and DrawState
public interface State {

	public void changeState(FactoryController fController);
	
	public void simpleRedArrow(FactoryController fController);
	public void doubleBlackArrow(FactoryController fController);
	

}
