import java.io.InputStream;

import javafx.scene.image.Image;

public class Spear extends Weapon {
	public Spear(String name, int damageValue) {
		this.name = name;
		this.quantity = 1;
		this.damageValue = damageValue;
		this.range = 150;
		InputStream inputStream = getClass().getResourceAsStream("images/ConvertedSpear.png");
		this.sprite = new Image(inputStream);
	}
}
