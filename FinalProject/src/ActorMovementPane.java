import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeLineCap;
/*
 * This is meant to act as the layer for various actors, such as the player, enemies, and NPCs.
 * In order to have a separate class keep track of the Player, this accepts a Player as input
 */
public class ActorMovementPane extends Canvas {
	public ActorMovementPane(int width, int height, Player playerCharacter) {
		super(width, height);
		// TODO: Replace with a spritesheet once I learn how to make one
		// Image playerCharacterLabel = playerCharacter.getSprite();

		startAnimation();
	}
	
	private void startAnimation() {
		GraphicsContext gc = this.getGraphicsContext2D();
		
		new AnimationTimer() {
			
			int frame = 0;
			
			long lastUpdate = System.nanoTime();
			private final long DELAY = 500_000_000; // 500 milliseconds

			@Override
			public void handle(long now) {
				if (now - lastUpdate >= DELAY) {
					gc.clearRect(200, 200, 100, 120);
					
					// head
					gc.setFill(Color.TAN);
					gc.fillOval(240, 210, 20, 20);
					
					// hair
					gc.setFill(Color.BROWN);
					gc.fillArc(239, 208, 20, 20, 45, 135, ArcType.CHORD);
				
					// Left arm
					gc.setStroke(Color.TAN);
					gc.setLineCap(StrokeLineCap.ROUND);
					gc.setLineWidth(10);
					if (frame == 0 || frame == 2) {
						gc.strokeLine(250, 240, 250, 270);
					}
					else if (frame == 1) {
						gc.strokeLine(250, 240, 268, 264);
					}
					else if (frame == 3) {
						gc.strokeLine(250, 240, 232, 264);
					}
					
					// Torso
					gc.setFill(Color.DARKRED);
					gc.fillPolygon(new double[] {235, 265, 260, 240}, new double[] {230, 230, 270, 270}, 4);
					
					// Waist
					gc.setFill(Color.DARKBLUE);
					gc.fillArc(240, 260, 20, 20, 180, 180, ArcType.CHORD);
					
					
					
					// Left leg and Shoe
					gc.setStroke(Color.DARKBLUE);
					if (frame == 0) {
						gc.strokeLine(250, 275, 262, 289);
						gc.strokeLine(262, 289, 274, 305);
					}
					else if (frame == 1) {
						
					}
					else if (frame == 2) {
						
					}
					else if (frame == 3) {
						
					}
					
					// Right leg and Shoe
					gc.setStroke(Color.DARKBLUE);
					if (frame == 0) {
						
					}
					else if (frame == 1) {
						
					}
					else if (frame == 2) {
						
					}
					else if (frame == 3) {
						
					}
					
					// Right arm
					gc.setStroke(Color.TAN);
					gc.setLineCap(StrokeLineCap.ROUND);
					gc.setLineWidth(10);
					if (frame == 0 || frame == 2) {
						gc.strokeLine(250, 240, 250, 270);
					}
					else if (frame == 1) {
						gc.strokeLine(250, 240, 232, 264);
					}
					else if (frame == 3) {
						gc.strokeLine(250, 240, 268, 264);
					}
					
					frame++;
					if (frame >= 4) {
						frame = 0;
					}
					lastUpdate = now;
				}
			}
			
		}.start();
	}
}
