import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
/*
 * This class represents the box which spawns to display dialogue. 
 */
public class DialogueBox extends StackPane {
	private String displayText;
	private int indexDisplayed;
	
	private int displaySpeed = 3;
	
	private Label dialogueLabel;
	
	public DialogueBox(String dialogueText, double width, double height) {
		Canvas dialogueBoxBackground = new Canvas(width, height);
		
		GraphicsContext dbbgc = dialogueBoxBackground.getGraphicsContext2D();
		dbbgc.setFill(Color.WHITE);
		dbbgc.setStroke(Color.BISQUE);
		dbbgc.setLineWidth(5);
		
		dbbgc.fillRoundRect(20, 0, width-40, height, height / 4, height / 4);
		dbbgc.strokeRoundRect(20, 0, width-40, height, height / 4, height / 4);
		
		getChildren().add(dialogueBoxBackground);
		
		
		displayText = DialogueScrambler.Scramble(dialogueText);
		
		dialogueLabel = new Label("");
		indexDisplayed = 0;
		
		dialogueLabel.setWrapText(true);
		dialogueLabel.setAlignment(Pos.CENTER);
		dialogueLabel.setMaxWidth(width - 60);
		
		dialogueLabel.setStyle("-fx-font-family: serif; ");
		
		getChildren().add(dialogueLabel);
	}
	
	public boolean advanceDialogue() {
		boolean finished = false;
		
		if (indexDisplayed < displayText.length()) { // If there is still text to display
			indexDisplayed += displaySpeed; // Increase the amount of text displayed
			
			if (indexDisplayed >= displayText.length()) {
				indexDisplayed = displayText.length(); // If the index exceeds the length of the text, set the two as equal
			}
			
			dialogueLabel.setText(displayText.substring(0, indexDisplayed)); // Displays from the first character to the displayed index
		}
		
		if (indexDisplayed == displayText.length()) { // If there is no longer text to display
			finished = true;
		}
		
		return finished;
	}
	
	
}
