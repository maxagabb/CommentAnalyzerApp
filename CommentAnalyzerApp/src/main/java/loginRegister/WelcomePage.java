package loginRegister;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import byVideoFrontEnd.MainPage;
import byVideoFrontEnd.TaskBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WelcomePage extends BorderPane{
	/**
	 * @invariant this != null
	 * @precondition frame != null
	 * @postcondition this != null
	 * @param frame
	 */
	public WelcomePage(Stage primaryStage) {
		this.primaryStage = primaryStage;
		setPage();
	}
	/*
	 * sets buttons redirecting to login and 
	 * register pages
	 * @precondition this != null
	 * @postcondition frame = null
	 */
	private void setPage() {
		Button login = new Button("login");
		login.setOnAction(e ->{
			StackPane root = new StackPane();
			root.getChildren().add(new LoginPage(primaryStage));
			root.setAlignment(Pos.TOP_CENTER);
    		this.primaryStage.getScene().setRoot(root);
			//primaryStage.setWidth(1200);primaryStage.setHeight(800);
			//primaryStage.centerOnScreen();
			/*StackPane root = new StackPane();
			root.getChildren().add(new LoginPage(primaryStage));
			
			Scene scene = new Scene(root, primaryStage.getWidth(), 
					primaryStage.getHeight());
			scene.getStylesheets().add
			 (JavaFXStart.class.getResource("myCSS.css").toExternalForm());
			
			primaryStage.setScene(scene);*/

		});
		Button register = new Button("register");
		register.setOnAction(e ->{
			StackPane root = new StackPane();
			root.getChildren().add(new RegisterPage(primaryStage));
			root.setAlignment(Pos.TOP_CENTER);
    		this.primaryStage.getScene().setRoot(root);
			/*
			StackPane root = new StackPane();
			root.getChildren().add(new RegisterPage(primaryStage));
			
			Scene scene = new Scene(root, primaryStage.getWidth(), 
					primaryStage.getHeight());
			scene.getStylesheets().add
			 (JavaFXStart.class.getResource("myCSS.css").toExternalForm());
			
			primaryStage.setScene(scene);*/
		});
		
		Text welcome = new Text("Welcome Screen");
		welcome.setId("welcome-text");
		HBox titleBox = new HBox();
		titleBox.getChildren().add(welcome);
		this.setTop(titleBox);
		titleBox.setAlignment(Pos.CENTER);
		
		VBox buttonBox = new VBox();
		buttonBox.getStyleClass().add("raisedBorder");
		buttonBox.setPadding(new Insets(50));
		buttonBox.setSpacing(30);
		login.setMaxWidth(100);
		register.setMaxWidth(100);
		buttonBox.getChildren().add(login);
		buttonBox.getChildren().add(register);
		//buttonBox.setStyle(cssLayout);
		GridPane  buttonBox2 = new GridPane ();
		buttonBox2.getChildren().add(buttonBox);
		this.setCenter(buttonBox2);
		buttonBox2.setAlignment(Pos.CENTER);
	}
	private Stage primaryStage;
}
