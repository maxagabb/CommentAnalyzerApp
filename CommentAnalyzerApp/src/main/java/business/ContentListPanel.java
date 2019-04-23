package business;


import java.util.ArrayList;


import byVideoFrontEnd.SearchByPage;
import byVideoFrontEnd.TaskBar;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class ContentListPanel extends VBox{
	public void emptyList() {};
	public ContentListPanel(Stage stage) {
		this.stage = stage;
		}


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
				Service<Void> backgroundThread = new Service<Void>() {
		            @Override
		            protected Task<Void> createTask() {
		                return new Task<Void>() {
		                    @Override
		                    protected Void call() throws Exception {
		                        self.setNextPage();
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

	public void setNextPage() {
		makeSearchByPage(panel);
		page.setPage();
		page.setPadding(new Insets(20));
	}
	protected abstract void makeSearchByPage(ContentPanel panel);
	public abstract void addPanel(Content content);
	protected ArrayList<ContentPanel> panels = new ArrayList<ContentPanel>();
	protected Stage stage;
	protected ContentPanel panel;
	protected SearchByPage<?,?> page;
}