package byVideoFrontEnd;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.fasterxml.jackson.core.JsonParseException;

import api.Retriever;
import api.VideoRetriever;
import business.ContentListPanel;
import business.ContentPanel;
import business.Video1;
import byChannelFrontEnd.Channel1;
import byChannelFrontEnd.ChannelPanel;

public class ByVideoPage extends SearchByPage{
	public ByVideoPage(JFrame frame,TaskBar bar) {
		super(frame,bar);
		//this.panel = new VideoListPanel(frame);
	}
	public ByVideoPage(JFrame frame,TaskBar bar, ChannelPanel panel) {
		super(frame,bar);
		this.channelName = panel.getName();
		this.channelID = panel.getChannelID();
		this.imageIcon = panel.getImageIcon();
	}



	@Override
	protected void setInitialContent() {
		if(channelName == null) 
			createJTextFields();
		else {
			addContentListPanel(panel);
			createPanels((ArrayList) retrieverInput.get("videos"), panel);
			this.revalidate();
			this.repaint();
		}
	}
	@Override
	protected JPanel getTitle() {
		JPanel panel = new JPanel();

		if(channelName ==null) {
			JLabel nameLabel = new JLabel("Video Selection Page");
			nameLabel.setHorizontalAlignment(JLabel.CENTER);
			panel.add(nameLabel);
		}
		else {
			try {
				URL imageUrl = new URL((String) retrieverInput.get("bannerURL"));
				InputStream in = imageUrl.openStream();
				BufferedImage image = ImageIO.read(in);
				in.close();
				this.imageIcon = new ImageIcon(image);
			}
			catch(Exception e) {e.printStackTrace();}

			JLabel imageLabel = new JLabel(imageIcon);
			panel.add(imageLabel);
		}
		panel.setAlignmentX(CENTER_ALIGNMENT);

		return panel;
	}
	protected void addContentListPanel(ContentListPanel panel) {
		if(panel != null) {
			panel.removeAll();
			panel.validate();
			panel.emptyList();
		}
		else 
			this.panel = new VideoListPanel(frame);
	}

	@Override
	protected void youtubeRetrieverSetup() {
		retriever = new VideoRetriever();
		if(channelName == null) {}
		else {
			try {
				retrieverInput = retriever.retrieveFromChannel(channelID);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private String channelName;
	private String channelID;

}

