import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
/*
 * This class represents the box which spawns to display dialogue. 
 */
public class DialogueBox extends VBox {
	public DialogueBox(String dialogueText) {
		super(10);
		
		String displayText = DialogueScrambler.Scramble(dialogueText);
		
		Label dialogueLabel = new Label(displayText);
		dialogueLabel.setWrapText(true);
		this.setAlignment(Pos.CENTER);
		
		getChildren().add(dialogueLabel);
		
		this.setStyle("background-color: white");
	}
	
	public void closeDialogue() {
		;
	}
}
