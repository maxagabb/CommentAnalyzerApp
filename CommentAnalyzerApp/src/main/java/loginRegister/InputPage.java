package loginRegister;

import java.io.File;

import javax.swing.JFrame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * 
 * @author mgabb2015
 */
public abstract class InputPage extends BorderPane{
	
	protected Stage stage;
	protected abstract void setPageName();
	protected abstract void nextPage(UserList list);
	protected TextField getEmailField() {return null;}

	/**
	 * Constructs InputPage by setting frame attribute
	 * and calling setPage()
	 * @precondition frame != null
	 * @postcondition this != null
	 * @param frame
	 */
	public InputPage(Stage primaryStage) {
		this.stage = primaryStage;
		setPage();
	}
	/**
	 * setPage is template method, calling
	 * concrete method setPagename()
	 * @precondition none
	 * @postcondition this != null
	 */
	public void setPage() {
		setPageName();
		setJTextFields();
	}
	/**
	 * sets name, password (and email) fields,
	 * getEmail() is implemented by concrete classes
	 * @precondition none
	 * @postcondition this != null
	 */
	protected void setJTextFields() {
		final TextField nameField = new TextField();
		nameField.setOnKeyReleased(e->{
			inputName = nameField.getText();
		});

		final TextField passwordField = new TextField();
		passwordField.setOnKeyReleased(e->{
			inputPassword = passwordField.getText();
		});

		VBox fieldPanel = new VBox();
		fieldPanel.setSpacing(30);
		fieldPanel.getStyleClass().add("raisedBorder");
		fieldPanel.setPadding(new Insets(50));
		fieldPanel.getChildren().add(makeInputPanel("username", nameField));
		fieldPanel.getChildren().add(makeInputPanel("password", passwordField));
		if (getEmailField()!=null) {
			fieldPanel.getChildren().add(makeInputPanel("Email", getEmailField()));}
		fieldPanel.getChildren().add(getSubmitButton());
		VBox fieldPanel2 = new VBox();
		fieldPanel2.getChildren().add(fieldPanel);
		GridPane fieldPanel3 = new GridPane();
		fieldPanel3.getChildren().add(fieldPanel2);
		this.setCenter(fieldPanel3);
		fieldPanel3.setAlignment(Pos.CENTER);
	}
	
	private VBox makeInputPanel(String name, TextField field) {
		field.setMaxWidth(100);
		VBox result = new VBox();
		Text label = new Text(name);
		result.getChildren().add(label);
		result.getChildren().add(field);
		//JPanel fieldPanel = new JPanel();
		
		return result;
	}
	/**
	 * sets the submit button and adds to the page,
	 * the listener calls nextPage(), a concrete method
	 * @precondition none
	 * @postcondition this != null
	 */
	protected HBox getSubmitButton() {
		Button submit = new Button("submit");
		submit.setOnAction(e ->{
			File userFile = new File("users.csv");
			UserList list = new UserList(userFile);
			nextPage(list);
		});
		HBox submitBox = new HBox();
		submitBox.getChildren().add(submit);
		submitBox.setAlignment(Pos.CENTER);
		return submitBox;
		
	}
	protected JFrame frame;
	protected String inputName;
	protected String inputPassword;
}
