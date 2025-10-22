import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

public class UIDisplayPane extends GridPane {
	
	
	public UIDisplayPane(Inventory inventory) {
		// TODO: set up a grid of tiles for the items
	}
	
	public UIDisplayPane(String message, double width, double height) {
		// TODO: Find out why the message cuts off when the ratio of width to height is too high
		Label messageLabel = new Label(message);
		
		messageLabel.setStyle("-fx-background-color: black; "
				+ "-fx-text-fill: red; "
				+ "-fx-label-padding: " + (width - 220) / 2 + ", 0, 0, 0; " // 220 is the width of the "Game Over" message
				+ "-fx-font-weight: bold; "
				+ "-fx-font-family: serif; "
				+ "-fx-font-size: 40"
				);

		// displays the message at the center of the screen in large block text
		messageLabel.setMinWidth(width);
		messageLabel.setMinHeight(height);
		messageLabel.setTextAlignment(TextAlignment.CENTER);
		this.add(messageLabel, 0, 0);
	}
}
