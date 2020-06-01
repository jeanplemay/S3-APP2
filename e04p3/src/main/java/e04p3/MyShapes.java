package e04p3;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

// This class makes part of the design pattern MVC together with class FactoryController and scene.fxml
public class MyShapes extends Group {
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
	
	public double x;
	public double y;
	public int index;
	public EShape myEShape;
	
	MyShapes(EShape eshape)
	{
		this.x = 0;
		this.y = 0;
		this.myEShape = eshape;
		this.index = 0;
			
		switch(eshape)
		{
			case EnergySource :
				Shape oval = new Ellipse(a,a/2);
		    	oval.setFill(Color.web("98FB98"));
		    	oval.setStroke(Color.web("005000"));
		    	oval.setStrokeWidth(b);
		    	this.getChildren().add(oval);
				break;
				
			case MonoPhysicalConverter :
				Shape rectangle = new Rectangle(a,a);
		    	rectangle.setFill(Color.web("FFD700"));
		    	rectangle.setStroke(Color.web("FF0000"));
		    	rectangle.setStrokeWidth(b);
		    	this.getChildren().add(rectangle);
				break;
				
			case MultiPhysicalConverter :
				Shape circle = new Circle(a/2);
		    	circle.setFill(Color.web("FFD700"));
		    	circle.setStroke(Color.web("FF0000"));
		    	circle.setStrokeWidth(b);
		    	this.getChildren().add(circle);
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
				this.getChildren().add(polygon4);
				break;
				
			case MonoPhysicalCoupling :
			
				Rectangle rect1 = new Rectangle(a,a);
				rect1.setFill(Color.web("FFD700"));
				rect1.setStroke(Color.web("FF0000"));
				rect1.setStrokeWidth(b);
				Rectangle rect2 = new Rectangle(a*2/3,a*2/3,a,a);
				rect2.setFill(Color.web("FFD700"));
				rect2.setStroke(Color.web("FF0000"));
				rect2.setStrokeWidth(b);
				Rectangle rect3 = new Rectangle(a*2/3,a*2/3,a/3,a/3);
				rect3.setFill(Color.web("FFD700"));
				rect3.setStroke(Color.web("FF0000"));
				rect3.setStrokeWidth(b);
				
				getChildren().add(rect1);
				getChildren().add(rect2);
				getChildren().add(rect3);
	
				break;
				
			case MultiPhysicalCoupling :
				Circle c1 =  new Circle(a/2);
				c1.setFill(Color.web("FFD700"));
				c1.setStroke(Color.web("FF0000"));
				c1.setStrokeWidth(b);
				Circle c2 = new Circle(0,a*2/3,a/2);
				c2.setFill(Color.web("FFD700"));
				c2.setStroke(Color.web("FF0000"));
				c2.setStrokeWidth(b);
				Shape ellipse = new Ellipse(0,a/3,a/3,a/6);
				ellipse.setFill(Color.web("FFD700"));
				ellipse.setStroke(Color.web("FF0000"));
				ellipse.setStrokeWidth(b);
				
				this.getChildren().add(c1);
				this.getChildren().add(c2);
				this.getChildren().add(ellipse);
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
		    	this.getChildren().add(polygon);
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
				this.getChildren().add(polygon2);
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
		    	this.getChildren().add(polygon3);
		    	break;
		    	
			case EnergySourceEstimator :
				Shape oval2 = new Ellipse(a,a/2);
		    	oval2.setFill(Color.web("EE82EE"));
		    	oval2.setStroke(Color.web("0000FF"));
		    	oval2.setStrokeWidth(b/2.0);
		    	this.getChildren().add(oval2);
				break;
				
			case MonoPhysicalConverterEstimator :
				Shape rectangle5 = new Rectangle(a,a);
		    	rectangle5.setFill(Color.web("EE82EE"));
		    	rectangle5.setStroke(Color.web("0000FF"));
		    	rectangle5.setStrokeWidth(b/2.0);
		    	this.getChildren().add(rectangle5);
				break;
				
			case MultiPhysicalConverterEstimator :
				Shape circle2 = new Circle(a/2);
		    	circle2.setFill(Color.web("EE82EE"));
		    	circle2.setStroke(Color.web("0000FF"));
		    	circle2.setStrokeWidth(b/2.0);
		    	this.getChildren().add(circle2);
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
				this.getChildren().add(polygon5);
				break;
				
			case MonoPhysicalCouplingEstimator :
				
				Rectangle rect1e = new Rectangle(a,a);
				rect1e.setFill(Color.web("EE82EE"));
				rect1e.setStroke(Color.web("0000FF"));
				rect1e.setStrokeWidth(b/2.0);
				Rectangle rect2e = new Rectangle(a*2/3,a*2/3,a,a);
				rect2e.setFill(Color.web("EE82EE"));
				rect2e.setStroke(Color.web("0000FF"));
				rect2e.setStrokeWidth(b/2.0);
				Rectangle rect3e = new Rectangle(a*2/3,a*2/3,a/3,a/3);
				rect3e.setFill(Color.web("EE82EE"));
				rect3e.setStroke(Color.web("0000FF"));
				rect3e.setStrokeWidth(b/2.0);
				
				getChildren().add(rect1e);
				getChildren().add(rect2e);
				getChildren().add(rect3e);
				break;
				
			case MultiPhysicalCouplingEstimator :
				
				Circle c1e =  new Circle(a/2);
				c1e.setFill(Color.web("EE82EE"));
				c1e.setStroke(Color.web("0000FF"));
				c1e.setStrokeWidth(b/2.0);
				Circle c2e = new Circle(0,a*2/3,a/2);
				c2e.setFill(Color.web("EE82EE"));
				c2e.setStroke(Color.web("0000FF"));
				c2e.setStrokeWidth(b/2.0);
				Shape ellipse2 = new Ellipse(0,a/3,a/3,a/6);
				ellipse2.setFill(Color.web("EE82EE"));
				ellipse2.setStroke(Color.web("0000FF"));
				ellipse2.setStrokeWidth(b/2.0);
		    	
				this.getChildren().add(c1e);
				this.getChildren().add(c2e);
				this.getChildren().add(ellipse2);
				break;
				
				
		}

	}

	public EShape getMyEShape() {
		return myEShape;
	}
	
	public void getMyEShape(EShape e) {
		this.myEShape = e;
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
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int i) {
		this.index = i;
	}
}
