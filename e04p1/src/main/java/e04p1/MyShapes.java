package e04p1;

import javafx.scene.shape.Shape;

// This class makes part of the design pattern MVC togheter with class FactoryController and scene.fxml
public class MyShapes {
	public Shape shape;
	public double x;
	public double y;
	MyShapes(Shape s)
	{
		shape = s;
		x = 0;
		y = 0;
	}
}
