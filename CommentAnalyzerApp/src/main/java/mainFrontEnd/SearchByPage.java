package mainFrontEnd;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.fasterxml.jackson.core.JsonParseException;

import analysisFrontEnd.CommentListPanel;
import analysisFrontEnd.CommentPanel;
import api.Retriever;
import business.Content;
import business.ContentListPanel;
import business.ContentPanel;

public abstract class SearchByPage<T> extends JPanel implements Runnable{

	public SearchByPage(JFrame frame, TaskBar bar) {
		this.bar = bar;
		this.frame = frame;
	}

	public void setPage() {
		this.setLayout(new BorderLayout());
		top.setLayout(new BoxLayout(top,BoxLayout.PAGE_AXIS));
		top.add(bar);
		top.add(Box.createRigidArea(new Dimension(0,20)));
		JLabel title = getTitle();
		title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		top.add(title);
		top.add(Box.createRigidArea(new Dimension(0,20)));
		retriever = createRetriever();
		setInitialContent();
		JPanel topPanel = new JPanel();
		topPanel.add(top);
		this.add(topPanel, BorderLayout.NORTH);
	}
	
	protected void createPanels(ArrayList<T> retrieverInput, ContentListPanel panel) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		for (Content content: (ArrayList<Content>)retrieverInput) {
			panel.addPanel(content);
		}
		panel.setPanel();
		this.add(panel);
	}
	
	protected void createJTextFields() {
		field.addActionListener(e ->{
			Thread thread = new Thread(this);
			thread.start();
		});
		JPanel fieldPanel = new JPanel();
		field.setColumns(15);
		fieldPanel.add(field);
		fieldPanel.setBorder(new EtchedBorder());
		top.add(fieldPanel);
		top.add(Box.createRigidArea(new Dimension(0,40)));
	}
	
	public void run() {
		try {
			retrieverInput = retriever.retrieve(field.getText());
		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		addContentListPanel(panel);
		createPanels(retrieverInput,panel);
		this.revalidate();
		this.repaint();
	}
	protected  void addContentListPanel(ContentListPanel panel) {};
	protected abstract JLabel getTitle();
	protected abstract void setInitialContent();
	protected abstract Retriever createRetriever();
	protected JFrame frame;
	protected ContentListPanel panel;
	protected Retriever retriever;
	protected ArrayList<T> retrieverInput;
	protected TaskBar bar;
	protected JPanel top = new JPanel();
	final JTextField field = new JTextField();
}
