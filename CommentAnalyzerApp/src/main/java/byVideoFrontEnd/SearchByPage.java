package byVideoFrontEnd;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.fasterxml.jackson.core.JsonParseException;

import api.Retriever;
import business.Content;
import business.ContentListPanel;
import business.ContentPanel;
import commentsFrontEnd.CommentListPanel;
import commentsFrontEnd.CommentPanel;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import loginRegister.JavaFXStart;

public abstract class SearchByPage<T> extends BorderPane implements Runnable {

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
	protected void createPanels(ArrayList<Content> retrieverInput, ContentListPanel panel) {
		//panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
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
			SearchByPage<T> self = this;
			Service<Void> backgroundThread = new Service<Void>() {
				@Override
				protected Task<Void> createTask() {
					return new Task<Void>() {
						@Override
						protected Void call() throws Exception {
							self.run();
							return null;
						}
					};
				}
			};
			backgroundThread.setOnSucceeded((evt) -> {
				createPanels((ArrayList<Content>) retrieverInput.get("content"),panel);
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

	public void run() {
		try {
			HashMap<String, Object> map = new HashMap();
			map.put("content", retriever.retrieve(field.getText()));
			retrieverInput =  map;
		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		addContentListPanel(panel);
		panel.setPadding(new Insets(20));
	}

	protected Stage stage;
	protected abstract void youtubeRetrieverSetup();
	protected  void addContentListPanel(ContentListPanel panel) {};
	protected abstract HBox getTitle();
	protected abstract void setInitialContent();
	protected ContentListPanel panel;
	protected Retriever retriever;
	protected HashMap<String, Object> retrieverInput;
	protected TaskBar bar;
	protected VBox top = new VBox();
	final TextField field = new TextField();
	protected Image image;
}
