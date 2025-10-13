import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class NonPlayerCharacter extends Actor {
	private GraphicsContext gc;
	
	public NonPlayerCharacter(int width, int height) {
		super(width, height);
		gc = this.getGraphicsContext2D();
	}
	
	@Override
	public void DrawCharacter() {
		// TODO: replace this with drawImage once I have assets for such
		gc.setFill(Color.AQUA);
		gc.fillPolygon(new double[] {.1 * this.getWidth(), .5 * this.getWidth(), .9 * this.getWidth()}
					 , new double [] {.9 * this.getHeight(), .2 * this.getHeight(), .9 * this.getHeight()}, 3);
		
		gc.setFill(Color.TAN);
		gc.fillOval(.4 * this.getWidth(), .1 * this.getHeight(), .2 * this.getWidth(), .2 * this.getHeight());
	}
	
	public String GetDialogue(int situationValue) {
		if (situationValue == 0) {
			return "If I have 15 oranges in one hand and 36 oranges in the other, I have far too many oranges than is reasonable.";
		}
		if (situationValue == 1) {
			return "You're too far away, I can't hear you!";
		}
		return "Placeholder Text as an example of Dialogue";
	}
}
