package commentsFrontEnd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import business.Comment;
import business.Content;
import business.ContentPanel;
import business.Video1;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

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
