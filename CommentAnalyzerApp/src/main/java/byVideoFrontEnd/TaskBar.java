package byVideoFrontEnd;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import byChannelFrontEnd.ByChannelPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import loginRegister.JavaFXStart;

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
				ScrollPane root = getPane(button.getText());
				GridPane grid = new GridPane();
				grid.getChildren().add(root);
				//ScrollPane pane = new ScrollPane(grid);
				
				Scene scene = new Scene(grid, stage.getWidth(), 
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
	
	private ScrollPane getPane(String name) {
		Pane page = null;
		if(name.equals("Manage Favorites"))
			return new ScrollPane();
		else if(name.equals("By Video")) {
			ByVideoPage videoPage = new ByVideoPage(stage, new TaskBar(stage));
			videoPage.setPage();
			page = videoPage;
		}
		else if(name.equals("By Channel")) {
			ByChannelPage channelPage = new ByChannelPage(stage, new TaskBar(stage));
			channelPage.setPage();
			page = channelPage;
		}
		else return new ScrollPane();
		StackPane centeredPage = new StackPane(page);
		//centeredPage.getStyleClass().add("raisedBorder");
		
		//Pane borderPage = new Pane();
		page.setPadding(new Insets(40));
		return new ScrollPane(centeredPage);
	}
	
	private Stage stage;
}
