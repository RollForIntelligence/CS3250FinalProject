import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		System.out.println("Welcome to my final project");
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(new GameWindowPane(), 500, 500);
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
	}

}
