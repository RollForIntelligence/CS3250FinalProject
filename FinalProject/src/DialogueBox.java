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
	public DialogueBox(String dialogueText, double width, double height) {
		
		Canvas dialogueBoxBackground = new Canvas(width, height);
		
		GraphicsContext dbbgc = dialogueBoxBackground.getGraphicsContext2D();
		dbbgc.setFill(Color.WHITE);
		dbbgc.setStroke(Color.BISQUE);
		dbbgc.setLineWidth(5);
		
		dbbgc.fillRoundRect(20, 0, width-40, height, height / 4, height / 4);
		dbbgc.strokeRoundRect(20, 0, width-40, height, height / 4, height / 4);
		
		getChildren().add(dialogueBoxBackground);
		
		
		String displayText = DialogueScrambler.Scramble(dialogueText);
		
		Label dialogueLabel = new Label(displayText);
		dialogueLabel.setWrapText(true);
		dialogueLabel.setAlignment(Pos.CENTER);
		dialogueLabel.setMaxWidth(width - 60);
		
		dialogueLabel.setStyle("-fx-font-family: serif; ");
		
		getChildren().add(dialogueLabel);
	}
	
	
}
