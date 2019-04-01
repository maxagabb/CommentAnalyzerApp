package analysisFrontEnd;

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
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import business.Video1;

public class CommentPanel extends JPanel implements Cloneable{
	public CommentPanel(String comment) {
		this.comment= comment;
		//this.setPanel();
	}
	
	public String toString() {
		return comment + "\n";
	}
	
	public boolean contains(String input) {
		return comment.contains(input);
	}
	
	public void setPanel() {
		JLabel label = new JLabel(String.format(html, 400,comment));
		this.add(label);
		this.setBorder(new EtchedBorder(EtchedBorder.RAISED));
	}
	public CommentPanel clone() {
		return new CommentPanel(comment);
	}
	private String comment;
	private final String html = "<html><body style='width: %1spx'>%1s";
}
