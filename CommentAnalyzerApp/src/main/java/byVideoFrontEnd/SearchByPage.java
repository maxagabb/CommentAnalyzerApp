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
 

/**
 * Abstract class that defines template method setPage()
 * @author mgabb2015
 * @param <Z> is a subclass of Content
  */
public abstract class SearchByPage<Z extends Content> extends BorderPane{

	public SearchByPage(Stage stage, TaskBar bar) {
		//this.getStyleClass().add("raisedBorder");
		this.bar = bar;
		this.stage = stage;
	}
        /**
         * template method for initial page setup
         * @precondition this != null
         * @postcondition top != null
         */
	public void setPage() {
		youtubeRetrieverSetup();
		HBox title = getTitle();
		title.getChildren().get(0).setId("title-text");
		top.getChildren().add(title);
		title.setAlignment(Pos.CENTER);
		setInitialContent();
		this.setTop(top);
	}
	/**
	 * Sets a contentListPanel with data from API, 
	 * then adds it to the SearchByPage
	 * @param retrieverInput
	 * @param panel
         * @precondition panel != null
         * @postcondition panel != null
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
        /**
         * Sets a listener for TextField that sets contentListPanel in 
         * a background thread
         * @precondition this.field != null
         * @postcondition this.field != null
         */
	protected void setTextFieldListener() {
		field.setOnAction(e->{
			Service<Void> backgroundThread = new Service<Void>() {
				@Override
				protected Task<Void> createTask() {
					return new Task<Void>() {
						@Override
						protected Void call() throws Exception {
							setRetreiverOutput();
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

	/**
         * Calls retrieve from YouTube API and sets retrieverOutput
         * @precondition this.retriever != null 
         * @postcondition this.retrieverOutput != null
         */
	public void setRetreiverOutput() {
		try {
			retrieverOutput = retriever.retrieve(field.getText());
		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

        /**
         * Instantiates Retriever with Retriever Subclass in 
         * concrete subclasses of SearchByPage.
         * primitiveOP and factory method 
         * @precondiiton none
         * @postcondition this.retriever != null
         */
	protected abstract void youtubeRetrieverSetup();
        /**
         * Factory method implemented by concrete subclasses that
         * instantiate the right subclass of ContentListPanel
         * @param panel 
         * @precondition none
         * @postcondition this.panel != null
         */
	protected  void addContentListPanel(ContentListPanel panel){};
        /**
         * PrimitiveOP for concrete classes that sets the title of the page
         * @return HBox containing page title
         * @precondition none
         * @postcondition HBox != null
         */
	protected abstract HBox getTitle();
        /**
         * PrimitiveOP for concrete classes that sets initialize page content.
         * Some pages are initialized with output from API 
         * @precondition none
         * @postcondition none 
         */
	protected abstract void setInitialContent();
	protected ContentListPanel panel;
	protected Retriever<Z> retriever;
	protected ArrayList<Z> retrieverOutput;
	protected TaskBar bar;
	protected VBox top = new VBox();
	final TextField field = new TextField();
	protected Image image;
        protected Stage stage;
}
