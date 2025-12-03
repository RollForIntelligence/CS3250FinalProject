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
	public final int PLAYER_SPEED = 5;
	
	public Player(int width, int height, String name) {
		super(width < 100 ? 100 : width, height < 125 ? 125 : 125);
		this.name = name;
		this.inventory = new Inventory();
		this.currentHealth = maxHealth;
		super.setxPos(1000);
		super.setyPos(1000);
		
		inventory.addItem(new Sword("Sword", 5));
		inventory.addItem(new Spear("Spear", 3));
	}

	public double getxPos() {
		return super.getxPos();
	}

	public double getyPos() {
		return super.getyPos();
	}
	
	public void move(double xMovement, double yMovement) {
		super.setxPos(super.getxPos() + xMovement);
		super.setyPos(super.getyPos() + yMovement);
	}
	
	public void unmove(double xMovement, double yMovement) {
		super.setxPos(super.getxPos() - xMovement);
		super.setyPos(super.getyPos() - yMovement);
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
	
	public int getDamage() {
		if (this.inventory.getFirstItem() instanceof Weapon) {
			Weapon weapon = (Weapon) inventory.getFirstItem();
			return weapon.getDamage();
		}
		else {
			return 1;
		}
	}
	
	public int getRange() {
		if (this.inventory.getFirstItem() instanceof Weapon) {
			Weapon weapon = (Weapon) inventory.getFirstItem();
			return weapon.getRange();
		}
		else {
			return 75;
		}
	}
	
	public Inventory getInventory() {
		return inventory;
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
		gc.setStroke(Color.BROWN);
		gc.setLineWidth(2);
		if (frame / 4 == 0) {
			gc.fillArc(39, 8, 20, 20, 45, 135, ArcType.CHORD);
		}
		else if (frame / 4 == 1) {
			gc.fillArc(41, 8, 20, 20, 0, 135, ArcType.CHORD);
		}
		else if (frame / 4 == 2) {
			gc.fillArc(39, 8, 22, 22, 315, 270, ArcType.CHORD);
		}
		else if (frame / 4 == 3) {
			gc.strokeArc(39, 9, 22, 22, 45, 90, ArcType.OPEN);
		}
	
		// Left arm
		gc.setStroke(Color.TAN);
		gc.setLineCap(StrokeLineCap.ROUND);
		gc.setLineWidth(10);
		if (frame == 1 || frame == 3 || frame == 5 || frame == 7) {
			gc.strokeLine(50, 40, 50, 70);
		}
		else if (frame == 0 || frame == 6) {
			gc.strokeLine(50, 40, 68, 64);
		}
		else if (frame == 2 || frame == 4) {
			gc.strokeLine(50, 40, 32, 64);
		}
		else if (frame == 9 || frame == 11) {
			gc.strokeLine(35, 40, 35, 70);
			gc.strokeLine(65, 40, 65, 70);
		}
		else if (frame == 8 || frame == 10) {
			gc.strokeLine(35, 40, 35, 64);
			gc.strokeLine(65, 40, 65, 64);
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
		if (frame == 0 || frame == 6) {
			gc.strokeLine(50, 75, 74, 107);
			gc.fillOval(72, 107, 10, 10);
		}
		else if (frame == 1 || frame == 7) {
			gc.strokeLine(50, 75, 50, 115);
			gc.fillOval(45, 115, 10, 10);
		}
		else if (frame == 2 || frame == 4) {
			gc.strokeLine(50, 75, 26, 107);
			gc.fillOval(18, 107, 10, 10);
		}
		else if (frame == 3 || frame == 5) {
			gc.strokeLine(50, 75, 50, 115);
			gc.fillOval(45, 115, 10, 10);
		}
		else if (frame == 9 || frame == 11 || frame == 13 || frame == 15) {
			gc.strokeLine(44, 75, 44, 115);
			gc.fillOval(39, 115, 10, 10);
		}
		else if (frame == 8 || frame == 14) {
			gc.strokeLine(44, 75, 44, 107);
			gc.fillOval(40, 107, 8, 8);
		}
		else if (frame == 10 || frame == 12) {
			gc.strokeLine(44, 75, 44, 107);
			gc.fillOval(38, 107, 12, 12);
		}
		
		// Right leg and Shoe
		gc.setStroke(Color.DARKBLUE);
		if (frame == 0 || frame == 6) {
			gc.strokeLine(50, 75, 26, 107);
			gc.fillOval(18, 107, 10, 10);
		}
		else if (frame == 1 || frame == 7) {
			gc.strokeLine(50, 75, 50, 115);
			gc.fillOval(45, 115, 10, 10);
		}
		else if (frame == 2 || frame == 4) {
			gc.strokeLine(50, 75, 74, 107);
			gc.fillOval(72, 107, 10, 10);
		}
		else if (frame == 3 || frame == 5) {
			gc.strokeLine(50, 75, 50, 115);
			gc.fillOval(45, 115, 10, 10);
		}
		else if (frame == 9 || frame == 11 || frame == 13 || frame == 15) {
			gc.strokeLine(56, 75, 56, 115);
			gc.fillOval(51, 115, 10, 10);
		}
		else if (frame == 10 || frame == 12) {
			gc.strokeLine(56, 75, 56, 107);
			gc.fillOval(52, 107, 8, 8);
		}
		else if (frame == 8 || frame == 14) {
			gc.strokeLine(56, 75, 56, 107);
			gc.fillOval(50, 107, 12, 12);
		}
		
		// Right arm
		gc.setStroke(Color.TAN);
		gc.setLineCap(StrokeLineCap.ROUND);
		gc.setLineWidth(10);
		if (frame == 1 || frame == 3 || frame == 5 || frame == 7) {
			gc.strokeLine(50, 40, 50, 70);
		}
		else if (frame == 0 || frame == 6) {
			gc.strokeLine(50, 40, 32, 64);
		}
		else if (frame == 2 || frame == 4) {
			gc.strokeLine(50, 40, 68, 64);
		}
		else if (frame == 13 || frame == 15) {
			gc.strokeLine(35, 40, 35, 70);
			gc.strokeLine(65, 40, 65, 70);
		}
		else if (frame == 12 || frame == 14) {
			gc.strokeLine(35, 40, 35, 64);
			gc.strokeLine(65, 40, 65, 64);
		}
	}
}
