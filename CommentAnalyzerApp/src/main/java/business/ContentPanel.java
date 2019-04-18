package business;

import java.io.File;
import java.io.IOException;

import java.io.InputStream;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public abstract class ContentPanel extends HBox {
	public ContentPanel(Content content) {
		this.content = content;
	}
	public void setPanel() {
		Label name = new Label(getName());
		name.setWrapText(true);
		name.setPrefWidth(300);
		HBox imageBox = new HBox();
		try {
			URL imageUrl = new URL(content.getthumbnailURL());
			InputStream in = imageUrl.openStream();
			this.image = new Image(in);
			in.close();
			ImageView imageView = new ImageView(image);
			imageBox.getChildren().add(imageView);
			imageBox.getChildren().add(name);

		}
		catch (IOException ioe) {
			try {
				File file = new File(content.getthumbnailURL());
				this.image = new Image(file.toURI().toString());
				ImageView imageView = new ImageView(image);
				imageBox.getChildren().add(imageView);
				imageBox.getChildren().add(name);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		imageBox.setSpacing(20);
		this.getChildren().add(imageBox);
	}
	public String getVideoID() {return null;}
	public String getChannelID() {return null;}
	public String getName() {return "default name";}
	public Image getImage() {
		return image;
	}
	protected Image image;
	protected Content content;
}
