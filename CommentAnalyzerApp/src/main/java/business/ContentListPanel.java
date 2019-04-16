package business;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

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
import javafx.concurrent.Service;
import javafx.concurrent.Task;
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
			
			panel.setOnMouseClicked(e->{
				self.panel = panel;
				self.panel.getStyleClass().remove("raisedBorder");
				self.panel.getStyleClass().add("pressedBorder");
				self.panel.setStyle("-fx-background-color: #f2f2f2;");
				//self.panel.getStyleClass().add("etchedBorder");
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
		    		GridPane root = new GridPane();
					root.add(new TaskBar(stage), 0, 0);
					root.add(self.page,0, 1);
					root.setAlignment(Pos.TOP_CENTER);
					root.getStyleClass().add("raisedBorder");
					GridPane root2 = new GridPane();
					root2.getChildren().add(root);
					root2.setAlignment(Pos.TOP_CENTER);
					this.stage.getScene().setRoot(root2);
		        });
		        backgroundThread.start();
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
		makeSearchByPage(panel);
		page.setPage();
		page.setPadding(new Insets(20));
		//page.getStyleClass().add("raisedBorder");
	}
	protected abstract void makeSearchByPage(ContentPanel panel);
	public abstract void addPanel(Content content);
	protected ArrayList panels = new ArrayList();
	protected Stage stage;
	protected ContentPanel panel;
	protected SearchByPage page;
}