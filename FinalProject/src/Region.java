import java.io.InputStream;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Region extends Canvas {
	private Player player;
	private Image backgroundImage;
	
	public Region(int width, int height, Player player) {
		super(width, height);
		
		this.player = player;
		InputStream inputStream = getClass().getResourceAsStream("images/BackgroundConverted.png");
		backgroundImage = new Image(inputStream);
		GraphicsContext gc = this.getGraphicsContext2D();
		
		// : use 20_Retro_Convert to create a background for the region
		
		
		
		
		render();
	}
	
	public void resize(double width, double height) {
		this.setWidth(width);
		this.setHeight(height);
		render();
	}
	
	private void render() {
		GraphicsContext gc = this.getGraphicsContext2D();
		
		// : uncomment this when I have a background image
		gc.save();
    	gc.translate(this.getWidth() / 2, this.getHeight() / 2);
    	gc.translate(-player.getxPos(), -player.getyPos());
    	
    	gc.setImageSmoothing(false);
    	gc.drawImage(backgroundImage, 0, 0, 2500, 2500); // : change this size to the size of the background image
    	gc.restore();
	}
	
	public void move() {
		render();
	}
}
