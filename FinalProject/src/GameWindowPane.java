import java.util.ArrayList;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
/*
 * This class is the overall main window for the game. 
 * It contains one Region as the background, one ActorMovementPane for the movable characters, and a UIPane for the healthbar and Dialoguebox
 */
public class GameWindowPane extends StackPane {
	public GameWindowPane() {
		Region region = new Region(500, 500);
		getChildren().add(region);
		
		Player player = new Player(100, 125, "Placeholder");
		ArrayList<Actor> actors = new ArrayList<Actor>();
		NonPlayerCharacter npc = new NonPlayerCharacter(100, 100);
		actors.add(npc);
		npc.setLayoutX(300);
		npc.setxPos(100);
		npc.setLayoutY(200);
		npc.setyPos(0);
		
		ActorMovementPane actorPane = new ActorMovementPane(500, 500, player, actors);
		getChildren().add(actorPane);
		
		// TODO: determine why movement controls only work when a dialogueBox is open
		this.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.D) {
				actorPane.setXMovement(1);
			}
			else if (event.getCode() == KeyCode.A) {
				actorPane.setXMovement(-1);
			}
			else if (event.getCode() == KeyCode.W) {
				actorPane.setYMovement(-1);
			}
			else if (event.getCode() == KeyCode.S) {
				actorPane.setYMovement(1);
			}
		});
		
		this.setOnKeyReleased(event -> {
			if (event.getCode() == KeyCode.D) {
				actorPane.setXMovement(0);
			}
			else if (event.getCode() == KeyCode.A) {
				actorPane.setXMovement(0);
			}
			else if (event.getCode() == KeyCode.W) {
				actorPane.setYMovement(0);
			}
			else if (event.getCode() == KeyCode.S) {
				actorPane.setYMovement(0);
			}
		});
		
		UIPane uiPane = new UIPane(500, 500, player);
		getChildren().add(uiPane);
		
		this.setOnMouseClicked(event -> {
			if (Math.abs(event.getX() - npc.getLayoutX() - 50) < 50 && Math.abs(event.getY() - npc.getLayoutY() - 50) < 50) {
				if (GetDistance(player.getxPos(), player.getyPos(), npc.getxPos(), npc.getyPos()) < 100) {
					uiPane.OpenDialogue(npc.GetDialogue(0));
				}
				else {
					uiPane.OpenDialogue(npc.GetDialogue(1));
				}
			}
						
			if (event.getY() >= 400) {
				uiPane.CloseDialogue();
			}
			
			if (event.getY() <= 100) {
				if (event.getX() < 250) {
					player.setCurrentHealth(player.getCurrentHealth() - 1);
				}
				else {
					player.setCurrentHealth(player.getCurrentHealth() + 1);
				}
				uiPane.UpdateHealth();
			}
		});
	}
	
	private double GetDistance(double x1, double y1, double x2, double y2) {
		double distance = Math.hypot(x1 - x2, y1 - y2);
		return distance;
	}
}
