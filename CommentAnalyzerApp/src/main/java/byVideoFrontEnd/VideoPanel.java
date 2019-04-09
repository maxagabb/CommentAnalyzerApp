package byVideoFrontEnd;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import business.Content;
import business.ContentPanel;
import business.Video1;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VideoPanel extends ContentPanel{
	
	public VideoPanel(Video1 content) {
		super(content);
	}
	public String getName() {
		return content.getName();
	}
	public String getVideoID() {
		return content.getID();
	}
	public void setPanel() {
		Label name = new Label(content.getName());
		name.setWrapText(true);
		name.setPrefWidth(200);
		//name.wrappingWidthProperty().bind(tabPane.widthProperty());
		//name.setVerticalAlignment(JLabel.CENTER);
		
		try {
		    URL imageUrl = new URL(content.getthumbnailURL());
		    InputStream in = imageUrl.openStream();
		    this.image = new Image(in);
		    in.close();
		    ImageView imageView = new ImageView(image);
		    HBox imageBox = new HBox(imageView);
		    imageBox.getChildren().add(name);
		    
		    this.getChildren().add(imageBox);
		    imageBox.setAlignment(Pos.CENTER_LEFT);
		   // name.setAlignment(Pos.CENTER_RIGHT);
		    imageBox.setSpacing(20);
		   // this.setBorder(new EtchedBorder());
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
			/*
		    try {
				image = ImageIO.read(new File(content.getthumbnailURL()));
				ImageView imageView = new ImageView(image);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    JLabel label = new JLabel(new ImageIcon(image));
		    this.getChildren().add(label);
		    this.getChildren().add(name);*/
		}
	}
	
	private final String html = "<html><body style='width: %1spx'>%1s";
}