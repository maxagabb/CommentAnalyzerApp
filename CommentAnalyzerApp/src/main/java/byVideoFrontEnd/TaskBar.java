package byVideoFrontEnd;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import byChannelFrontEnd.ByChannelPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TaskBar extends HBox{
	public TaskBar(Stage stage) {
		this.setSpacing(25);
		this.getStyleClass().add("taskBar");
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
				GridPane root = new GridPane();
				root.add(this, 0, 0);
				root.add(getPane(button.getText()),0, 1);
				root.setAlignment(Pos.TOP_CENTER);
				root.getStyleClass().add("raisedBorder");
				GridPane root2 = new GridPane();
				root2.getChildren().add(root);
				root2.setAlignment(Pos.TOP_CENTER);
				this.stage.getScene().setRoot(root2);
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
			ByChannelPage channelPage = new ByChannelPage(stage, new TaskBar(stage));
			channelPage.setPage();
			page = channelPage;
		}
		else return new Pane();
		StackPane centeredPage = new StackPane(page);
		//centeredPage.getStyleClass().add("raisedBorder");
		
		//Pane borderPage = new Pane();
		page.setPadding(new Insets(40));
		return centeredPage;
	}
	
	private Stage stage;
}
