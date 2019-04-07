package byVideoFrontEnd;

import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

import byChannelFrontEnd.ByChannelPage;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import loginRegister.JavaFXStart;
import loginRegister.WelcomePage;

public class TaskBar extends HBox{
	public TaskBar(Stage stage) {
		this.stage = stage;
		setBar();
	}

	private void setBar() {
		ArrayList<String> buttonNames = new ArrayList<String>();
		buttonNames.add("Manage Favorites");buttonNames.add("By Video");
		buttonNames.add("By Channel");buttonNames.add("By Favorites");
		Stream<Button> stream = buttonNames.stream().map( Button::new);

		ArrayList<Button> buttons = (ArrayList<Button>) stream.collect(Collectors.toList());
		Iterator<Button> iterator = buttons.iterator();
		
		while(iterator.hasNext()) {
			Button button = iterator.next();
			
			button.setOnAction(e ->{
				StackPane root = new StackPane(getPane(button.getText()));
				ScrollPane pane = new ScrollPane(root);
				GridPane grid = new GridPane();

				root.minWidthProperty().bind(Bindings.createDoubleBinding(() -> 
				pane.getViewportBounds().getWidth(), pane.viewportBoundsProperty()));
				grid.getChildren().add(root);

				Scene scene = new Scene(grid, 800, 
						stage.getHeight());
				
				grid.setAlignment(Pos.TOP_CENTER);

				scene.getStylesheets().add
				(JavaFXStart.class.getResource("myCSS.css").toExternalForm());
				stage.setScene(scene);
	
			});
			this.getChildren().add(button);
		}
		this.setAlignment(Pos.CENTER);
	}
	
	private Pane getPane(String name) {
		Pane page = null;
		if(name.equals("Manage Favorites"))
			return new Pane();
		else if(name.equals("By Video")) {
			ByVideoPage videoPage = new ByVideoPage(stage, new TaskBar(stage));
			videoPage.setPage();
			page = videoPage;
		}
		else if(name.equals("By Channel")) {
			//ByChannelPage channelPage = new ByChannelPage(stage, new TaskBar(stage));
			//channelPage.setPage();
			//page = channelPage;
		}
		else return new Pane();
		
		Pane borderPage = new Pane();
		page.setPadding(new Insets(40));
		borderPage.getChildren().add(page);
		

		borderPage.getStyleClass().add("raisedBorder");
		Pane finalPage = new Pane();
		finalPage.getChildren().add(borderPage);
		return finalPage;
		
		
	}
	private Stage stage;
}
