import javafx.scene.canvas.Canvas;

public class Actor extends Canvas {
//	private Image sprite;
	private double xPos;
	private double yPos;
	
	public Actor(int width, int height) {
		super(width, height);
	}

	public double getxPos() {
		return xPos;
	}

	public void setxPos(double xPos) {
		this.xPos = xPos;
	}

	public double getyPos() {
		return yPos;
	}

	public void setyPos(double yPos) {
		this.yPos = yPos;
	}
	
	public void DrawCharacter() {
		
	}
}
