package e04p4;

import e04p4.MyShapes.EShape;

public class MyArrow extends MyShapes {
	
	private int startShapeIndex;
	private int endShapeIndex;
	
	public MyArrow (EShape eshape, double startX, double startY, double endX, double endY, int startShapeIndex, int endShapeIndex )
	{
		super(eshape, startX, startY, endX, endY);
		this.startShapeIndex = startShapeIndex;
		this.endShapeIndex = endShapeIndex;
	}
	

	public int getStartShapeIndex() {
		return startShapeIndex;
	}

	public void setStartShapeIndex(int startShapeIndex) {
		this.startShapeIndex = startShapeIndex;
	}

	public int getEndShapeIndex() {
		return endShapeIndex;
	}

	public void setEndShapeIndex(int endShapeIndex) {
		this.endShapeIndex = endShapeIndex;
	}

	
	

}
