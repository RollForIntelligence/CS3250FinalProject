import javafx.scene.image.Image;

public abstract class Item {
	protected String name;
	protected int quantity;
	protected Image sprite;
	
	public Image getSprite() {
		return sprite;
	}
	
}
