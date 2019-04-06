package commentsFrontEnd;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import com.fasterxml.jackson.core.JsonParseException;

import api.CommentRetriever;
import api.Retriever;
import byVideoFrontEnd.SearchByPage;
import byVideoFrontEnd.TaskBar;
import byVideoFrontEnd.VideoListPanel;
import byVideoFrontEnd.VideoPanel;

public class CommentPage extends SearchByPage{


	public CommentPage(JFrame frame, TaskBar bar, VideoPanel videoPanel) {
		super(frame, bar);
		this.videoID = videoPanel.getVideoID();
		this.panel = new CommentListPanel(frame);
		this.videoName = videoPanel.getName();
		this.imageIcon = videoPanel.getImageIcon();

	}

	protected void createJTextFields() {
		final JTextField field2 = new JTextField();
		field2.addActionListener(e ->{
			panel.parseComments(field2.getText());
		});
		JPanel fieldPanel = new JPanel();
		field2.setColumns(15);
		fieldPanel.add(field2);
		fieldPanel.setBorder(new EtchedBorder());
		top.add(fieldPanel);
		top.add(Box.createRigidArea(new Dimension(0,40)));

	}

	@Override
	protected void setInitialContent() {
		createPanels((ArrayList) retrieverInput.get("comments"), panel);
		createJTextFields();
	}
	@Override
	protected JPanel getTitle() {
		JLabel nameLabel = new JLabel(videoName);
		nameLabel.setHorizontalAlignment(JLabel.CENTER);


		JPanel panel = new JPanel();
		panel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(nameLabel);

		return panel;
	}
	@Override
	protected void youtubeRetrieverSetup() {
		try {
			retriever = new CommentRetriever();
			HashMap<String, Object> map = new HashMap();
			map.put("comments", retriever.retrieve(videoID));
			retrieverInput =  map;
		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	private String videoID;
	private String videoName;
	private CommentListPanel panel = new CommentListPanel(frame);


}
