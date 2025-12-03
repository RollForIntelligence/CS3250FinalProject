import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
/*
 * This pane is meant to hold the player's UI, which currently displays a stand-in healthbar and a DialogueBox
 */
public class UIPane extends BorderPane {
	private DialogueBox dialogueBox = null;
	private PlayerHealthBar playerHealthBar = null;
	private UIDisplayPane inventoryPane = null;
	
	public UIPane(int width, int height, Player player) {
		this.playerHealthBar = new PlayerHealthBar(width - 20, 50, player);
		setTop(playerHealthBar);
	}
	
	// Implement UIPane.resize to use PlayerHealthBar.resize and DialogueBox.resize
	public void resizeScreen(double width, double height) {
		if (playerHealthBar != null) {
			playerHealthBar.resizeScreen(width, height);
		}
		if (dialogueBox != null) {
			dialogueBox.resizeScreen(width, height);
		}
		if (inventoryPane != null) {
			updateInventorySize(width, height);
		}
	}
	
	private void updateInventorySize(double width, double height) {
		inventoryPane.resizeScreen(width, height);
		Pane spacingPane;
		if (this.getLeft() != null) {
			spacingPane = (Pane) this.getLeft();
		}
		else {
			spacingPane = new Pane();
		}
		
		if (width <= inventoryPane.getInventoryWidth() + 5) {
			this.setLeft(null);
		}
		else {
			spacingPane.setPrefWidth((width - inventoryPane.getInventoryWidth()) / 2);
			this.setLeft(spacingPane);
		}
	}

	public void OpenDialogue(String dialogueText) {
		if (this.getBottom() instanceof DialogueBox) {
			return;
		}
		this.dialogueBox = new DialogueBox(dialogueText, this.getWidth(), 100);
		this.setBottom(dialogueBox);
	}
	
	public void GameOver() {
		UIDisplayPane gameOverScreen = new UIDisplayPane("Game Over", this.getWidth(), this.getHeight());
		this.setTop(null);
		this.setCenter(gameOverScreen);
	}
	
	public void CloseDialogue() {
		this.setBottom(null);
	}
	
	public void UpdateHealth() {
		playerHealthBar.DisplayHealth();
	}
	
	public boolean updateDialogue() {
		if (this.getChildren().contains(dialogueBox)) {
			return dialogueBox.advanceDialogue();
		}
		return true;
	}
	
	public void openInventory(Player player) {
		if (this.getCenter() == null) {
			inventoryPane = new UIDisplayPane(player.getInventory(), this.getWidth(), this.getHeight());
			this.setCenter(inventoryPane);

			Pane spacingPane = new Pane();
			spacingPane.setPrefWidth((this.getWidth() - inventoryPane.getInventoryWidth()) / 2);
			this.setLeft(spacingPane);
		}
	}
	
	public void closeInventory() {
		
		if (inventoryPane != null) {
			((UIDisplayPane)(this.getCenter())).closeInventory();
			this.setCenter(null);
			this.setLeft(null);
			
			inventoryPane = null;
		}
	}
	
	public boolean inventoryOpen() {
		return inventoryPane != null;
	}
}
