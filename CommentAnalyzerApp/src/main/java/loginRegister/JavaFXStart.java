package loginRegister;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavaFXStart extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Comment Analyzer");
		
		StackPane root = new StackPane();
		root.getChildren().add(new WelcomePage(primaryStage));
		Scene scene = new Scene(root, 300, 500);
				
		primaryStage.setScene(scene);
		
		scene.getStylesheets().add
		 (JavaFXStart.class.getResource("myCSS.css").toExternalForm());
		
		primaryStage.show();
	}

}
