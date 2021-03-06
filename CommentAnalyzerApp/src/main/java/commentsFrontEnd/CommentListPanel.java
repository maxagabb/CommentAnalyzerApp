package commentsFrontEnd;


import business.AnalyzedComment;
import java.util.ArrayList;



import business.Comment;
import business.Content;
import business.ContentListPanel;
import business.ContentPanel;
import com.ibm.watson.tone_analyzer.v3.model.ToneAnalysis;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class CommentListPanel extends ContentListPanel{
	public CommentListPanel(Stage stage) {
		super(stage);
	}

        @Override
	public void setPanel(){
		this.setPadding(new Insets(25));
		for(CommentPanel panel: panels) {
			panel.setPanel();
			panel.setPadding(new Insets(20));
			this.getChildren().add(panel);
		}
		if (panels.isEmpty()){
			CommentPanel panel = new CommentPanel
					(new Comment("No comments"));
			panel.setPanel();
			panel.setPadding(new Insets(20));
			this.getChildren().add(panel);
		}
	}
	
        @Override
	public void addPanel(Content content) {
		panels.add(new CommentPanel((Comment) content));
		allPanels.add(new CommentPanel((Comment) content));
	}
	/**
         * Selects out CommentPanels without keyword and updates view
         * @param text is the keyword
         * @precondition none
         * @postcondition none
         */
	public void parseComments(String text) {
		panels.clear();
		for(CommentPanel panel : allPanels) {
		    panels.add(panel.clone());
		}
		ArrayList<CommentPanel> panelsToRemove = new ArrayList<CommentPanel>();
		for(CommentPanel panel: panels) {
			if (panel.contains(text) == false) {
				panelsToRemove.add(panel);
			}
		}
		panels.removeAll(panelsToRemove);
		this.getChildren().clear();
		setPanel();
	}
	/**
         * returns all comments currently displayed by the view
         * @return ArrayList<String> of comments
         * @precondition panels != null
         * @postcondition returns ArrayList of comments
         */
	public ArrayList<String> getComments(){
		ArrayList<String> result = new ArrayList<String>();
		panels.stream().forEach((e)-> result.add(e.getPanelText()));
		return result;
	}
        
        
        public void applyAnalysis(ArrayList<ToneAnalysis> tones){
            panels.clear();
            for (ToneAnalysis tone: tones){
                panels.add(new AnalysisPanel(new AnalyzedComment(tone)));
            }
            this.getChildren().clear();
            setPanel();
        }
        
        @Override
	protected void makeSearchByPage(ContentPanel panel) {}
	private ArrayList<CommentPanel> panels = new ArrayList<CommentPanel>();
	ArrayList<CommentPanel> allPanels= new ArrayList<CommentPanel>();
}
