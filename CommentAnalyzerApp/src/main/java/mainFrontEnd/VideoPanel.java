package mainFrontEnd;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
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

public class VideoPanel extends ContentPanel{
	public VideoPanel(Video1 content) {
		super(content);
	}

	public String getVideoID() {
		return content.getID();
	}
	
	public void setPanel() {
		this.setLayout(new FlowLayout());
		BufferedImage image = null;
		JLabel name = new JLabel(String.format(html, 200, content.getname()));
		name.setVerticalAlignment(JLabel.CENTER);
		
		try {
		    URL imageUrl = new URL(content.getthumbnailURL());
		    InputStream in = imageUrl.openStream();
		    image = ImageIO.read(in);
		    in.close();
		    this.add(new JLabel(new ImageIcon(image)));
		    this.add(name);
		    this.setBorder(new EtchedBorder());
		}
		catch (IOException ioe) {
		    try {
				image = ImageIO.read(new File(content.getthumbnailURL()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    this.add(new JLabel(new ImageIcon(image)));
		    this.add(name);
		}
	}
	private final String html = "<html><body style='width: %1spx'>%1s";
}
