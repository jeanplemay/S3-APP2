package e04p4;

//This class makes part of the design pattern STRATEGY together with class OpenStrategy, OpenTxt, OpenXML and FactoryController
public interface OpenStrategy {
	
	public void open(FactoryController fController);

}
