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

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
	public WelcomePage(JFrame frame, Stage primaryStage) {
		this.frame = frame;
		this.primaryStage = primaryStage;
		setPage();
	}
	/**
	 * sets buttons redirecting to login and 
	 * register pages
	 * @precondition this != null
	 * @postcondition frame = null
	 */
	private void setPage() {
		String cssLayout = "-fx-border-color: grey;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 2;\n" +
                "-fx-background-color: white;" ;
		
		Button login = new Button("login");
		login.setOnAction(e ->{
			primaryStage.close();
			JFrame nextFrame = new JFrame();
			nextFrame.setBounds(frame.getX(), frame.getY(), 
					frame.getWidth(), frame.getHeight());
			frame.dispose();
			nextFrame.add(new LoginPage(nextFrame));
			nextFrame.setVisible(true);
			nextFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		});
		Button register = new Button("register");
		register.setOnAction(e ->{
			primaryStage.close();
			JFrame nextFrame = new JFrame();
			nextFrame.setBounds(frame.getX(), frame.getY(), 
					frame.getWidth(), frame.getHeight());
			frame.dispose();
			nextFrame.add(new RegisterPage(nextFrame));
			nextFrame.setVisible(true);
			nextFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		});
		
		Text welcome = new Text("Welcome Screen");
		//Label welcome = new Label(scenetitle);
		welcome.setId("welcome-text");
		HBox titleBox = new HBox();
		titleBox.getChildren().add(welcome);
		this.setTop(titleBox);
		titleBox.setAlignment(Pos.CENTER);
		
		VBox buttonBox = new VBox();
		buttonBox.getStyleClass().add("vbox");
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
	private JFrame frame;
	private Stage primaryStage;
}
