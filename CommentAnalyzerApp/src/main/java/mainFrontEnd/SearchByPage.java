package mainFrontEnd;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import api.Retriever;

public abstract class SearchByPage<T> extends JPanel{
	
	public SearchByPage(JFrame frame, TaskBar bar) {
		this.frame = frame;
		this.setLayout(new BorderLayout());
		this.add(bar, BorderLayout.NORTH);
		setPage();
	}
	
	public void setPage() {
		retriever = createRetriever();
		createJTextFields();
	}

	protected void createJTextFields() {
		final JTextField field = new JTextField();
		field.addActionListener(e ->{
			retrieverInput = retriever.retrieve(field.getText());
			createPanels(retrieverInput);
		});
		this.add(field);
	}
	
	protected abstract void createPanels(ArrayList<T> retrieverInput);
	protected abstract Retriever createRetriever();
	protected JFrame frame;
	protected Retriever retriever;
	protected ArrayList<T> retrieverInput;
}
