package commentsFrontEnd;


import java.io.IOException;





import com.fasterxml.jackson.core.JsonParseException;

import api.CommentRetriever;
import business.Comment;
import byVideoFrontEnd.SearchByPage;
import byVideoFrontEnd.TaskBar;
import byVideoFrontEnd.VideoPanel;
import com.ibm.watson.tone_analyzer.v3.Analyzer;
import com.ibm.watson.tone_analyzer.v3.model.ToneAnalysis;
import java.util.ArrayList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CommentPage extends SearchByPage<Comment>{


	public CommentPage(Stage stage, TaskBar bar, VideoPanel videoPanel) {
		super(stage, bar);
		this.videoID = videoPanel.getVideoID();
		this.panel = new CommentListPanel(stage);
		this.videoName = videoPanel.getPanelText();
                this.analyzed = false;
	}

        @Override
	protected void setTextFieldListener() {
		final TextField keywordField = new TextField();
		keywordField.setOnAction(e ->{
			panel.parseComments(keywordField.getText());
                        analyzed = false;
		});
		
		HBox fieldBox = new HBox(keywordField);
		VBox fieldPanel = new VBox();
		keywordField.setMaxWidth(250);
		fieldPanel.getChildren().add(fieldBox);
		fieldPanel.getStyleClass().add("fieldBorder");
		
		top.getChildren().add(fieldPanel);
		fieldBox.setAlignment(Pos.CENTER);
		top.setSpacing(30);
	}

	@Override
	protected void setInitialContent() {
		createPanels(retrieverOutput, panel);
		setTextFieldListener();
		Button analyze = new Button("Tone Analyze");
		analyze.setOnAction(e ->{
                    if (analyzed == false) {
                        analyzed = true;
                        startAnalysisThread();
                    }
		});
		top.getChildren().add(analyze);
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
			retrieverOutput =  retriever.retrieve(videoID);
		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
    private void startAnalysisThread() {
        Service<Void> backgroundThread = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        Analyzer watson = new Analyzer();
                        analyzedComments = watson.analyze(panel.getComments());
                        return null;
                    }
                };
            }
        };
        backgroundThread.setOnSucceeded((evt) -> {
            panel.applyAnalysis(analyzedComments);
        });
        backgroundThread.start();
        }
        private boolean analyzed;
        private ArrayList<ToneAnalysis> analyzedComments;
	private String videoID;
	private String videoName;
	private CommentListPanel panel = new CommentListPanel(stage);


}
