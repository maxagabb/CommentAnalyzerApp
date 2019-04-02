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

import api.Retriever;

public abstract class SearchByPage<T> extends JPanel{

	public SearchByPage(JFrame frame, TaskBar bar) {
		this.bar = bar;
		this.frame = frame;
	}

	public void setPage() {
		this.setLayout(new BorderLayout());
		top.setLayout(new BoxLayout(top,BoxLayout.PAGE_AXIS));
		top.setBorder(new EmptyBorder(0, 50, 0, 50));
		top.add(bar);
		top.add(Box.createRigidArea(new Dimension(0,20)));
		JLabel title = getTitle();
		title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		top.add(title);
		top.add(Box.createRigidArea(new Dimension(0,40)));
		retriever = createRetriever();
		setInitialContent();
	}


	protected void createJTextFields() {
		final JTextField field = new JTextField();
		field.addActionListener(e ->{
			try {
				retrieverInput = retriever.retrieve(field.getText());
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
		});
		top.add(field);
		top.add(Box.createRigidArea(new Dimension(0,40)));
	}
	protected abstract JLabel getTitle();
	protected abstract void setInitialContent();
	protected abstract void createPanels(ArrayList<T> retrieverInput);
	protected abstract Retriever createRetriever();
	protected JFrame frame;
	protected Retriever retriever;
	protected ArrayList<T> retrieverInput;
	protected TaskBar bar;
	protected JPanel top = new JPanel();
}
