package byVideoFrontEnd;


import java.io.IOException;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;

import api.Retriever;
import business.Content;
import business.ContentListPanel;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//T defines whatretrieverInput holds  
//Z is Content subclass
public abstract class SearchByPage<Z extends Content> extends BorderPane{

	public SearchByPage(Stage stage, TaskBar bar) {
		//this.getStyleClass().add("raisedBorder");
		this.bar = bar;
		this.stage = stage;
	}

	public void setPage() {
		//top.getChildren().add(bar);
		youtubeRetrieverSetup();
		HBox title = getTitle();
		title.getChildren().get(0).setId("title-text");
		top.getChildren().add(title);
		title.setAlignment(Pos.CENTER);
		setInitialContent();
		//top.setPadding(new Insets(25));
		this.setTop(top);
	}
	/**
	 * Sets a contentListPanel with data from API, 
	 * then adds it to the SearchByPage
	 * @param retrieverInput
	 * @param panel
	 */
	protected void createPanels(ArrayList<Z> retrieverInput, ContentListPanel panel) {
		for (Content content: retrieverInput) {
			panel.addPanel(content);
		}
		panel.getChildren().clear();
		panel.setPanel();
		ScrollPane list = new ScrollPane(panel);
		GridPane grid = new GridPane();
		VBox pane = new VBox(grid);
		grid.getChildren().add(list);
		this.setCenter(pane);
		grid.setAlignment(Pos.CENTER);
		pane.setAlignment(Pos.CENTER);
		panel.setAlignment(Pos.CENTER);
		panel.setPadding(new Insets(100));
		pane.setPadding(new Insets(25));
	}

	protected void createJTextFields() {
		field.setOnAction(e->{
			SearchByPage<Z> self = this;
			Service<Void> backgroundThread = new Service<Void>() {
				@Override
				protected Task<Void> createTask() {
					return new Task<Void>() {
						@Override
						protected Void call() throws Exception {
							self.setRetreiverInput();
							addContentListPanel(panel);
							panel.setPadding(new Insets(20));
							return null;
						}
					};
				}
			};
			backgroundThread.setOnSucceeded((evt) -> {
				createPanels(retrieverOutput,panel);
			});
			backgroundThread.start();
		});

		HBox fieldBox = new HBox(field);
		VBox fieldPanel = new VBox();
		field.setMaxWidth(250);
		fieldPanel.getChildren().add(fieldBox);
		fieldPanel.getStyleClass().add("fieldBorder");

		top.getChildren().add(fieldPanel);
		fieldBox.setAlignment(Pos.CENTER);
		top.setSpacing(30);
	}

	
	public void setRetreiverInput() {
		try {
			//HashMap<String, T> map = new HashMap<String, T>();
			//map.put("content", (T) retriever.retrieve(field.getText()));
			retrieverOutput = retriever.retrieve(field.getText());
		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}


	protected Stage stage;
	protected abstract void youtubeRetrieverSetup();
	protected  void addContentListPanel(ContentListPanel panel) {};
	protected abstract HBox getTitle();
	protected abstract void setInitialContent();
	protected ContentListPanel panel;
	protected Retriever<Z> retriever;
	//protected HashMap<String, T> retrieverInput;
	protected ArrayList<Z> retrieverOutput;
	protected TaskBar bar;
	protected VBox top = new VBox();
	final TextField field = new TextField();
	protected Image image;
}
