import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Region extends Canvas {
	public Region(int width, int height) {
		super(width, height);
		
		GraphicsContext gc = this.getGraphicsContext2D();
		
		// TODO: use 20_Retro_Convert to create a background for the region
		// TODO: place the background here
		// TODO: allow this pane to change size with the window
		// Background color: grass
		gc.setFill(Color.DARKGREEN);
		gc.fillRect(0, 0, width, height);
		
		// brown path
		gc.setFill(Color.SADDLEBROWN);
		gc.fillRect(0, 300, width, 50);
	}
}
