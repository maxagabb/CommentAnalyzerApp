package business;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

import analysisFrontEnd.CommentPage;
import byVideoFrontEnd.SearchByPage;
import byVideoFrontEnd.TaskBar;
import byVideoFrontEnd.VideoListPanel;
import byVideoFrontEnd.VideoPanel;

public abstract class ContentListPanel extends JPanel implements Runnable{

	public ContentListPanel(JFrame frame) {
		this.frame = frame;}
	public void emptyList() {};

	@SuppressWarnings("unchecked")
	public void setPanel(){
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		for(ContentPanel panel: (ArrayList<ContentPanel>)panels) {
			panel.setPanel();
			panel.setBorder(BorderFactory.createRaisedBevelBorder());
			panel.setAlignmentX(LEFT_ALIGNMENT);
			ContentListPanel self  = this;
			panel.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					self.panel = panel;
					Thread thread = new Thread(self);
					thread.start();
					/*try {
						thread.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
				}
				@Override
				public void mouseEntered(MouseEvent arg0) {
					panelColor = panel.getBackground();
					panel.setBackground(Color.WHITE);
				}
				@Override
				public void mouseExited(MouseEvent arg0) {
					panel.setBackground(panelColor);
				}
				@Override
				public void mousePressed(MouseEvent arg0) {
					panel.setBackground(panelColor);
					panel.setBorder(BorderFactory.createLoweredBevelBorder());
				}
				@Override
				public void mouseReleased(MouseEvent arg0) {}
			});
			this.add(panel, gbc);
			gbc.gridy++;
		}
	}
	
	public void run() {
		makeSearchByPage(frame, new TaskBar(frame), panel);
		page.setPage();
		JPanel borderPage = new JPanel();
		page.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
		borderPage.add(page);
		borderPage.setBorder(BorderFactory.createRaisedBevelBorder());
		JPanel finalPage = new JPanel();
		finalPage.add(borderPage);
		JScrollPane pane = new JScrollPane(finalPage);
		frame.getContentPane().removeAll();
		frame.getContentPane().revalidate();
		frame.add(pane);
		frame.repaint();
	}
	protected abstract void makeSearchByPage(JFrame frame2, TaskBar taskBar, ContentPanel panel);
	public abstract void addPanel(Content content);
	protected ArrayList panels = new ArrayList();
	protected JFrame frame;
	protected ContentPanel panel;
	protected SearchByPage page;
	protected Color panelColor;
}