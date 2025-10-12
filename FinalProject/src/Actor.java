import javafx.scene.canvas.Canvas;

public class Actor extends Canvas {
//	private Image sprite;
	private int xPos;
	private int yPos;
	
	public Actor(int width, int height) {
		super(width, height);
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	
	public void DrawCharacter() {
		
	}
}
