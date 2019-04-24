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
	/**
         * Determine if comment held in panel contains keyword
         * @param keyword
         * @return true if comment contains keyword
         * @precondition this != null
         * @postcondition contains == true || contains == false
         */
	public boolean contains(String keyword) {
		return content.getText().toLowerCase().contains(keyword.toLowerCase());
	}
	/**
         * Sets and styles comment panel
         * @precondition this != null
         * @postcondition this != null
         */
        @Override
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
        /**
         * returns deep clone of commentPanel
         * @return  new CommentPanel()
         * @precondition this != null
         * @postcondition this.clone != null
         */
	public CommentPanel clone() {
		return new CommentPanel(content);
	}
}
