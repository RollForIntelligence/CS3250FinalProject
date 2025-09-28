import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;

public class ActorMovementPane extends AnchorPane {
	public ActorMovementPane(Player playerCharacter) {
		// TODO: Replace with Image once we learn how to do so
		Label playerCharacterLabel = new Label(playerCharacter.getSprite());
		getChildren().add(playerCharacterLabel);
		
		// TODO: Implement better positioning than this once the playerCharacter is an Image
		playerCharacterLabel.setTextAlignment(TextAlignment.CENTER);
		setBottomAnchor(playerCharacterLabel, 200.0);
		setTopAnchor(playerCharacterLabel, 200.0);
		setLeftAnchor(playerCharacterLabel, 200.0);
		setRightAnchor(playerCharacterLabel, 200.0);
	}
}
