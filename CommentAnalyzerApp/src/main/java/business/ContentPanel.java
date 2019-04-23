package business;

import java.io.File;

import java.io.IOException;

import java.io.InputStream;
import java.net.URL;


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
		Label name = new Label(getPanelText());
		name.setWrapText(true);
		name.setPrefWidth(300);
		HBox panelBox = new HBox();
		HBox imageBox = new HBox();
		try {
			URL imageUrl = new URL(content.getthumbnailURL());
			InputStream in = imageUrl.openStream();
			this.image = new Image(in);
			in.close();
			ImageView imageView = new ImageView(image);
			imageBox.getChildren().add(imageView);
			panelBox.getChildren().add(imageBox);
			panelBox.getChildren().add(name);

		}
		catch (IOException ioe) {
			try {
				File file = new File(content.getthumbnailURL());
				this.image = new Image(file.toURI().toString());
				ImageView imageView = new ImageView(image);
				imageBox.getChildren().add(imageView);
				panelBox.getChildren().add(imageBox);
				panelBox.getChildren().add(name);
				this.setDisable(true);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		imageBox.setAlignment(Pos.CENTER_LEFT);
		name.setAlignment(Pos.CENTER_LEFT);
		panelBox.setSpacing(20);
		this.getChildren().add(panelBox);
	}
	public String getPanelText() {
		return content.getText();
	}
	public String getVideoID() {return null;}
	public String getChannelID() {return null;}
	public Image getImage() {
		return image;
	}
	protected Image image;
	protected Content content;
}
