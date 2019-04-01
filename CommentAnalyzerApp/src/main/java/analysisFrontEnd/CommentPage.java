package analysisFrontEnd;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.fasterxml.jackson.core.JsonParseException;

import api.CommentRetriever;
import api.Retriever;
import business.Video1;
import mainFrontEnd.SearchByPage;
import mainFrontEnd.TaskBar;
import mainFrontEnd.VideoListPanel;
import mainFrontEnd.VideoPanel;

public class CommentPage extends JPanel{


	public CommentPage(JFrame frame, TaskBar bar, String videoID) {

		this.bar = bar;
		this.frame = frame;
		this.videoID = videoID;
		setPage();
		
	}
	public void setPage(){
		JPanel top = new JPanel();
		this.setLayout(new BorderLayout());
		top.setLayout(new BoxLayout(top,BoxLayout.PAGE_AXIS));
		top.add(bar);
		//this.add(bar, BorderLayout.NORTH);
		retriever = createRetriever();
		createJTextFields();
	}
	protected void createJTextFields() {
		ArrayList<String>retrieverInput = new ArrayList<String>();
		try {
			
			retrieverInput = retriever.retrieve(videoID);
		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		createPanels(retrieverInput);
		this.revalidate();
		this.repaint();
	}

	
	protected void createPanels(ArrayList retrieverInput) {
		if(panel != null) {
		panel.removeAll();
		panel.validate();
		panel.emptyList();
		}
		else {
		panel = new CommentListPanel(frame);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		}
		for (String comment: (ArrayList<String>)retrieverInput) {
			panel.addPanel(new CommentPanel(comment));
		}
		
		panel.setPanel();
		this.add(panel);
		
		
	}

	protected Retriever createRetriever() {
		// TODO Auto-generated method stub
		return new CommentRetriever();
	}
	private String videoID;
	CommentListPanel panel;
	private TaskBar bar;
	private JFrame frame;
	private Retriever retriever;

}
