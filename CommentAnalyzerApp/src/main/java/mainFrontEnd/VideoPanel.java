package mainFrontEnd;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import api.Video1;

public class VideoPanel extends JPanel{
	public VideoPanel(Video1 video) {
		this.video = video;
		//this.setPanel();
	}
	
	public String toString() {
		return video.getname() + "\n";
	}
	
	public void setPanel() {
		this.setLayout(new FlowLayout());
		BufferedImage image = null;
		JLabel name = new JLabel(video.getname());
		name.setVerticalAlignment(JLabel.CENTER);
		
		try {
		    URL imageUrl = new URL(video.getthumbnailURL());
		    InputStream in = imageUrl.openStream();
		    image = ImageIO.read(in);
		    in.close();
		    this.add(new JLabel(new ImageIcon(image)));
		    this.add(name);
		}
		catch (IOException ioe) {
		    ioe.printStackTrace();
		}
	}
	private Video1 video;
}
