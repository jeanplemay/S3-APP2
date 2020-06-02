package e04p4;

//This class makes part of the design pattern STRATEGY together with class SaveStrategy, SaveTxt, SaveXML and FactoryController
public interface SaveStrategy {
	
	public void save(FactoryController fController);

}
