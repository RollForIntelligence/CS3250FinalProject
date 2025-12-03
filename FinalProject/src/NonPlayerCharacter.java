import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class NonPlayerCharacter extends Actor {
	private static final ArrayList<Color> UNIFORM_COLORS = new ArrayList<Color>();
	
	static {
		UNIFORM_COLORS.add(Color.AQUA);
		UNIFORM_COLORS.add(Color.BLACK);
		UNIFORM_COLORS.add(Color.GREEN);
		UNIFORM_COLORS.add(Color.BLUE);
		UNIFORM_COLORS.add(Color.WHITE);
	}
	
	private GraphicsContext gc;
	private ArrayList<String> dialogueOptions;
	private Color uniformColor;
	
	public NonPlayerCharacter(int width, int height) {
		super(width, height);
		gc = this.getGraphicsContext2D();
		dialogueOptions = new ArrayList<>();
		uniformColor = UNIFORM_COLORS.get((int)(Math.random() * 5));
	}
	
	@Override
	public void DrawCharacter() {
		// TODO: replace this with drawImage once I have assets for such
		gc.setFill(uniformColor);
		gc.fillPolygon(new double[] {.1 * this.getWidth(), .5 * this.getWidth(), .9 * this.getWidth()}
					 , new double [] {.9 * this.getHeight(), .2 * this.getHeight(), .9 * this.getHeight()}, 3);
		
		gc.setFill(Color.TAN);
		gc.fillOval(.4 * this.getWidth(), .1 * this.getHeight(), .2 * this.getWidth(), .2 * this.getHeight());
	}
	
	public void AddDialogue(String option) {
		dialogueOptions.add(option);
	}
	
	public String GetDialogue(int situationValue) {
		if (dialogueOptions.size() > situationValue) {
			return dialogueOptions.get(situationValue);
		}
		else {
			return "Placeholder Text as an example of Dialogue";
		}
	}
}
