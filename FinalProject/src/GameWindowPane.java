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
		
		Player player = new Player("Placeholder");
		ActorMovementPane actorPane = new ActorMovementPane(500, 500, player);
		getChildren().add(actorPane);
		
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
	}
}
