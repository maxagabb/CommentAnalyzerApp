package byVideoFrontEnd;


import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainPage extends BorderPane{

	public MainPage(Stage stage, TaskBar bar) {
		this.bar = bar;
		//this.stage = stage;
		setPage();
	}
	
	private void setPage() {
		setTop(bar);
	}

	//private Stage stage;
	private TaskBar bar;
}
