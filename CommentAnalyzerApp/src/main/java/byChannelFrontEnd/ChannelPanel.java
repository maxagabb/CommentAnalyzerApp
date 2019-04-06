package byChannelFrontEnd;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

import business.Content;
import business.ContentPanel;

public class ChannelPanel extends ContentPanel{
	public ChannelPanel(Channel1 content) {
		super(content);
	}
	public String getName() {
		return content.getName();
	}
	public String getChannelID() {
		return content.getID();
	}
	public void setPanel() {
		this.setLayout(new FlowLayout());
		BufferedImage image = null;
		JLabel name = new JLabel(String.format(html, 200, getName()));
		name.setVerticalAlignment(JLabel.CENTER);
		
		try {
		    URL imageUrl = new URL(content.getthumbnailURL());
		    InputStream in = imageUrl.openStream();
		    image = ImageIO.read(in);
		    in.close();
		    this.imageIcon = new ImageIcon(image);
		    this.add(new JLabel(imageIcon));
		    this.add(name);
		}
		catch (IOException ioe) {
		    try {
				image = ImageIO.read(new File(content.getthumbnailURL()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    imageLabel = new JLabel(new ImageIcon(image));
		    this.add(imageLabel);
		    this.add(name);
		}
	}
	public ImageIcon getImageIcon() {
		
		return imageIcon;
	}
	private JLabel imageLabel;
	private final String html = "<html><body style='width: %1spx'>%1s";
}
