package loginRegister;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import byVideoFrontEnd.MainPage;
import byVideoFrontEnd.TaskBar;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginPage extends InputPage{

	/**
	 * concrete child of InputPage,
	 * implements nextPage and setPageName
	 * @param primaryStage
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
