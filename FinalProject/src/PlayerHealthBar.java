import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class PlayerHealthBar extends Canvas {
	Player player;
	GraphicsContext gc;
	
	public PlayerHealthBar(int width, int height, Player player) {
		super(width, height);
		this.player = player;
		gc = this.getGraphicsContext2D();
		displayHealth();
	}
	
	private void displayHealth() {
		gc.clearRect(0, 0, getWidth(), getHeight());
		
		gc.setFill(Color.BLACK);
		// This segment of the program creates the empty hearts
		for (int i = 0; i < player.getMaxHealth() / 4; i++) {
			gc.fillArc(10 + i * 40, 10, 15, 15, 0, 270, ArcType.ROUND);
			gc.fillArc(25 + i * 40, 10, 15, 15, 270, 270, ArcType.ROUND);
			gc.fillRect(15 + i * 40, 17.5, 18, 7.5);
			gc.fillPolygon(new double[] {25 + i * 40, 12 + i * 40, 38 + i * 40}, new double[] {40, 23, 23}, 3);
		}
		
		// This segment of the method fills all the hearts that are full
		gc.setFill(Color.RED);
		for (int i = 0; i < player.getCurrentHealth() / 4; i++) {
			gc.fillArc(10 + i * 40, 10, 15, 15, 0, 270, ArcType.ROUND);
			gc.fillArc(25 + i * 40, 10, 15, 15, 270, 270, ArcType.ROUND);
			gc.fillRect(15 + i * 40, 17.5, 18, 7.5);
			gc.fillPolygon(new double[] {25 + i * 40, 12 + i * 40, 38 + i * 40}, new double[] {40, 23, 23}, 3);
		}
		
		// This segment goes through the remaining health, if there is any, to partially fill the remaining hearts
		int heartFill = player.getCurrentHealth() % 4;
		int heartPos = player.getCurrentHealth() / 4;
		
		switch (heartFill) {
		case 3:
			gc.fillPolygon(new double[] {25 + heartPos * 40, 38 + heartPos * 40, 25 + heartPos * 40}, new double[] {40, 23, 23}, 3);
		case 2:
			gc.fillPolygon(new double[] {25 + heartPos * 40, 12 + heartPos * 40, 25 + heartPos * 40}, new double[] {40, 23, 23}, 3);
		case 1:
			gc.fillArc(10 + heartPos * 40, 10, 15, 15, 0, 270, ArcType.ROUND);
			gc.fillRect(15 + heartPos * 40, 17.5, 10, 7.5);
		default:
			return;
		}
	}
}
