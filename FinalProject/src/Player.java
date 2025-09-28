
public class Player {
	private String name;
	private String sprite = " o \n-+-\n | \n ^ ";
	private double xPos;
	private double yPos;
	private Inventory inventory;
	
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
}
