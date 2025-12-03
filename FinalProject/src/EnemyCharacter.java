import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EnemyCharacter extends Actor {
private static final ArrayList<Color> UNIFORM_COLORS = new ArrayList<Color>();
	
	static {
		UNIFORM_COLORS.add(Color.ORANGE);
		UNIFORM_COLORS.add(Color.YELLOW);
		UNIFORM_COLORS.add(Color.ORANGERED);
		UNIFORM_COLORS.add(Color.DARKORANGE);
		UNIFORM_COLORS.add(Color.PURPLE);
	}
	
	private GraphicsContext gc;
	private int maxHealth;
	private int currentHealth;
	private int damageDealt;
	private Color uniformColor;

	public EnemyCharacter(int width, int height, int hp, int damage) {
		super(width, height);
		gc = this.getGraphicsContext2D();
		
		maxHealth = hp;
		currentHealth = maxHealth;
		damageDealt = damage;
		uniformColor = UNIFORM_COLORS.get((int)(Math.random() * 5));
	}
	
	public int GetDamageDealt() {
		return damageDealt;
	}
	
	public boolean TakeDamage(int damageValue) {
		boolean isDead = false;
		currentHealth -= damageValue;
		if (currentHealth <= 1) {
			isDead = true;
		}
		return isDead;
	}
	
	@Override
	public void DrawCharacter() {
		gc.setFill(uniformColor);
		gc.fillPolygon(new double[] {.1 * this.getWidth(), .5 * this.getWidth(), .9 * this.getWidth()}
					 , new double [] {.2 * this.getHeight(), .9 * this.getHeight(), .2 * this.getHeight()}, 3);
		
		gc.setFill(Color.RED);
		gc.fillOval(.4 * this.getWidth(), .1 * this.getHeight(), .2 * this.getWidth(), .2 * this.getHeight());
	}
	
	public ArrayList<Color> getColors() {
		ArrayList<Color> returnValues = new ArrayList<>();
		returnValues.add(Color.RED);
		returnValues.add(uniformColor);
		return returnValues;
	}

}
