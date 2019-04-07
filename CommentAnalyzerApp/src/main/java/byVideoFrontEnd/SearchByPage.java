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
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class SearchByPage<T> extends BorderPane implements Runnable{

	public SearchByPage(Stage stage, TaskBar bar) {
		this.bar = bar;
		this.stage = stage;
	}

	public void setPage() {
		//this.setLayout(new BorderLayout());
		//top.setLayout(new BoxLayout(top,BoxLayout.PAGE_AXIS));
		top.getChildren().add(bar);
		//top.add(Box.createRigidArea(new Dimension(0,20)));
		youtubeRetrieverSetup();
		HBox title = getTitle();
		//title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		top.getChildren().add(title);
		title.setAlignment(Pos.CENTER);
		//top.add(Box.createRigidArea(new Dimension(0,20)));
		setInitialContent();
		//JPanel topPanel = new JPanel();
		//topPanel.add(top);
		this.setTop(top);
	}
	
	protected void createPanels(ArrayList<Content> retrieverInput, ContentListPanel panel) {
		//panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		for (Content content: retrieverInput) {
			panel.addPanel(content);
		}
		panel.setPanel();
		this.getChildren().add(panel);
	}
	
	protected void createJTextFields() {
		field.setOnAction(e->{
			Thread thread = new Thread(this);
			thread.start();
		});
		VBox fieldPanel = new VBox();
		field.setMaxWidth(250);
		fieldPanel.getChildren().add(field);
		fieldPanel.getStyleClass().add("fieldBorder");
		
		top.getChildren().add(fieldPanel);
		top.setSpacing(30);
		//top.add(Box.createRigidArea(new Dimension(0,40)));
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
		createPanels((ArrayList<Content>) retrieverInput.get("content"),panel);
		//this.revalidate();
		//this.repaint();
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
	protected Image imageIcon;
}
