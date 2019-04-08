package business;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

import byVideoFrontEnd.SearchByPage;
import byVideoFrontEnd.TaskBar;
import byVideoFrontEnd.VideoListPanel;
import byVideoFrontEnd.VideoPanel;
import commentsFrontEnd.CommentPage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import loginRegister.JavaFXStart;

public abstract class ContentListPanel extends VBox implements Runnable{
	public void emptyList() {};
	public ContentListPanel(Stage stage) {
		this.stage = stage;
		}

	@SuppressWarnings("unchecked")
	public void setPanel(){
		ContentListPanel self  = this;
		for(ContentPanel panel: (ArrayList<ContentPanel>)panels) {
			panel.setPanel();
			panel.getStyleClass().add("raisedBorder");
			panel.setPadding(new Insets(25));
			//ContentListPanel self  = this;
			
			panel.setOnMouseClicked(e->{
				panel.setStyle("-fx-background-color: #f2f2f2;");
				panel.getStyleClass().add("etchedBorder");
				self.panel = panel;
				Thread thread = new Thread(self);
				Platform.runLater(thread);
			});
			
			panel.setOnMouseEntered(e->{
				panel.setStyle("-fx-background-color: white;");
			});
			
			panel.setOnMouseExited(e->{
				panel.setStyle("-fx-background-color: #f2f2f2;");
			});
			this.getChildren().add(panel);
		}

	}

	public void run() {
		makeSearchByPage(stage, new TaskBar(stage), panel);
		page.setPage();
		//JPanel borderPage = new JPanel();
		page.setPadding(new Insets(40));
		//page.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
		//borderPage.add(page);
		//borderPage.setBorder(BorderFactory.createRaisedBevelBorder());
		page.getStyleClass().add("raisedBorder");
		//VBox finalPage = new VBox();
		//finalPage.getChildren().add(page);
		//ScrollPane pane = new ScrollPane(finalPage);
		
		
		ScrollPane root = new ScrollPane(page);
		GridPane grid = new GridPane();
		grid.getChildren().add(root);
		//ScrollPane pane = new ScrollPane(grid);
		
		Scene scene = new Scene(grid, stage.getWidth(), 
				stage.getHeight());
		grid.setAlignment(Pos.TOP_CENTER);
		
		scene.getStylesheets().add
		(JavaFXStart.class.getResource("myCSS.css").toExternalForm());
		stage.setScene(scene);

		//frame.getContentPane().removeAll();
		//frame.getContentPane().revalidate();
		//frame.add(pane);
		//frame.repaint();
	}
	protected abstract void makeSearchByPage(Stage stage, TaskBar taskBar, ContentPanel panel);
	public abstract void addPanel(Content content);
	protected ArrayList panels = new ArrayList();
	protected Stage stage;
	protected ContentPanel panel;
	protected SearchByPage page;
}