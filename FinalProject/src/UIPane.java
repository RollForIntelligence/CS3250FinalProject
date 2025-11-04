import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
/*
 * This pane is meant to hold the player's UI, which currently displays a stand-in healthbar and a DialogueBox
 */
public class UIPane extends BorderPane {
	private DialogueBox dialogueBox;
	private PlayerHealthBar playerHealthBar;
	
	public UIPane(int width, int height, Player player) {
		this.playerHealthBar = new PlayerHealthBar(width - 20, 50, player);
		setTop(playerHealthBar);
		
		this.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
//			System.out.println(newValue);
			
		});
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
}
