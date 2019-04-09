package commentsFrontEnd;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import com.fasterxml.jackson.core.JsonParseException;

import api.CommentRetriever;
import api.Retriever;
import byVideoFrontEnd.SearchByPage;
import byVideoFrontEnd.TaskBar;
import byVideoFrontEnd.VideoListPanel;
import byVideoFrontEnd.VideoPanel;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CommentPage extends SearchByPage{


	public CommentPage(Stage stage, TaskBar bar, VideoPanel videoPanel) {
		super(stage, bar);
		this.videoID = videoPanel.getVideoID();
		this.panel = new CommentListPanel(stage);
		this.videoName = videoPanel.getName();

	}

	protected void createJTextFields() {
		final TextField field2 = new TextField();
		field2.setOnAction(e ->{
			panel.parseComments(field2.getText());
		});
		
		HBox fieldBox = new HBox(field2);
		VBox fieldPanel = new VBox();
		field2.setMaxWidth(250);
		fieldPanel.getChildren().add(fieldBox);
		fieldPanel.getStyleClass().add("fieldBorder");
		
		top.getChildren().add(fieldPanel);
		fieldBox.setAlignment(Pos.CENTER);
		top.setSpacing(30);

	}

	@Override
	protected void setInitialContent() {
		createPanels((ArrayList) retrieverInput.get("comments"), panel);
		createJTextFields();
	}
	@Override
	protected HBox getTitle() {
		Text nameLabel = new Text(videoName);
		HBox panel = new HBox(nameLabel);
		return panel;
	}
	@Override
	protected void youtubeRetrieverSetup() {
		try {
			retriever = new CommentRetriever();
			HashMap<String, Object> map = new HashMap();
			map.put("comments", retriever.retrieve(videoID));
			retrieverInput =  map;
		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	private String videoID;
	private String videoName;
	private CommentListPanel panel = new CommentListPanel(stage);


}
