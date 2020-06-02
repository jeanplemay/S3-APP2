package e04p4;

import java.io.FileWriter;
import java.io.IOException;

import e04p4.MyShapes.EShape;
import javafx.stage.FileChooser;

//This class makes part of the design pattern STRATEGY together with class SaveStrategy, SaveTxt, SaveXML and FactoryController
public class SaveTxt implements SaveStrategy {

	public void save(FactoryController fController) {
		FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt"));
        fileChooser.setInitialFileName("myCanvas.txt");

        EShape shapeWrite;
		try{
			FileWriter file = new FileWriter(fileChooser.showSaveDialog(null));
			for(int i = 0; i < fController.getV().size(); i++)
			{
				if(fController.getV().elementAt(i).getMyEShape() == EShape.SimpleArrow || fController.getV().elementAt(i).getMyEShape() == EShape.DoubleArrow)
				{
					file.write((int) ((MyArrow) fController.getV().elementAt(i)).getStartShapeIndex() + " ");
					file.write((int) ((MyArrow) fController.getV().elementAt(i)).getEndShapeIndex() + " ");
				} else {
					file.write((int) fController.getV().elementAt(i).getX() + " ");
					file.write((int) fController.getV().elementAt(i).getY()+ " ");
				}
				shapeWrite = fController.getV().elementAt(i).getMyEShape();
				file.write(shapeWrite.toString()+ "\n");				
			}
			file.close();
            fController.setStatusBarMessage("Saved");
		} catch (IOException e){
			e.printStackTrace();               
			fController.setStatusBarMessage("Fichier Introuvable");
		}
		
	}

}
