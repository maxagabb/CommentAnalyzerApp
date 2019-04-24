package loginRegister;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
		});
		Button register = new Button("register");
		register.setOnAction(e ->{
			StackPane root = new StackPane();
			root.getChildren().add(new RegisterPage(primaryStage));
			root.setAlignment(Pos.TOP_CENTER);
    		this.primaryStage.getScene().setRoot(root);
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
