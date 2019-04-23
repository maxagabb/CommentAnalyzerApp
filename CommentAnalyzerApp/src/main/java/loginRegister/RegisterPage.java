package loginRegister;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegisterPage extends InputPage{

	/**
	 * concrete child of InputPage,
	 * implements nextPage,setPageName
	 * and getEmailField
	 * @param frame
	 * @invariant this != null
	 */
	public RegisterPage(Stage primaryStage ) {
		super(primaryStage);
	}

	@Override
	/**
	 * if all fields are set, write to userList file,
	 * dispose current frame and setup login page
	 * @precondition list != null
	 * @postcondition this.frame = null
	 */
	protected void nextPage(UserList list) {
		ArrayList<String> input = new ArrayList<String>();
		try {
			input.add(inputName);
			input.add(inputPassword);
			input.add(inputEmail);
			list.printToFile(input);
			
			StackPane root = new StackPane();
			root.getChildren().add(new LoginPage(stage));
			
			Scene scene = new Scene(root, stage.getWidth(), 
					stage.getHeight());
			scene.getStylesheets().add
			 (JavaFXStart.class.getResource("myCSS.css").toExternalForm());
			
			stage.setScene(scene);
			
		}
		catch(Exception E) {
			JOptionPane.showMessageDialog(frame, "invalid Input");
		}
	}

	@Override
	/**
	 * returns email text field attached to KeyListener
	 * @precondition none
	 * @postcondition returns emailField
	 */
	protected TextField getEmailField() {
		final TextField emailField = new TextField();
		emailField.setOnKeyReleased(e->{
			inputEmail = emailField.getText();
		});
		return emailField;
	}

	@Override
	/**
	 * Appends name JLabel to North of panel
	 * @precondition this != null
	 * @postcondition this != null
	 */
	protected void setPageName() {
		Text login = new Text("Register Screen");
		login.setId("welcome-text");
		HBox titleBox = new HBox();
		titleBox.getChildren().add(login);
		this.setTop(titleBox);
		titleBox.setAlignment(Pos.CENTER);
	}
	
	private String inputEmail;
}
