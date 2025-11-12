import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
/*
 * This pane is meant to hold the player's UI, which currently displays a stand-in healthbar and a DialogueBox
 */
public class UIPane extends BorderPane {
	private DialogueBox dialogueBox;
	private PlayerHealthBar playerHealthBar;
	
	private boolean inventoryOpen = false;
	
	public UIPane(int width, int height, Player player) {
		this.playerHealthBar = new PlayerHealthBar(width - 20, 50, player);
		setTop(playerHealthBar);
		
		
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
			UIDisplayPane inventoryPane = new UIDisplayPane(player.getInventory(), this.getWidth(), this.getHeight());
			this.setCenter(inventoryPane);

			Pane spacingPane = new Pane();
			spacingPane.setPrefWidth((this.getWidth() - inventoryPane.getInventoryWidth()) / 2);
			this.setLeft(spacingPane);
			
			inventoryOpen = true;
		}
	}
	
	public void closeInventory() {
		if (inventoryOpen) {
			((UIDisplayPane)(this.getCenter())).closeInventory();
			this.setCenter(null);
			this.setLeft(null);
			
			inventoryOpen = false;
		}
	}
	
	public boolean inventoryOpen() {
		return inventoryOpen;
	}
}
