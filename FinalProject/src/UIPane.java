import javafx.scene.layout.AnchorPane;
/*
 * This pane is meant to hold the player's UI, which currently displays a stand-in healthbar and a DialogueBox
 */
public class UIPane extends AnchorPane {
	private DialogueBox dialogueBox;
	private PlayerHealthBar playerHealthBar;
	
	public UIPane(int width, int height, Player player) {
//		DialogueBox dialogueBox = new DialogueBox("Example dialogue");
//		setBottomAnchor(dialogueBox, 20.0);
//		setLeftAnchor(dialogueBox, 20.0);
//		setRightAnchor(dialogueBox, 20.0);
		
		this.playerHealthBar = new PlayerHealthBar(width, 50, player);
		setTopAnchor(playerHealthBar, 20.0);
		setLeftAnchor(playerHealthBar, 20.0);
		
		
		
		getChildren().add(playerHealthBar);
	}
	
	public void OpenDialogue(String dialogueText) {
		if (this.getChildren().contains(dialogueBox)) {
			return;
		}
		this.dialogueBox = new DialogueBox(dialogueText);
		setBottomAnchor(dialogueBox, 20.0);
		setLeftAnchor(dialogueBox, 20.0);
		setRightAnchor(dialogueBox, 20.0);
		getChildren().add(dialogueBox);
	}
	
	public void CloseDialogue() {
		getChildren().remove(dialogueBox);
	}
	
	public void UpdateHealth() {
		playerHealthBar.DisplayHealth();
	}
}
