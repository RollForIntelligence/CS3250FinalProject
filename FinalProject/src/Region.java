import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Region extends Canvas {
	public Region(int width, int height) {
		super(width, height);
		
		GraphicsContext gc = this.getGraphicsContext2D();
		
		// Background color: grass
		gc.setFill(Color.DARKGREEN);
		gc.fillRect(0, 0, width, height);
		
		// brown path
		gc.setFill(Color.SADDLEBROWN);
		gc.fillRect(0, 300, width, 50);
	}
}
