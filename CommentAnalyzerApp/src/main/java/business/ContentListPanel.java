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


        /**
         * sets content list panel, calling setPanel on all aggregated
         * panels(composition pattern). Adds listeners to panels
         * @precondition none
         * @postcondition ContentListPanel object is set 
         */
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

				tasks.stream().forEach((task)->task.cancel());
				Service<Void> thread = makeThread();
		        thread.start();
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
	/**
         * creates background thread which starts when a ContentPanel
         * is clicked. The thread sets the next page. 
         * @return Service with Task that runs in background
         * @precondition none
         * @postcondition returns Service with defined Task
         */
	private Service<Void> makeThread() {
		ContentListPanel self  = this;
		Service<Void> backgroundThread = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                Task<Void> task =  new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        self.setNextPage();
                        return null;
                    }
                };
                tasks.add(task);
                return task;
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
        return backgroundThread;
	}

        /**
         * Calls factory method in concrete subclass and sets the 
         * resulting page. 
         * @precondition none
         * @postcondition this.page != null
         */
	private void setNextPage() {
		makeSearchByPage(panel);
		page.setPage();
		page.setPadding(new Insets(20));
	}
	protected abstract void makeSearchByPage(ContentPanel panel);
	public abstract void addPanel(Content content);
	protected ArrayList<ContentPanel> panels = new ArrayList<ContentPanel>();
	protected Stage stage;
	protected ContentPanel panel;
	protected SearchByPage<?> page;
	private ArrayList<Task<Void>> tasks = new ArrayList<Task<Void>>();
}