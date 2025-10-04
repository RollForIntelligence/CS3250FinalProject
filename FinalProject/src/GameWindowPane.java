import javafx.scene.layout.StackPane;
/*
 * This class is the overall main window for the game. 
 * It contains one Region as the background, one ActorMovementPane for the movable characters, and a UIPane for the healthbar and Dialoguebox
 */
public class GameWindowPane extends StackPane {
	public GameWindowPane() {
		Region region = new Region(500, 500);
		getChildren().add(region);
		
		Player player = new Player();
		ActorMovementPane actorPane = new ActorMovementPane(500, 500, player);
		getChildren().add(actorPane);
		
		UIPane uiPane = new UIPane(500, 500, player);
		getChildren().add(uiPane);
	}
}
