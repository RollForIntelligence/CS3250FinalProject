import javafx.scene.layout.AnchorPane;
/*
 * This pane is meant to hold the player's UI, which currently displays a stand-in healthbar and a DialogueBox
 */
public class UIPane extends AnchorPane {
	private DialogueBox dialogueBox;
	private PlayerHealthBar playerHealthBar;
	
	public UIPane(int width, int height, Player player) {
		this.playerHealthBar = new PlayerHealthBar(width - 20, 50, player);
		setTopAnchor(playerHealthBar, 20.0);
		setLeftAnchor(playerHealthBar, 20.0);
		
		
		
		getChildren().add(playerHealthBar);
	}
	
	public void OpenDialogue(String dialogueText) {
		if (this.getChildren().contains(dialogueBox)) {
			return;
		}
		this.dialogueBox = new DialogueBox(dialogueText, this.getWidth(), 100);
		setBottomAnchor(dialogueBox, 20.0);
		setLeftAnchor(dialogueBox, 20.0);
		setRightAnchor(dialogueBox, 20.0);
		getChildren().add(dialogueBox);
	}
	
	public void GameOver() {
		UIDisplayPane gameOverScreen = new UIDisplayPane("Game Over", this.getWidth(), this.getHeight());
		getChildren().remove(playerHealthBar);
		getChildren().add(gameOverScreen);
		setBottomAnchor(gameOverScreen, 0.0);
		setTopAnchor(gameOverScreen, 0.0);
		setRightAnchor(gameOverScreen, 0.0);
		setLeftAnchor(gameOverScreen, 0.0);
	}
	
	public void CloseDialogue() {
		getChildren().remove(dialogueBox);
	}
	
	public void UpdateHealth() {
		playerHealthBar.DisplayHealth();
	}
}
