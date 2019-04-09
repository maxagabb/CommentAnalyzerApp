package business;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

public abstract class ContentPanel extends HBox {
	public ContentPanel(Content content) {
		this.content = content;
	}
	
	public void setPanel() {}
	public String getVideoID() {return null;}
	public String getChannelID() {return null;}
	public String getName() {return "default name";}
	public Image getImageIcon() {
		return image;
	}
	protected Image image;
	protected Content content;
}
