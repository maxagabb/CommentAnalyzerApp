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
		//this.setPanel();
	}
	
	public String toString() {
		return content.getComment() + "\n";
	}
	
	public boolean contains(String input) {
		return content.getComment().toLowerCase().contains(input.toLowerCase());
	}
	
	public void setPanel() {
		Label comment = new Label(content.getComment());
		comment.setWrapText(true);
		comment.setPrefWidth(500);
		HBox commentBox = new HBox(comment);
		commentBox.setSpacing(20);
		commentBox.getStyleClass().add("commentBorder");
		
		this.getChildren().add(commentBox);
		//HBox.setHgrow(comment, Priority.ALWAYS);
		commentBox.setAlignment(Pos.CENTER_LEFT);
		
		
		//String html = "<html><body style='width: %1spx'>%1s";
		//JLabel label = new JLabel(String.format(html, 400,content.getComment()));
		//this.add(label);
		//this.setBackground(Color.WHITE);
		//this.setBorder(BorderFactory.createRaisedBevelBorder());
	}
	public CommentPanel clone() {
		return new CommentPanel(content);
	}
}
