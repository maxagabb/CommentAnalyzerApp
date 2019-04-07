package loginRegister;

import java.awt.BorderLayout;

import javax.swing.JFrame;

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
		
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setBounds(800, 400, 1200, 800);
		primaryStage.setTitle("Comment Analyzer");
		
		StackPane root = new StackPane();
		root.getChildren().add(new WelcomePage(frame,primaryStage));
		Scene scene = new Scene(root, 300, 250);
		
				
		primaryStage.setScene(scene);
		
		scene.getStylesheets().add
		 (JavaFXStart.class.getResource("myCSS.css").toExternalForm());
		
		primaryStage.show();
	}

}
