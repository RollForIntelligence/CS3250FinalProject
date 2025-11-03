import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;

public class UIDisplayPane extends StackPane {
	
	
	public UIDisplayPane(Inventory inventory, double width, double height) {
		// TODO: set up a GridPane of tiles for the items
		
		// Ensures the tiles are squares that fit within the bounds of the screen
		double tileSize = Math.min((width >= 400 ? 80 : width / 5), (height >= 320 ? 80 : height / 4));
		
		// TODO: display the items from the inventory in the grid
		// TODO: set up an animationTimer that runs while the UIDisplayPane is open
		// TODO: allow the user to appear to move a copy of a tile around with their mouse
		// TODO: set up a way to stop the timer when the inventory is closed
	}
	
	public UIDisplayPane(String message, double width, double height) {
		Label messageLabel = new Label(message);
		
		messageLabel.setStyle("-fx-background-color: black; "
				+ "-fx-text-fill: red; "
				+ "-fx-label-padding: 0 0 0 " + (width - 220) / 2 + "; " // 220 is the width of the "Game Over" message
				+ "-fx-font-weight: bold; "
				+ "-fx-font-family: serif; "
				+ "-fx-font-size: 40"
				);

		// displays the message at the center of the screen in large block text
		messageLabel.setMinWidth(width);
		messageLabel.setMinHeight(height);
		messageLabel.setTextAlignment(TextAlignment.CENTER);
		this.getChildren().add(messageLabel);
	}
}
