import javafx.scene.layout.StackPane;

public class GameWindowPane extends StackPane {
	public GameWindowPane() {
		Region region = new Region();
		getChildren().add(region);
		
		Player player = new Player();
		ActorMovementPane actorPane = new ActorMovementPane(player);
		getChildren().add(actorPane);
		
		UIPane uiPane = new UIPane();
		getChildren().add(uiPane);
	}
}
