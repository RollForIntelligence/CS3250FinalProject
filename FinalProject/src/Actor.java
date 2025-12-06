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
	
	public double getCenterX() {
		return this.getLayoutX() + this.getWidth() / 2;
	}
	
	public double getCenterY() {
		return this.getLayoutY() + this.getHeight() / 2;
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
	
	public void move(double xMovement, double yMovement) {
		xPos += xMovement;
		yPos += yMovement;
	}
	
	public static double GetDistance(Actor to, Actor from) {
		double distance = Math.hypot(from.xPos - to.xPos, from.yPos - to.yPos);
		return distance;
	}
	
	public static double GetXDistance(Actor to, Actor from) {
		return from.xPos - to.xPos;
	}
	
	public static double GetYDistance(Actor to, Actor from) {
		return from.yPos - to.yPos;
	}
}
