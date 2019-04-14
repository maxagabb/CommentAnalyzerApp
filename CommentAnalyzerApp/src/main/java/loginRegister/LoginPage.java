package loginRegister;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import byVideoFrontEnd.MainPage;
import byVideoFrontEnd.TaskBar;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginPage extends InputPage{

	/**
	 * concrete child of InputPage,
	 * implements nextPage and setPageName
	 * @param frame
	 * @invariant this != null
	 */
	public LoginPage(Stage primaryStage) {
		super(primaryStage);
	}

	@Override
	/**
	 * if user exists, dispose current
	 * frame and setup the main page
	 * @precondition list != null
	 * @postcondition this.frame = null
	 */
	protected void nextPage(UserList list) {
		ArrayList<String> input = new ArrayList<String>();
		input.add(inputName);
		input.add(inputPassword);
		if(list.validate(input)) {
			GridPane grid = new GridPane();
    		grid.getChildren().add(new MainPage(stage, new TaskBar(stage)));
    		grid.setAlignment(Pos.TOP_CENTER);
    		this.stage.getScene().setRoot(grid);
			
			/*
			StackPane root = new StackPane(new MainPage(stage, new TaskBar(stage)));
			ScrollPane pane = new ScrollPane(root);
			GridPane grid = new GridPane();

			root.minWidthProperty().bind(Bindings.createDoubleBinding(() -> 
			pane.getViewportBounds().getWidth(), pane.viewportBoundsProperty()));
			grid.getChildren().add(pane);

			Scene scene = new Scene(root, 800, 
					stage.getHeight());
			
			grid.setAlignment(Pos.TOP_CENTER);

			scene.getStylesheets().add
			(JavaFXStart.class.getResource("myCSS.css").toExternalForm());
			stage.setScene(scene);*/
			
		}

		else
			JOptionPane.showMessageDialog(frame, "Login failed");
	}

	@Override
	/**
	 * Appends name JLabel to North of panel
	 * @precondition this != null
	 * @postcondition this != null
	 */
	protected void setPageName() {
		Text login = new Text("Login Screen");
		login.setId("welcome-text");
		HBox titleBox = new HBox();
		titleBox.getChildren().add(login);
		this.setTop(titleBox);
		titleBox.setAlignment(Pos.CENTER);
	}
}
