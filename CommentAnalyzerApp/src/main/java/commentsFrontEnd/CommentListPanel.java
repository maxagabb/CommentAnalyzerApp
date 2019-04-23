package commentsFrontEnd;


import java.util.ArrayList;



import business.Comment;
import business.Content;
import business.ContentListPanel;
import business.ContentPanel;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class CommentListPanel extends ContentListPanel{
	public CommentListPanel(Stage stage) {
		super(stage);
	}

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
	
	public void addPanel(Content content) {
		panels.add(new CommentPanel((Comment) content));
		allPanels.add(new CommentPanel((Comment) content));
	}
	
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
	
	public ArrayList<String> getComments(){
		ArrayList<String> result = new ArrayList<String>();
		panels.stream().forEach((e)-> result.add(e.getPanelText()));
		return result;
	}
	protected void makeSearchByPage(ContentPanel panel) {}
	private ArrayList<CommentPanel> panels = new ArrayList<CommentPanel>();
	ArrayList<CommentPanel> allPanels= new ArrayList<CommentPanel>();
}
