import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EnemyCharacter extends Actor {
	private GraphicsContext gc;
	private int maxHealth;
	private int currentHealth;
	private int damageDealt;

	public EnemyCharacter(int width, int height) {
		super(width, height);
		gc = this.getGraphicsContext2D();
		
		maxHealth = 5;
		currentHealth = maxHealth;
		damageDealt = 3;
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
		gc.setFill(Color.ORANGE);
		gc.fillPolygon(new double[] {.1 * this.getWidth(), .5 * this.getWidth(), .9 * this.getWidth()}
					 , new double [] {.2 * this.getHeight(), .9 * this.getHeight(), .2 * this.getHeight()}, 3);
		
		gc.setFill(Color.RED);
		gc.fillOval(.4 * this.getWidth(), .1 * this.getHeight(), .2 * this.getWidth(), .2 * this.getHeight());
	}

}
