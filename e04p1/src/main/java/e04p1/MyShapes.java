package e04p1;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

// This class makes part of the design pattern MVC togheter with class FactoryController and scene.fxml
public class MyShapes {
	public enum EShape {EnergySource,
						MonoPhysicalConverter,
						MultiPhysicalConverter,
						EnergyAccumulation,
						MonoPhysicalCoupling,
						MultiPhysicalCoupling,
						DirectInversion,
						IndirectInversion,
						Strategy,
						EnergySourceEstimator,
						MonoPhysicalConverterEstimator,
						MultiPhysicalConverterEstimator,
						EnergyAccumulationEstimator,
						MonoPhysicalCouplingEstimator,
						MultiPhysicalCouplingEstimator,
						};
	static final int a = 30;
	static final int b = 2;
	static final int c = 40;
	
	public Shape shape;
	public double x;
	public double y;
	MyShapes(Shape s)
	{
		shape = s;
		x = 0;
		y = 0;
	}
	
	MyShapes(EShape eshape)
	{
		this.x = 0;
		this.y = 0;
		
		switch(eshape)
		{
			case EnergySource :
				Shape oval = new Ellipse(a,a/2);
		    	oval.setFill(Color.web("98FB98"));
		    	oval.setStroke(Color.web("005000"));
		    	oval.setStrokeWidth(b);
		    	this.shape = oval;
				break;
				
			case MonoPhysicalConverter :
				Shape rectangle = new Rectangle(a,a);
		    	rectangle.setFill(Color.web("FFD700"));
		    	rectangle.setStroke(Color.web("FF0000"));
		    	rectangle.setStrokeWidth(b);
		    	this.shape = rectangle;
				break;
				
			case MultiPhysicalConverter :
				Shape circle = new Circle(a/2);
		    	circle.setFill(Color.web("FFD700"));
		    	circle.setStroke(Color.web("FF0000"));
		    	circle.setStrokeWidth(b);
		    	this.shape = circle;
				break;
				
			case EnergyAccumulation :
				Shape polygon4 = new Polygon(
		        		a/2.0,0.0,
		        		a/2.0,a*1.0,
		        		0.0,a*1.0,
		        		0.0,0.0,
		        		a/2.0,0.0,
		        		0.0,a*1.0
		        		);
				polygon4.setFill(Color.web("FFD700"));
				polygon4.setStroke(Color.web("FF0000"));
				polygon4.setStrokeWidth(b);
		    	this.shape = polygon4;
				break;
				
			case MonoPhysicalCoupling :
				Shape shape2 = Shape.union(new Rectangle(a,a), new Rectangle(a*2/3,a*2/3,a,a));
		    	shape2.setFill(Color.web("FFD700"));
		    	shape2.setStroke(Color.web("FF0000"));
		    	shape2.setStrokeWidth(b);
		    	this.shape = shape2;
				break;
				
			case MultiPhysicalCoupling :
		    	Shape shape3 = Shape.union( new Circle(a/2),  new Circle(0,a*2/3,a/2));
		    	shape3.setFill(Color.web("FFD700"));
		    	shape3.setStroke(Color.web("FF0000"));
		    	shape3.setStrokeWidth(b);
		    	this.shape = shape3;
				break;
				
			case DirectInversion :
				Shape polygon = new Polygon(
		        		a/4.0,0.0,
		        		a*5.0/4.0,0.0,
		        		a*1.0,a*1.0,
		        		0.0,a*1.0
		        		);
		    	polygon.setFill(Color.web("87CEEB"));
		    	polygon.setStroke(Color.web("0000FF"));
		    	polygon.setStrokeWidth(b/2.0);
		    	this.shape = polygon;
		    	break;
		    	
			case IndirectInversion :
				Shape polygon2 = new Polygon(
		        		a/4.0,0.0,
		        		a*5.0/4.0,0.0,
		        		a*1.0,a*1.0,
		        		0.0,a*1.0,
		        		a/4.0,0.0,
		        		a*1.0,a*1.0
		        		);
				polygon2.setFill(Color.web("87CEEB"));
				polygon2.setStroke(Color.web("0000FF"));
				polygon2.setStrokeWidth(b/2.0);
		    	this.shape = polygon2;
		    	break;
		    	
			case Strategy :
				Shape polygon3 = new Polygon(
		        		a/4.0,0.0,
		        		a*13.0/4.0,0.0,
		        		a*3.0,a*1.0,
		        		0.0,a*1.0
		        		);
		    	polygon3.setFill(Color.web("0000FF"));
		    	polygon3.setStroke(Color.web("0000FF"));
		    	polygon3.setStrokeWidth(b/2.0);
		    	this.shape = polygon3;
		    	break;
		    	
			case EnergySourceEstimator :
				Shape oval2 = new Ellipse(a,a/2);
		    	oval2.setFill(Color.web("EE82EE"));
		    	oval2.setStroke(Color.web("0000FF"));
		    	oval2.setStrokeWidth(b/2.0);
		    	this.shape = oval2;
				break;
				
			case MonoPhysicalConverterEstimator :
				Shape rectangle5 = new Rectangle(a,a);
		    	rectangle5.setFill(Color.web("EE82EE"));
		    	rectangle5.setStroke(Color.web("0000FF"));
		    	rectangle5.setStrokeWidth(b/2.0);
		    	this.shape = rectangle5;
				break;
				
			case MultiPhysicalConverterEstimator :
				Shape circle2 = new Circle(a/2);
		    	circle2.setFill(Color.web("EE82EE"));
		    	circle2.setStroke(Color.web("0000FF"));
		    	circle2.setStrokeWidth(b/2.0);
		    	this.shape = circle2;
				break;
				
			case EnergyAccumulationEstimator :
				Shape polygon5 = new Polygon(
		        		a/2.0,0.0,
		        		a/2.0,a*1.0,
		        		0.0,a*1.0,
		        		0.0,0.0,
		        		a/2.0,0.0,
		        		0.0,a*1.0
		        		);
				polygon5.setFill(Color.web("EE82EE"));
				polygon5.setStroke(Color.web("0000FF"));
				polygon5.setStrokeWidth(b/2.0);
		    	this.shape = polygon5;
				break;
				
			case MonoPhysicalCouplingEstimator :
				Shape shape6 = Shape.union(new Rectangle(a,a), new Rectangle(a*2/3,a*2/3,a,a));
		    	shape6.setFill(Color.web("EE82EE"));
		    	shape6.setStroke(Color.web("0000FF"));
		    	shape6.setStrokeWidth(b/2.0);
		    	this.shape = shape6;
				break;
				
			case MultiPhysicalCouplingEstimator :
				Shape shape7 = Shape.union( new Circle(a/2),  new Circle(0,a*2/3,a/2));
		    	shape7.setFill(Color.web("EE82EE"));
		    	shape7.setStroke(Color.web("0000FF"));
		    	shape7.setStrokeWidth(b/2.0);
		    	this.shape = shape7;
				break;
				
				
		}

	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
