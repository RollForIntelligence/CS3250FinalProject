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
	private Canvas dialogueBoxBackground;
	
	public DialogueBox(String dialogueText, double width, double height) {
		dialogueBoxBackground = new Canvas(width, height);
		
		GraphicsContext dbbgc = dialogueBoxBackground.getGraphicsContext2D();
		drawBackground(dbbgc);
		
		getChildren().add(dialogueBoxBackground);
		
		this.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> { 
			drawBackground(dbbgc);
		});
		
		displayText = DialogueScrambler.Scramble(dialogueText);
		
		dialogueLabel = new Label("");
		indexDisplayed = 0;
		
		dialogueLabel.setWrapText(true);
		dialogueLabel.setAlignment(Pos.CENTER);
		dialogueLabel.setMaxWidth(width - 60);
		
		dialogueLabel.setStyle("-fx-font-family: serif; "
				+ "-fx-padding: 0 20 0 30;");
		
		getChildren().add(dialogueLabel);
	}
	
	public void resizeScreen(double width, double height) {
		dialogueBoxBackground.setWidth(width);
		dialogueBoxBackground.setHeight(100);
		drawBackground(dialogueBoxBackground.getGraphicsContext2D());
	}
	
	private void drawBackground(GraphicsContext gc) {
		double width = this.getWidth();
		double height = this.getHeight();
		
		gc.clearRect(0, 0, width, height);
		
		gc.setFill(Color.WHITE);
		gc.setStroke(Color.BISQUE);
		gc.setLineWidth(5);
		
		gc.fillRoundRect(20, 0, width-40, height, height / 4, height / 4);
		gc.strokeRoundRect(20, 0, width-40, height, height / 4, height / 4);
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
