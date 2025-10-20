import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new GameWindowScene(700, 500);
		primaryStage.setScene(scene);
		
		
		primaryStage.show();
		
	}

}
