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
	private Region region;
	
	private AnimationTimer timer;
	
	private int frameOffset = 0;
	
	public ActorMovementPane(int width, int height, Player playerCharacter, ArrayList<Actor> actors) {
		//super(width, height);
		this.player = playerCharacter;
		
		this.actors = actors;
		
		this.getChildren().add(this.player);
		this.player.setLayoutX((width / 2) - (this.player.getWidth() / 2));
		this.player.setLayoutY((height / 2) - (this.player.getHeight() / 2));
		
		for (Actor actor : actors) {
			this.getChildren().add(actor);
		}

		startAnimation();
	}
	
	public void resizeScreen(double width, double height) {
		this.setWidth(width);
		this.setHeight(height);
		this.player.setLayoutX((width / 2) - (this.player.getWidth() / 2));
		this.player.setLayoutY((height / 2) - (this.player.getHeight() / 2));
	}
	
	public void setRegion(Region region) {
		this.region = region;
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
		player.move((-25 * xMovement / player.PLAYER_SPEED), (-25 * yMovement / player.PLAYER_SPEED));
	}
	
	public void Kill(Actor actor) {
		actors.remove(actor);
		getChildren().remove(actor);
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
					movePlayer();
					if (xMovement > 0) {
						frameOffset = 0;
					}
					else if (xMovement < 0) {
						frameOffset = 4;
					}
					else if (yMovement > 0) {
						frameOffset = 12;
					}
					else if (yMovement < 0) {
						frameOffset = 8;
					}
					int currentFrame = frame + frameOffset;
					player.DrawCharacter(currentFrame);
					
					showOtherActors();
					
					for (Actor actor : actors) {
						if (actor instanceof EnemyCharacter) {
							EnemyCharacter enemy = (EnemyCharacter) actor;
							if (enemy.getBoundsInParent().intersects(player.getBoundsInParent())) {
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
					
					uiPane.updateDialogue();
					
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
	
	private void movePlayer() {
		player.move(xMovement, yMovement);
		if (player.getxPos() < 200 || player.getxPos() > 2300 || player.getyPos() < 200 || player.getyPos() > 2300) {
			player.unmove(xMovement, yMovement);
			System.out.println("not moving");
		}
		region.move();
	}
	
	private void showOtherActors() {
		for (Actor actor : actors) {
			actor.setLayoutX(Actor.GetXDistance(player, actor) + player.getLayoutX());
			actor.setLayoutY(Actor.GetYDistance(player, actor) + player.getLayoutY());
			actor.DrawCharacter();
		}
	}
}
