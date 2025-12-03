import java.io.InputStream;

import javafx.scene.image.Image;

public class Sword extends Weapon {
	public Sword(String name, int damageValue) {
		this.name = name;
		this.quantity = 1;
		this.damageValue = damageValue;
		this.range = 100;
		InputStream inputStream = getClass().getResourceAsStream("images/ConvertedSword.png");
		this.sprite = new Image(inputStream);
	}
}
