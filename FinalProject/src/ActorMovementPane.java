import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeLineCap;
/*
 * This is meant to act as the layer for various actors, such as the player, enemies, and NPCs.
 * In order to have a separate class keep track of the Player, this accepts a Player as input
 */
public class ActorMovementPane extends Pane {
	private Player player;
	private int xMovement = 0;
	private int yMovement = 0;
	private ArrayList<Actor> actors;
	
	private UIPane uiPane;
	
	private AnimationTimer timer;
	
	public ActorMovementPane(int width, int height, Player playerCharacter, ArrayList<Actor> actors) {
		//super(width, height);
		this.player = playerCharacter;
		
		this.actors = actors;
		
		this.getChildren().add(this.player);
		this.player.setLayoutX(200 + this.player.getxPos());
		this.player.setLayoutY(200 + this.player.getyPos());
		
		for (Actor actor : actors) {
			this.getChildren().add(actor);
		}
		
		
		// TODO: Replace with a spritesheet once I learn how to make one
		// Image playerCharacterLabel = playerCharacter.getSprite();

		startAnimation();
	}
	
	public void setUIPane(UIPane pane) {
		uiPane = pane;
	}
	
	public void setXMovement(int movement) {
		this.xMovement = movement;
	}
	
	public void setYMovement(int movement) {
		this.yMovement = movement;
	}
	
	public void BounceBack() {
		player.setxPos(player.getxPos() - (25 * xMovement));
		player.setyPos(player.getyPos() - (25 * yMovement));
		
		player.setLayoutX(200 + player.getxPos());
		player.setLayoutY(200 + player.getyPos());
	}
	
	public void Kill(Actor actor) {
		actors.remove(actor);
		getChildren().remove(actor);
	}
	
	private double GetDistance(double x1, double y1, double x2, double y2) {
		double distance = Math.hypot(x1 - x2, y1 - y2);
		return distance;
	}
	
	private void startAnimation() {
//		GraphicsContext gc = this.getGraphicsContext2D();
		
		timer = new AnimationTimer() {
			
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
					
					player.setLayoutX(200 + player.getxPos());
					player.setLayoutY(200 + player.getyPos());
					
					player.DrawCharacter(frame);
					for (Actor actor : actors) {
						actor.DrawCharacter();
					}
					
					for (Actor actor : actors) {
						if (actor instanceof EnemyCharacter) {
							EnemyCharacter enemy = (EnemyCharacter) actor;
							if (GetDistance(player.getxPos(), player.getyPos(), enemy.getxPos(), enemy.getyPos()) < 50) {
								if (player.TakeDamage(enemy.GetDamageDealt())) {
									uiPane.GameOver();
									this.stop();
									break;
								}
								BounceBack();
								uiPane.UpdateHealth();
							}
						}
					}
					
					if (now - lastAnimationUpdate >= ANIMATION_DELAY) {
						frame++;
						frame = frame % 4;
						lastAnimationUpdate = now;
					}
					lastUpdate = now;
				}
			}
			
		};
		
		timer.start();
	}
}
