package commentsFrontEnd;



import business.Content;
import business.ContentPanel;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class CommentPanel extends ContentPanel implements Cloneable{
	public CommentPanel(Content comment) {
		super(comment);
	}
	
	public boolean contains(String input) {
		return content.getText().toLowerCase().contains(input.toLowerCase());
	}
	
	public void setPanel() {
		Label comment = new Label(content.getText());
		comment.setWrapText(true);
		comment.setPrefWidth(500);
		HBox commentBox = new HBox(comment);
		commentBox.setSpacing(20);
		commentBox.getStyleClass().add("commentBorder");
		
		this.getChildren().add(commentBox);
		commentBox.setAlignment(Pos.CENTER_LEFT);
		
	}
	public CommentPanel clone() {
		return new CommentPanel(content);
	}
}
