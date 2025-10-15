import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeLineCap;

public class Player extends Actor {
	private String name;
	private Image sprite;
	private Inventory inventory;
	private int maxHealth = 20;
	private int currentHealth;
	
	public Player(int width, int height, String name) {
		super(width < 100 ? 100 : width, height < 125 ? 125 : 125);
		this.name = name;
		this.inventory = new Inventory();
		this.currentHealth = maxHealth;
	}

	public double getxPos() {
		return super.getxPos();
	}

	public void setxPos(double xPos) {
		super.setxPos(xPos);
	}

	public double getyPos() {
		return super.getyPos();
	}

	public void setyPos(double yPos) {
		super.setyPos(yPos);
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		if (currentHealth < 0) {
			return;
		}
		else if (currentHealth > this.getMaxHealth()) {
			return;
		}
		this.currentHealth = currentHealth;
	}
	
	public boolean TakeDamage(int damageValue) {
		boolean isDead = false;
		currentHealth -= damageValue;
		if (currentHealth <= 1) {
			isDead = true;
		}
		return isDead;
	}
	
	public void DrawCharacter(int frame) {
		GraphicsContext gc = this.getGraphicsContext2D();
		
		gc.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		// TODO: create a spritesheet to replace all of this drawing;
		// paint.net / pixelart
		// head
		gc.setFill(Color.TAN);
		gc.fillOval(40, 10, 20, 20);
		
		// hair
		gc.setFill(Color.BROWN);
		gc.fillArc(39, 8, 20, 20, 45, 135, ArcType.CHORD);
	
		// Left arm
		gc.setStroke(Color.TAN);
		gc.setLineCap(StrokeLineCap.ROUND);
		gc.setLineWidth(10);
		if (frame == 1 || frame == 3) {
			gc.strokeLine(50, 40, 50, 70);
		}
		else if (frame == 0) {
			gc.strokeLine(50, 40, 68, 64);
		}
		else if (frame == 2) {
			gc.strokeLine(50, 40, 32, 64);
		}
		
		// Torso
		gc.setFill(Color.DARKRED);
		gc.fillPolygon(new double[] {35, 65, 60, 40}
					 , new double[] {30, 30, 70, 70}, 4);
		
		// Waist
		gc.setFill(Color.DARKBLUE);
		gc.fillArc(40, 60, 20, 20, 180, 180, ArcType.CHORD);
		
		
		
		// Left leg and Shoe
		gc.setStroke(Color.DARKBLUE);
		gc.setFill(Color.BLACK);
		if (frame == 0) {
			gc.strokeLine(50, 75, 74, 107);
			gc.fillPolygon(new double[] {77.5, 70.5, 76, 88}
			, new double[] {105, 110, 118, 108}, 4);
		}
		else if (frame == 1) {
			gc.strokeLine(50, 75, 50, 115);
			gc.fillPolygon(new double[] {45, 45, 60, 55}
			, new double[] {110, 120, 120, 110}, 4);
		}
		else if (frame == 2) {
			gc.strokeLine(50, 75, 26, 107);
			gc.fillPolygon(new double[] {29.5, 21, 15, 30}
			, new double[] {110, 105, 114, 120}, 4);
		}
		else if (frame == 3) {
			gc.strokeLine(50, 75, 50, 115);
			gc.fillPolygon(new double[] {45, 45, 60, 55}
			, new double[] {110, 120, 120, 110}, 4);
		}
		
		// Right leg and Shoe
		gc.setStroke(Color.DARKBLUE);
		if (frame == 0) {
			gc.strokeLine(50, 75, 26, 107);
			gc.fillPolygon(new double[] {29.5, 21, 15, 30}
			, new double[] {110, 105, 114, 120}, 4);
		}
		else if (frame == 1) {
			gc.strokeLine(50, 75, 50, 115);
			gc.fillPolygon(new double[] {45, 45, 60, 55}
			, new double[] {110, 120, 120, 110}, 4);
		}
		else if (frame == 2) {
			gc.strokeLine(50, 75, 74, 107);
			gc.fillPolygon(new double[] {77.5, 70.5, 76, 88}
			, new double[] {105, 110, 118, 108}, 4);
		}
		else if (frame == 3) {
			gc.strokeLine(50, 75, 50, 115);
			gc.fillPolygon(new double[] {45, 45, 60, 55}
			, new double[] {110, 120, 120, 110}, 4);
		}
		
		// Right arm
		gc.setStroke(Color.TAN);
		gc.setLineCap(StrokeLineCap.ROUND);
		gc.setLineWidth(10);
		if (frame == 1 || frame == 3) {
			gc.strokeLine(50, 40, 50, 70);
		}
		else if (frame == 0) {
			gc.strokeLine(50, 40, 32, 64);
		}
		else if (frame == 2) {
			gc.strokeLine(50, 40, 68, 64);
		}
	}
}
