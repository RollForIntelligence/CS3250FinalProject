import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
/*
 * This class represents the box which spawns to display dialogue. 
 * It will later be able to close.
 */
public class DialogueBox extends VBox {
	public DialogueBox(String dialogueText) {
		super(10);
		
		String displayText = DialogueScrambler.Scramble(dialogueText);
		
		Label dialogueLabel = new Label(displayText);
		Button closeDialogueButton = new Button("Done");
		this.setAlignment(Pos.CENTER);
		
		closeDialogueButton.setOnAction(event -> {
			this.closeDialogue();
		});
		
		getChildren().add(dialogueLabel);
		getChildren().add(closeDialogueButton);
	}
	
	public void closeDialogue() {
		
	}
}
