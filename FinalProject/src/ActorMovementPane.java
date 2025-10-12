import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeLineCap;
/*
 * This is meant to act as the layer for various actors, such as the player, enemies, and NPCs.
 * In order to have a separate class keep track of the Player, this accepts a Player as input
 */
public class ActorMovementPane extends Canvas {
	private Player player;
	private int xMovement = 0;
	private int yMovement = 0;
	
	public ActorMovementPane(int width, int height, Player playerCharacter) {
		super(width, height);
		this.player = playerCharacter;
		// TODO: Replace with a spritesheet once I learn how to make one
		// Image playerCharacterLabel = playerCharacter.getSprite();

		startAnimation();
	}
	
	public void setXMovement(int movement) {
		this.xMovement = movement;
	}
	
	public void setYMovement(int movement) {
		this.yMovement = movement;
	}
	
	private void startAnimation() {
		GraphicsContext gc = this.getGraphicsContext2D();
		
		new AnimationTimer() {
			
			int frame = 0;
			
			long lastUpdate = System.nanoTime();
			long lastAnimationUpdate = System.nanoTime();
			private final long DELAY = 30_000_000; // 30 milliseconds
			private final long ANIMATION_DELAY = 500_000_000;

			@Override
			public void handle(long now) {
				if (now - lastUpdate >= DELAY) {
					player.setxPos(player.getxPos() + xMovement);
					player.setyPos(player.getyPos() + yMovement);
					
					gc.clearRect(200 + player.getxPos(), 200 + player.getyPos(), 100, 125);
					
					// TODO: create a spritesheet to replace all of this drawing;
					// head
					gc.setFill(Color.TAN);
					gc.fillOval(240 + player.getxPos(), 210 + player.getyPos(), 20, 20);
					
					// hair
					gc.setFill(Color.BROWN);
					gc.fillArc(239 + player.getxPos(), 208 + player.getyPos(), 20, 20, 45, 135, ArcType.CHORD);
				
					// Left arm
					gc.setStroke(Color.TAN);
					gc.setLineCap(StrokeLineCap.ROUND);
					gc.setLineWidth(10);
					if (frame == 1 || frame == 3) {
						gc.strokeLine(250 + player.getxPos(), 240 + player.getyPos(), 250 + player.getxPos(), 270 + player.getyPos());
					}
					else if (frame == 0) {
						gc.strokeLine(250 + player.getxPos(), 240 + player.getyPos(), 268 + player.getxPos(), 264 + player.getyPos());
					}
					else if (frame == 2) {
						gc.strokeLine(250 + player.getxPos(), 240 + player.getyPos(), 232 + player.getxPos(), 264 + player.getyPos());
					}
					
					// Torso
					gc.setFill(Color.DARKRED);
					gc.fillPolygon(new double[] {235 + player.getxPos(), 265 + player.getxPos(), 260 + player.getxPos(), 240 + player.getxPos()}
								 , new double[] {230 + player.getyPos(), 230 + player.getyPos(), 270 + player.getyPos(), 270 + player.getyPos()}, 4);
					
					// Waist
					gc.setFill(Color.DARKBLUE);
					gc.fillArc(240 + player.getxPos(), 260 + player.getyPos(), 20, 20, 180, 180, ArcType.CHORD);
					
					
					
					// Left leg and Shoe
					gc.setStroke(Color.DARKBLUE);
					gc.setFill(Color.BLACK);
					if (frame == 0) {
						gc.strokeLine(250 + player.getxPos(), 275 + player.getyPos(), 274 + player.getxPos(), 307 + player.getyPos());
						gc.fillPolygon(new double[] {277.5 + player.getxPos(), 270.5 + player.getxPos(), 276 + player.getxPos(), 288 + player.getxPos()}
						, new double[] {305 + player.getyPos(), 310 + player.getyPos(), 318 + player.getyPos(), 308 + player.getyPos()}, 4);
					}
					else if (frame == 1) {
						gc.strokeLine(250 + player.getxPos(), 275 + player.getyPos(), 250 + player.getxPos(), 315 + player.getyPos());
						gc.fillPolygon(new double[] {245 + player.getxPos(), 245 + player.getxPos(), 260 + player.getxPos(), 255 + player.getxPos()}
						, new double[] {310 + player.getyPos(), 320 + player.getyPos(), 320 + player.getyPos(), 310 + player.getyPos()}, 4);
					}
					else if (frame == 2) {
						gc.strokeLine(250 + player.getxPos(), 275 + player.getyPos(), 226 + player.getxPos(), 307 + player.getyPos());
						gc.fillPolygon(new double[] {229.5 + player.getxPos(), 221 + player.getxPos(), 215 + player.getxPos(), 230 + player.getxPos()}
						, new double[] {310 + player.getyPos(), 305 + player.getyPos(), 314 + player.getyPos(), 320 + player.getyPos()}, 4);
					}
					else if (frame == 3) {
						gc.strokeLine(250 + player.getxPos(), 275 + player.getyPos(), 250 + player.getxPos(), 315 + player.getyPos());
						gc.fillPolygon(new double[] {245 + player.getxPos(), 245 + player.getxPos(), 260 + player.getxPos(), 255 + player.getxPos()}
						, new double[] {310 + player.getyPos(), 320 + player.getyPos(), 320 + player.getyPos(), 310 + player.getyPos()}, 4);
					}
					
					// Right leg and Shoe
					gc.setStroke(Color.DARKBLUE);
					if (frame == 0) {
						gc.strokeLine(250 + player.getxPos(), 275 + player.getyPos(), 226 + player.getxPos(), 307 + player.getyPos());
						gc.fillPolygon(new double[] {229.5 + player.getxPos(), 221 + player.getxPos(), 215 + player.getxPos(), 230 + player.getxPos()}
						, new double[] {310 + player.getyPos(), 305 + player.getyPos(), 314 + player.getyPos(), 320 + player.getyPos()}, 4);
					}
					else if (frame == 1) {
						gc.strokeLine(250 + player.getxPos(), 275 + player.getyPos(), 250 + player.getxPos(), 315 + player.getyPos());
						gc.fillPolygon(new double[] {245 + player.getxPos(), 245 + player.getxPos(), 260 + player.getxPos(), 255 + player.getxPos()}
						, new double[] {310 + player.getyPos(), 320 + player.getyPos(), 320 + player.getyPos(), 310 + player.getyPos()}, 4);
					}
					else if (frame == 2) {
						gc.strokeLine(250 + player.getxPos(), 275 + player.getyPos(), 274 + player.getxPos(), 307 + player.getyPos());
						gc.fillPolygon(new double[] {277.5 + player.getxPos(), 270.5 + player.getxPos(), 276 + player.getxPos(), 288 + player.getxPos()}
						, new double[] {305 + player.getyPos(), 310 + player.getyPos(), 318 + player.getyPos(), 308 + player.getyPos()}, 4);
					}
					else if (frame == 3) {
						gc.strokeLine(250 + player.getxPos(), 275 + player.getyPos(), 250 + player.getxPos(), 315 + player.getyPos());
						gc.fillPolygon(new double[] {245 + player.getxPos(), 245 + player.getxPos(), 260 + player.getxPos(), 255 + player.getxPos()}
						, new double[] {310 + player.getyPos(), 320 + player.getyPos(), 320 + player.getyPos(), 310 + player.getyPos()}, 4);
					}
					
					// Right arm
					gc.setStroke(Color.TAN);
					gc.setLineCap(StrokeLineCap.ROUND);
					gc.setLineWidth(10);
					if (frame == 1 || frame == 3) {
						gc.strokeLine(250 + player.getxPos(), 240 + player.getyPos(), 250 + player.getxPos(), 270 + player.getyPos());
					}
					else if (frame == 0) {
						gc.strokeLine(250 + player.getxPos(), 240 + player.getyPos(), 232 + player.getxPos(), 264 + player.getyPos());
					}
					else if (frame == 2) {
						gc.strokeLine(250 + player.getxPos(), 240 + player.getyPos(), 268 + player.getxPos(), 264 + player.getyPos());
					}
					
					if (now - lastAnimationUpdate >= ANIMATION_DELAY) {
						frame++;
						if (frame >= 4) {
							frame = 0;
						}
						lastAnimationUpdate = now;
					}
					lastUpdate = now;
				}
			}
			
		}.start();
	}
}
