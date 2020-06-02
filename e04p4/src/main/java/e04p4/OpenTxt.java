package e04p4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import e04p4.MyShapes.EShape;
import javafx.stage.FileChooser;

//This class makes part of the design pattern STRATEGY together with class OpenStrategy, OpenTxt, OpenXML and FactoryController
public class OpenTxt implements OpenStrategy{

	public void open(FactoryController fController) {
		FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt"));
        File file = fileChooser.showOpenDialog(null);

        fController.getV().removeAllElements();
        fController.getPaneDessin().getChildren().clear();
        
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
					EShape eshape1 = ((MyShapes) fController.getPaneDessin().getChildren().get(lastClickedIndex)).getMyEShape();
					EShape eshape2 = ((MyShapes) fController.getPaneDessin().getChildren().get(clickedIndex)).getMyEShape();
					
					// AJUSTEMENTS POUR LES CARR�S
					double ajustX1 = fController.getPaneDessin().getChildren().get(lastClickedIndex).getLayoutBounds().getWidth();
					double ajustY1 = fController.getPaneDessin().getChildren().get(lastClickedIndex).getLayoutBounds().getHeight()/2;
					double ajustX2 = 0;
					double ajustY2 = fController.getPaneDessin().getChildren().get(clickedIndex).getLayoutBounds().getHeight()/2;
					if(fController.getPaneDessin().getChildren().get(lastClickedIndex).getLayoutX() >
					fController.getPaneDessin().getChildren().get(clickedIndex).getLayoutX())
					{
						ajustX1 =0;
						ajustX2 = fController.getPaneDessin().getChildren().get(clickedIndex).getLayoutBounds().getWidth();
					}
					
					// AJUSTEMENTS SI LA FORME DE D�PART EST UN ROND/OVALE 
					if(eshape1 == EShape.EnergySource || eshape1 == EShape.MultiPhysicalConverter ||
							eshape1 == EShape.EnergySourceEstimator || eshape1 == EShape.MultiPhysicalConverterEstimator)
					{
						ajustX1 /= 2;
						ajustY1 = 0;
						if(fController.getPaneDessin().getChildren().get(lastClickedIndex).getLayoutX() >
						fController.getPaneDessin().getChildren().get(clickedIndex).getLayoutX())
    					{
    						ajustX1 = -fController.getPaneDessin().getChildren().get(lastClickedIndex).getLayoutBounds().getWidth() /2;
    					}
					}
					
					// AJUSTEMENTS SI LA FORME D'ARRIV�E EST UN ROND/OVALE 
					if(eshape2 == EShape.EnergySource || eshape2 == EShape.MultiPhysicalConverter ||
							eshape2 == EShape.EnergySourceEstimator || eshape2 == EShape.MultiPhysicalConverterEstimator)
					{
						ajustX2 = -fController.getPaneDessin().getChildren().get(clickedIndex).getLayoutBounds().getWidth() /2;
						ajustY2 = 0;
						if(fController.getPaneDessin().getChildren().get(lastClickedIndex).getLayoutX() >
						fController.getPaneDessin().getChildren().get(clickedIndex).getLayoutX())
    					{
    						ajustX2 = fController.getPaneDessin().getChildren().get(clickedIndex).getLayoutBounds().getWidth() /2;
    					}
					}
				
 					MyShapes arrow = new MyArrow(tempShape,
 							fController.getV().get((int) x).getX()+ajustX1,
 							fController.getV().get((int) x).getY()+ajustY1,
 							fController.getV().get((int) y).getX()+ajustX2,
 							fController.getV().get((int) y).getY()+ajustY2,
 							(int)x, (int)y);
 					fController.getPaneDessin().getChildren().add(arrow);
					fController.getV().add(arrow);
 				}else	fController.addShape(tempShape, x, y);
 				fController.setStatusBarMessage("Opened");
             }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	

}
