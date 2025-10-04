
public class Player {
	private String name;
	private String sprite = " o \n-+-\n | \n ^ ";
	private double xPos;
	private double yPos;
	private Inventory inventory;
	private int maxHealth = 20;
	private int currentHealth = 15;
	
	public String getSprite() {
		return sprite;
	}
	
	public void setSprite(String sprite) {
		this.sprite = sprite;
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
		this.currentHealth = currentHealth;
	}
}
