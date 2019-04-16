package business;

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
		name.setPrefWidth(200);
		
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
			imageBox.setSpacing(20);
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
			try {
				URL imageUrl = new URL(content.getthumbnailURL());
				InputStream in = imageUrl.openStream();
				this.image = new Image(in);
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			this.getChildren().add(new ImageView(image));
			this.getChildren().add(name);
		}
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
