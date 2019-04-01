package analysisFrontEnd;

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

import business.Video1;

public class CommentPanel extends JPanel{
	public CommentPanel(String comment) {
		this.comment= comment;
		//this.setPanel();
	}
	
	public String toString() {
		return comment + "\n";
	}
	
	public void setPanel() {
		JLabel label = new JLabel(comment);
		this.add(label);
	}
	private String comment;
}
